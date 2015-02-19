package co.mobilemakers.chaplin;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import co.mobilemakers.chaplin.shows.Show;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Micaela on 16/02/2015.
 */
public class ChaplinService {

    final static String TRAKT_API_URL = "https://api-v2launch.trakt.tv";
    final static String ACCEPTED_DATA = "application/json";
    final static String API_VERSION = "2";
    final static String SHOWS_ENDPOINT = "/users/{username}/watched/shows";
    String mToken = "";

    public ChaplinService() {
    }

    public interface ApiInterface {
        @GET(SHOWS_ENDPOINT)
        void getShows(@Path("username") String username, Callback<List<Show>> callback);
    }

       public ApiInterface generateServiceInterface(String token) {
        mToken = token;
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(TRAKT_API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", ACCEPTED_DATA);
                        request.addHeader("Authorization", "Bearer " + mToken);
                        request.addHeader("trakt-api-version", API_VERSION);
                        request.addHeader("trakt-api-key", String.valueOf((R.string.client_id)));
                    }
                });
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }

}

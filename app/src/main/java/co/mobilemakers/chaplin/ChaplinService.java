package co.mobilemakers.chaplin;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

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
    final static String SHOWS_ENDPOINT = "/users/{username}/watchlist/shows";

    public ChaplinService() {
    }

    public interface ApiInterface {
        @GET(SHOWS_ENDPOINT)
        void getShows(@Path("username")String username, Callback<List<WatchList>> callback);
    }

       public ApiInterface generateServiceInterface() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(TRAKT_API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", ACCEPTED_DATA);
                        request.addHeader("Version", API_VERSION);
                        /* trakt-api-version: 2
                           trakt-api-key: [client_id] */
                    }
                });
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }

}

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

    final static String TRAKT_API_URL = "https://private-anon-e71ba2ba1-trakt.apiary-mock.com";
    final static String ACCEPTED_DATA = "application/json";
    final static String API_VERSION = "2";
    final static String CLIENT_ID = "e57d3ee05117f15cf6f5bd058d12f0a480406976c446f26963856812691c7dc2";
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
                        request.addHeader("Content-Type", ACCEPTED_DATA);
                        request.addHeader("trakt-api-version", API_VERSION);
                        request.addHeader("trakt-api-key", CLIENT_ID );
                    }
                });
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }

}

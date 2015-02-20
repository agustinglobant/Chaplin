package co.mobilemakers.chaplin;

import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import java.util.List;
import co.mobilemakers.chaplin.episodes.NextEpisode;
import co.mobilemakers.chaplin.shows.Show;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Micaela on 16/02/2015.
 */
public class ChaplinService {

    final static String TRAKT_API_URL = "https://api-v2launch.trakt.tv";
    final static String ACCEPTED_DATA = "application/json";
    final static String API_VERSION = "2";
    final static String SHOWS_ENDPOINT = "/users/me/watched/shows";
    final static String NEXT_EPISODE_ENDPOINT = "/shows/{id}/progress/watched";
    final static String WATCHING_EPISODE_ENDPOINT = "/checkin";
    String mToken = "";
    String mClientID = "";

    public ChaplinService() {
    }

    public interface ApiInterface {
        @GET(SHOWS_ENDPOINT)
        void getShows(Callback<List<Show>> callback);

        @GET(NEXT_EPISODE_ENDPOINT)
        void getNextEpisode(@Path("id") String ID, Callback<NextEpisode> callback);

        @POST(WATCHING_EPISODE_ENDPOINT)
        void putEpisodeLikeWatched(@Body JsonObject body, Callback<JsonObject> callback);
    }

       public ApiInterface generateServiceInterface(String token, String client_id) {
        mToken = token;
        mClientID = client_id;
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(TRAKT_API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", ACCEPTED_DATA);
                        request.addHeader("Authorization", "Bearer " + mToken);
                        request.addHeader("trakt-api-version", API_VERSION);
                        request.addHeader("trakt-api-key", mClientID);
                    }
                });
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }

}

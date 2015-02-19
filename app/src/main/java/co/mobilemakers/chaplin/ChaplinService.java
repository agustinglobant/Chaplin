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

    final static String SHOWS_ENDPOINT = "/users/{username}/watchlist/shows";

    public ChaplinService() {
    }

}

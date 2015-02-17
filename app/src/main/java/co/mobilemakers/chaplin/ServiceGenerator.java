package co.mobilemakers.chaplin;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by agustin on 16/02/15.
 */
public class ServiceGenerator {

    public ServiceGenerator() {
    }

    // oauth
    public static <S> S createService(Class<S> serviceClass, String baseUrl,
                                      final AccessToken accessToken){
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()));

        if (accessToken != null){
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                  request.addHeader("Authorization", accessToken.getTokenType() +
                          " " + accessToken.getAccessToken());
                }
            });
        }
        RestAdapter adapter = builder.build();

        return  adapter.create(serviceClass);
    }

    // normal auth

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        // call basic auth generator method without user and pass
        return createService(serviceClass, baseUrl, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password) {
        // set endpoint url and use OkHTTP as HTTP client
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()));

        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // create Base64 encodet string
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Authorization", string);
                    request.addHeader("Accept", "application/json");
                }
            });
        }

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}

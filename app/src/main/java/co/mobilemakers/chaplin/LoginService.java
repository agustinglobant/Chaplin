package co.mobilemakers.chaplin;

import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by agustin on 16/02/15.
 */
public interface LoginService {
    public static final String BASE_URL = "https://api-v2launch.trakt.tv";
    public static final String BASE_URL_FOR_LOGIN = "https://trakt.tv/oauth";

    @POST("/oauth/token")
    AccessToken getAccessToken(@Query("code") String code,
                               @Query("client_id") String clientId,
                               @Query("client_secret") String clientSecret,
                               @Query("redirect_uri") String redirectUri,
                               @Query("grant_type") String grantType);
}

package co.mobilemakers.chaplin;

import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by agustin on 16/02/15.
 */
public interface LoginService {
    public static final String BASE_URL = "https://api-v2launch.trakt.tv";
    public static final String BASE_URL_FOR_LOGIN = "https://trakt.tv/oauth";
}

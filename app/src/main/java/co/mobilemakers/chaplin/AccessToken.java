package co.mobilemakers.chaplin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agustin.gugliotta on 18/02/2015.
 */
public class AccessToken {
    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("token_type")
    private String mTokenType;
    @SerializedName("expires_in")
    private String mExpiresIn;
    @SerializedName("refresh_token")
    private String mRefreshToken;
    @SerializedName("scope")
    private String mScope;

    public AccessToken() {
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    public String getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        mExpiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public String getScope() {
        return mScope;
    }

    public void setScope(String scope) {
        mScope = scope;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if ( ! Character.isUpperCase(mTokenType.charAt(0))) {
            mTokenType = Character.toString(mTokenType.charAt(0)).toUpperCase() + mTokenType.substring(1);
        }
        return mTokenType;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "mAccessToken='" + mAccessToken + '\'' +
                ", mTokenType='" + mTokenType + '\'' +
                ", mExpiresIn='" + mExpiresIn + '\'' +
                ", mRefreshToken='" + mRefreshToken + '\'' +
                ", mScope='" + mScope + '\'' +
                '}';
    }

}

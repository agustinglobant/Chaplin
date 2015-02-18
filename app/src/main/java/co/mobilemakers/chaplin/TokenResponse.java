package co.mobilemakers.chaplin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agustin.gugliotta on 18/02/2015.
 */
public class TokenResponse {
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

    public TokenResponse() {
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getTokenType() {
        return mTokenType;
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

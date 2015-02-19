package co.mobilemakers.chaplin.shows;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agustin.gugliotta on 19/02/2015.
 */
public class ShowIds {
    @SerializedName("trakt")
    private String mTrakt;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("tvdb")
    private String mTvdb;
    @SerializedName("imdb")
    private String mImdb;
    @SerializedName("tmdb")
    private String mTmdb;
    @SerializedName("tvrage")
    private String mTvRange;

    public ShowIds() {
    }

    public String getTrakt() {
        return mTrakt;
    }

    public void setTrakt(String trakt) {
        mTrakt = trakt;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getTvdb() {
        return mTvdb;
    }

    public void setTvdb(String tvdb) {
        mTvdb = tvdb;
    }

    public String getImdb() {
        return mImdb;
    }

    public void setImdb(String imdb) {
        mImdb = imdb;
    }

    public String getTmdb() {
        return mTmdb;
    }

    public void setTmdb(String tmdb) {
        mTmdb = tmdb;
    }

    public String getTvRange() {
        return mTvRange;
    }

    public void setTvRange(String tvRange) {
        mTvRange = tvRange;
    }
}

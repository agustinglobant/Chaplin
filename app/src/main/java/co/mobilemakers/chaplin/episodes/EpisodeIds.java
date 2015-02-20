package co.mobilemakers.chaplin.episodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 19/02/2015.
 */
public class EpisodeIds {

 @SerializedName("trakt")
    private String mTrakt;

    @SerializedName("slug")
    private String mSlug;

    @SerializedName("imdb")
    private String mImdb;

    @SerializedName("tmdb")
    private String mTmdb;


    public EpisodeIds() {
    }

    public String getmTrakt() {
        return mTrakt;
    }

    public void setmTrakt(String mTrakt) {
        this.mTrakt = mTrakt;
    }

    public String getmSlug() {
        return mSlug;
    }

    public void setmSlug(String mSlug) {
        this.mSlug = mSlug;
    }

    public String getmImdb() {
        return mImdb;
    }

    public void setmImdb(String mImdb) {
        this.mImdb = mImdb;
    }

    public String getmTmdb() {
        return mTmdb;
    }

    public void setmTmdb(String mTmdb) {
        this.mTmdb = mTmdb;
    }
}

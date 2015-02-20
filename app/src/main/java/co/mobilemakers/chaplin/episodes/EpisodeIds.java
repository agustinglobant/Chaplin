package co.mobilemakers.chaplin.episodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 19/02/2015.
 */
public class EpisodeIds {

    @SerializedName("trakt")
    private String mTrakt;

    public EpisodeIds() {
    }

    public String getmTrakt() {
        return mTrakt;
    }

    public void setmTrakt(String mTrakt) {
        this.mTrakt = mTrakt;
    }

}

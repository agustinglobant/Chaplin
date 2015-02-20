package co.mobilemakers.chaplin.episodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 19/02/2015.
 */
public class NextEpisode {

    @SerializedName("next_episode")
    private Episode mEpisode;

    public NextEpisode() {
    }

    public Episode getmEpisode() {
        return mEpisode;
    }

    public void setmEpisode(Episode mEpisode) {
        this.mEpisode = mEpisode;
    }
}

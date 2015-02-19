package co.mobilemakers.chaplin.episodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by micaela.cavallo on 19/02/2015.
 */
public class Episode {

    @SerializedName("next_episode")
    private NextEpisode mNextEpisode;

    public Episode() {
    }

    public NextEpisode getmNextEpisode() {
        return mNextEpisode;
    }

    public void setmNextEpisode(NextEpisode mNextEpisode) {
        this.mNextEpisode = mNextEpisode;
    }

}

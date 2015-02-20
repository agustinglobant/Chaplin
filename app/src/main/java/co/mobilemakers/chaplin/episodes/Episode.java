package co.mobilemakers.chaplin.episodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 19/02/2015.
 */
public class Episode {

    @SerializedName("number")
    private String mNumber;
    @SerializedName("season")
    private String mSeason;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("ids")
    private EpisodeIds mID;

    public Episode() {
    }

    public EpisodeIds getmID() {
        return mID;
    }


    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmSeason() {
        return mSeason;
    }

    public void setmSeason(String mSeason) {
        this.mSeason = mSeason;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}

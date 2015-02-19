package co.mobilemakers.chaplin;

import com.google.gson.annotations.SerializedName;

import co.mobilemakers.chaplin.shows.ShowInfo;

/**
 * Created by Micaela on 16/02/2015.
 */
public class WatchList {

    @SerializedName("movie")
    ShowInfo mShow;

    public WatchList() {
    }

    public ShowInfo getmShow() {
        return mShow;
    }

    public void setmShow(ShowInfo mShow) {
        this.mShow = mShow;
    }

    @Override
    public String toString() {
        return mShow.toString();
    }
}

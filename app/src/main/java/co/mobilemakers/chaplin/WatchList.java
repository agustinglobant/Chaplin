package co.mobilemakers.chaplin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 16/02/2015.
 */
public class WatchList {

    @SerializedName("movie")
    Show mShow;

    public WatchList() {
    }

    public Show getmShow() {
        return mShow;
    }

    public void setmShow(Show mShow) {
        this.mShow = mShow;
    }

    @Override
    public String toString() {
        return mShow.toString();
    }
}

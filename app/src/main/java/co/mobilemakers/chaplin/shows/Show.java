package co.mobilemakers.chaplin.shows;

import com.google.gson.annotations.SerializedName;

import co.mobilemakers.chaplin.shows.ShowInfo;

/**
 * Created by Micaela on 16/02/2015.
 */
public class Show {

    @SerializedName("show")
    ShowInfo mShow;

    public Show() {
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

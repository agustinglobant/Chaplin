package co.mobilemakers.chaplin.shows;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agustin.gugliotta on 19/02/2015.
 */
public class ShowInfo {
    @SerializedName("title")
    private ShowId mTitle;
    @SerializedName("year")
    private String mYear;
    @SerializedName("ids")
    private ShowId mShowId;

    public ShowInfo() {
    }

    public ShowId getmTitle() {
        return mTitle;
    }

    public void setmTitle(ShowId mTitle) {
        this.mTitle = mTitle;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    public ShowId getmShowId() {
        return mShowId;
    }


    @Override
    public String toString() {
        return mTitle + " (" + mYear + ") ";
    }
}

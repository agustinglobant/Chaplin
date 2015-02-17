package co.mobilemakers.chaplin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 16/02/2015.
 */
public class Show {
    @SerializedName("title")
    String mTitle;
    @SerializedName("year")
    String mYear;

    public Show() {
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    @Override
    public String toString() {
        return mTitle + " (" + mYear + ")";
    }
}

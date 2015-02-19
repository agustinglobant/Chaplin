package co.mobilemakers.chaplin.shows;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Micaela on 16/02/2015.
 */
public class ShowInfo {
    @SerializedName("title")
    String mTitle;
    @SerializedName("year")
    String mYear;
    @SerializedName("ids")
    ShowIds mIds;

    public ShowInfo() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public ShowIds getIds() {
        return mIds;
    }

    public void setIds(ShowIds ids) {
        mIds = ids;
    }

    @Override
    public String toString() {
        return mTitle + " (" + mYear + ")";
    }
}

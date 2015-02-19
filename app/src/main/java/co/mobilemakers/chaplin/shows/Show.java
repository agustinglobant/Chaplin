package co.mobilemakers.chaplin.shows;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agustin.gugliotta on 19/02/2015.
 */
public class Show {
    @SerializedName("listed_at")
    private String mListedAt;
    @SerializedName("type")
    private String mType;
    @SerializedName("show")
    private ShowInfo mShowInfo;

    public Show() {
    }

    public String getListedAt() {
        return mListedAt;
    }

    public void setListedAt(String listedAt) {
        mListedAt = listedAt;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public ShowInfo getShowInfo() {
        return mShowInfo;
    }

    public void setShowInfo(ShowInfo showInfo) {
        mShowInfo = showInfo;
    }

    @Override
    public String toString() {
        return "Show{" +
                "mType='" + mType + '\'' +
                ", mListedAt='" + mListedAt + '\'' +
                '}';
    }
}

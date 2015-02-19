package co.mobilemakers.chaplin.shows;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agustin.gugliotta on 19/02/2015.
 */
public class ShowId {

    @SerializedName("slug")
    private String mSlug;

    public ShowId() {
    }

    public String getmSlug() {
        return mSlug;
    }

    public void setmSlug(String mSlug) {
        this.mSlug = mSlug;
    }
}

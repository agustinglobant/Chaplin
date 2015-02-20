package co.mobilemakers.chaplin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import co.mobilemakers.chaplin.episodes.Episode;
import co.mobilemakers.chaplin.episodes.NextEpisode;
import co.mobilemakers.chaplin.shows.Show;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.Converter;

/**
 * A placeholder fragment containing a simple view.
 */
public class NextEpisodeFragment extends Fragment {

    private ChaplinService.ApiInterface mChaplinInterface;
    String mToken = "";
    String mClientID = "";
    String mIDShow = "";
    int mIDEpisode;
    JsonObject mJson;
    final static String LOG_TAG = NextEpisodeFragment.class.getSimpleName();

    TextView mTextViewTitle;
    TextView mTextViewSeason;
    TextView mTextViewNumber;
    CheckBox mCheckBoxWatching;
    Button mButtonConfirm;

    public NextEpisodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_next_episode, container, false);
        wireUpViews(rootView);
        prepareCheckBox(rootView);
        prepareButton(rootView);
        return rootView;
    }

    private void prepareButton(View rootView) {
        mButtonConfirm = (Button)rootView.findViewById(R.id.button_confirm);
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mJson = createJson(mIDEpisode);
            mChaplinInterface.putEpisodeLikeWatched(mJson, new Callback<JsonObject>() {
                @Override
                public void success(JsonObject s, Response response) {
                    if (response.getStatus() == 201){
                        prepareIntent(mIDShow);
                    }
                    else {
                        Toast.makeText(getActivity(), "Please, try again.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (error.getResponse().getStatus() == 409)
                    {
                      Toast.makeText(getActivity(), "You already check this episode.", Toast.LENGTH_LONG).show();
                    }
                     Log.w(LOG_TAG, "ERROR: downloading " + error.getBody());
                }
            });
            }
        });
    }

    private void prepareIntent(String show) {
        Intent intent = new Intent(getActivity(), ShowsListFragment.class);
        intent.putExtra(ShowsListFragment.ID_SHOW, show);
        intent.putExtra(ShowsListFragment.TOKEN, mToken);
        startActivity(intent);
    }
    public void prepareCheckBox(View rootView)
    {
        mCheckBoxWatching = (CheckBox)rootView.findViewById(R.id.check_box_watching);
        mCheckBoxWatching.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheckBoxWatching.isChecked()) {
                    mButtonConfirm.setEnabled(true);
                } else {
                    mButtonConfirm.setEnabled(false);
                }
            }
        });
    }


    private JsonObject createJson (int idEpisode) {
        JsonObject jsonObjectEpisode = new JsonObject();
        JsonObject jsonObjectIds = new JsonObject();
        JsonObject jsonObjectSharing= new JsonObject();
        JsonObject jsonObject = new JsonObject();

        jsonObjectIds.addProperty("trakt", idEpisode);
        jsonObjectEpisode.add("ids", jsonObjectIds);

        jsonObjectSharing.addProperty("facebook", false);
        jsonObjectSharing.addProperty("twitter", false);
        jsonObjectSharing.addProperty("tumblr", false);

        jsonObject.add("episode", jsonObjectEpisode);
        jsonObject.add("sharing", jsonObjectSharing);
        jsonObject.addProperty("message", "I'm the one who knocks!");
        jsonObject.addProperty("app_version", "1.0");
        jsonObject.addProperty("app_date", "2015-02-10");
        return jsonObject;
    }

    private void wireUpViews(View rootView) {
        mTextViewTitle = (TextView)rootView.findViewById(R.id.text_view_episode_title);
        mTextViewSeason = (TextView)rootView.findViewById(R.id.text_view_episode_season);
        mTextViewNumber = (TextView)rootView.findViewById(R.id.text_view_episode_number);
    }

    private void getIntent() {
        mIDShow = getActivity().getIntent().getStringExtra(ShowsListFragment.ID_SHOW);
        mToken = getActivity().getIntent().getStringExtra(ShowsListFragment.TOKEN);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getIntent();
        mClientID = getString(R.string.client_id);
        ChaplinService chaplinService = new ChaplinService();
        mChaplinInterface = chaplinService.generateServiceInterface(mToken, mClientID);
    }

       @Override
    public void onStart() {
        super.onStart();
        mChaplinInterface.getNextEpisode(mIDShow, new Callback<NextEpisode>() {
            @Override
            public void success(NextEpisode episode, Response response) {
                if (response.getStatus() == 200) {
                   mTextViewSeason.setText("Season: " + episode.getmEpisode().getmSeason());
                   mTextViewNumber.setText("Episode: " + episode.getmEpisode().getmNumber());
                   mTextViewTitle.setText("Title: '" + episode.getmEpisode().getmTitle() + "'");
                   mIDEpisode = Integer.parseInt(episode.getmEpisode().getmID().getmTrakt());
                } else {
                    Log.e(LOG_TAG, "Project retrieval status problem: " + response.getReason());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.w(LOG_TAG, "ERROR: downloading " + error.getBody());
            }
        });
    }
}

package co.mobilemakers.chaplin;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import co.mobilemakers.chaplin.episodes.NextEpisode;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class NextEpisodeFragment extends Fragment {

    private ChaplinService.ApiInterface mChaplinInterface;
    String mToken = "";
    String mClientID = "";
    String mID= "";
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
        mCheckBoxWatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void prepareCheckBox(View rootView)
    {
        mCheckBoxWatching = (CheckBox)rootView.findViewById(R.id.check_box_watching);
        mCheckBoxWatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBoxWatching.isChecked())
                {
                    mButtonConfirm.setEnabled(true);
                }
                else
                {
                    mButtonConfirm.setEnabled(false);
                }
            }
        });
    }

    private void wireUpViews(View rootView) {
        mTextViewTitle = (TextView)rootView.findViewById(R.id.text_view_episode_title);
        mTextViewSeason = (TextView)rootView.findViewById(R.id.text_view_episode_season);
        mTextViewNumber = (TextView)rootView.findViewById(R.id.text_view_episode_number);
    }

    private void getIntent() {
        mID = getActivity().getIntent().getStringExtra(ShowsListFragment.ID_EPISODE);
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
        mChaplinInterface.getNextEpisode(mID, new Callback<NextEpisode>() {
            @Override
            public void success(NextEpisode episode, Response response) {
                if (response.getStatus() == 200) {
                   mTextViewSeason.setText("Season: " + episode.getmEpisode().getmSeason());
                   mTextViewNumber.setText("Episode: " + episode.getmEpisode().getmNumber());
                   mTextViewTitle.setText("Title: '" + episode.getmEpisode().getmTitle() + "'");

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

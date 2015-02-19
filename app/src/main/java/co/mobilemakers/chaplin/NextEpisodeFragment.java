package co.mobilemakers.chaplin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.mobilemakers.chaplin.episodes.Episode;
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

    TextView textView;

    public NextEpisodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_next_episode, container, false);
        textView = (TextView)rootView.findViewById(R.id.textView);
        return rootView;
    }

    private void getArgument() {
        /*if (getArguments().containsKey(ShowsListFragment.ID_EPISODE))
        {
            mID = getArguments().getString(ShowsListFragment.ID_EPISODE);
        }*/
        mID = getActivity().getIntent().getStringExtra(ShowsListFragment.ID_EPISODE);
        mToken = getActivity().getIntent().getStringExtra(ShowsListFragment.TOKEN);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getArgument();
        mClientID = getString(R.string.client_id);
        ChaplinService chaplinService = new ChaplinService();
        mChaplinInterface = chaplinService.generateServiceInterface(mToken, mClientID);
    }

       @Override
    public void onStart() {
        super.onStart();
        mChaplinInterface.getNextEpisode(mID, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                if (response.getStatus() == 200) {
                   textView.setText(episode.getmNextEpisode().getmTitle() + " " + episode.getmNextEpisode().getmSeason());
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

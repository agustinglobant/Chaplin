package co.mobilemakers.chaplin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import co.mobilemakers.chaplin.shows.Show;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShowsListFragment extends ListFragment {

    private ChaplinService.ApiInterface mChaplinInterface;
    private ArrayAdapter<Show> mAdapter;

    String mUsername = "micaela.cavallo";
    String mToken = "";
    final static String TOKEN = "token";
    final static String LOG_TAG = ShowsListFragment.class.getSimpleName();

    public ShowsListFragment() {
    }

        @Override
    public void onAttach(Activity activity) {
            super.onAttach(activity);
            retrieveToken();
            ChaplinService chaplinService = new ChaplinService();
            mChaplinInterface = chaplinService.generateServiceInterface(mToken);
        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void retrieveToken() {
        mToken = getActivity().getIntent().getStringExtra(TOKEN);
    }

    private void prepareListView() {
        List<Show> shows = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_shows, R.id.text_view_show_name, shows);
        setListAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mChaplinInterface.getShows(mUsername, new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                if (response.getStatus() == 200) {
                    mAdapter.clear();
                    mAdapter.addAll(shows);
                    mAdapter.notifyDataSetChanged();
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

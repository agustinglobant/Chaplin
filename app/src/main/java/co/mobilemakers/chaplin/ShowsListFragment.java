package co.mobilemakers.chaplin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    String mToken = "";
    String mClientID = "";
    final static String TOKEN = "token";
    final static String LOG_TAG = ShowsListFragment.class.getSimpleName();
    public final static String ID_SHOW = "id_episode";

    public ShowsListFragment() {
    }

        @Override
    public void onAttach(Activity activity) {
            super.onAttach(activity);
            mToken = getActivity().getIntent().getStringExtra(TOKEN);
            mClientID = getString(R.string.client_id);
            ChaplinService chaplinService = new ChaplinService();
            mChaplinInterface = chaplinService.generateServiceInterface(mToken, mClientID);
        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<Show> shows = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_shows, R.id.text_view_show_name, shows);
        setListAdapter(mAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Show showSelected = mAdapter.getItem(position);
                prepareIntent(showSelected);
            }

            private void prepareIntent(Show show) {
                Intent intent = new Intent(getActivity(), NextEpisodeActivity.class);
                intent.putExtra(ID_SHOW, show.getmShow().getmShowId().getmSlug());
                intent.putExtra("token", mToken);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mChaplinInterface.getShows(new Callback<List<Show>>() {
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

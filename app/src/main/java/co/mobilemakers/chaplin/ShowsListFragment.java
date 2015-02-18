package co.mobilemakers.chaplin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShowsListFragment extends ListFragment {

    final static String USERNAME = "USERNAME";

    private String mUsername = "";
    private ChaplinService.ApiInterface mChaplinInterface;
    private ArrayAdapter<WatchList> mAdapter;

    public ShowsListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ChaplinService chaplinService = new ChaplinService();
        mChaplinInterface = chaplinService.generateServiceInterface();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveUsername();
        prepareListView();
    }

    private void retrieveUsername() {
        /*if (getArguments().containsKey(USERNAME)) {
            mUsername = getArguments().getString(USERNAME);
        }*/
        mUsername = "sean";
    }

    private void prepareListView() {
        List<WatchList> shows = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_shows, R.id.text_view_show_name, shows);
        setListAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mChaplinInterface.getShows(mUsername, new Callback<List<WatchList>>() {
            @Override
            public void success(List<WatchList> shows, Response response) {
                mAdapter.clear();
                mAdapter.addAll(shows);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

}

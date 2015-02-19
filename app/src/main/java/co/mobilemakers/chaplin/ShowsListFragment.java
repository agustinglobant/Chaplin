package co.mobilemakers.chaplin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.mobilemakers.chaplin.shows.Show;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShowsListFragment extends ListFragment {

    private final OkHttpClient client = new OkHttpClient();
//    final static String USERNAME = "USERNAME";
//
//    private String mUsername = "";
//    private ChaplinService.ApiInterface mChaplinInterface;
//    private ArrayAdapter<WatchList> mAdapter;


    public ShowsListFragment() {
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        ChaplinService chaplinService = new ChaplinService();
//        mChaplinInterface = chaplinService.generateServiceInterface();
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getShows();
     //   retrieveUsername();
//        prepareListView();
    }

    private void getShows() {
        String token = getActivity().getIntent().getStringExtra("token");
        Request request = new Request.Builder()
                .url(LoginService.BASE_URL + "/users/agustinglobant/watched/shows")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+token)
                .addHeader("trakt-api-version", "2")
                .addHeader("trakt-api-key", getString(R.string.client_id))
                .build();

        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                String body = response.body().string();
                Log.i("Body shows", body);
//                Gson gson = new Gson();
//                ArrayList<Show> la = new ArrayList<Show>();
//                List<Show> list =  new Gson().fromJson(body,la.getClass());
//                Log.i("Shows", list.toString());
            }
        });
    }

   /* private void retrieveUsername() {
        if (getArguments().containsKey(USERNAME)) {
            mUsername = getArguments().getString(USERNAME);
        }
    }*/

//    private void prepareListView() {
//        List<WatchList> shows = new ArrayList<>();
//        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_shows, R.id.text_view_show_name, shows);
//        setListAdapter(mAdapter);
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mChaplinInterface.getShows(mUsername, new Callback<List<WatchList>>() {
//            @Override
//            public void success(List<WatchList> shows, Response response) {
//                mAdapter.clear();
//                mAdapter.addAll(shows);
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//            }
//        });
//    }

}

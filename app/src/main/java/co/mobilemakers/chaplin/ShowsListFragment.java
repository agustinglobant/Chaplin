package co.mobilemakers.chaplin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
        final String token = getActivity().getIntent().getStringExtra("token");
        Request request = new Request.Builder()
                .url(LoginService.BASE_URL + LoginService.ENDPOINT_WATCHED)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token)
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
                String bodyNameOfShows = response.body().string();
                JsonParser jsonParser = new JsonParser();
                JsonArray watchedList = jsonParser.parse(bodyNameOfShows).getAsJsonArray();
                List<String> listOfNames = getListNamesOfWatchedShows(watchedList);

                for (String name: listOfNames){
                    final Request request = new Request.Builder()
                            .url(LoginService.BASE_URL + LoginService.SHOW+name+LoginService.ENDPOINT_PROGRESS)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer " + token)
                            .addHeader("trakt-api-version", "2")
                            .addHeader("trakt-api-key", getString(R.string.client_id))
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String bodyUnwatchedEp = request.body().toString();
                            Log.i("Unw", bodyUnwatchedEp);
                        }
                    });
                }
            }

            private List<String> getListNamesOfWatchedShows(JsonArray watchedList) {
                List<String> list= new ArrayList<>();
                for (int i = 0; i < watchedList.size(); i++) {
                    list.add(watchedList.get(i)
                            .getAsJsonObject()
                            .get("show")
                            .getAsJsonObject()
                            .get("ids")
                            .getAsJsonObject()
                            .get("slug").getAsString());
                }
                return list;
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

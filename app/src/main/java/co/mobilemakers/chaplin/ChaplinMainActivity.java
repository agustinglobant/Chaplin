package co.mobilemakers.chaplin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;


public class ChaplinMainActivity extends ActionBarActivity {

    private String clientId = "e57d3ee05117f15cf6f5bd058d12f0a480406976c446f26963856812691c7dc2";
    private String clientSecret = "9572a76d16a29067d252efff05d8c4c8973bee76fe6452008e60d61a1bc7515c";
    private String redirectUri = "urn:ietf:wg:oauth:2.0:oob";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaplin_main);
        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(LoginService.BASE_URL_FOR_LOGIN + "/authorize"
                                + "?response_type=code&client_id="+ clientId
                                +"&redirect_uri=" + redirectUri));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent =  getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String code = uri.getLastPathSegment();
            LoginService loginService = ServiceGenerator.createService(LoginService.class,
                                                                       LoginService.BASE_URL,
                                                                       clientId,
                                                                       clientSecret);
//            AccessToken accessToken = loginService.getAccessToken(code,
//                                                                  clientId,
//                                                                  clientSecret,
//                                                                  redirectUri,
//                                                                  "authorization_code");

            Request request = new Request.Builder().url(LoginService.BASE_URL+"/oauth/token").build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    Log.i("Access Token", response.body().string());
                }
            });
        } else {
            Log.e("ERROR", "Uri is null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chaplin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

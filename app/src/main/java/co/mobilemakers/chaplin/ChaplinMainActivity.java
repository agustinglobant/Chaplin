package co.mobilemakers.chaplin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class ChaplinMainActivity extends ActionBarActivity {

    private String clientId;
    private String clientSecret;
    private String redirectUri = "urn:ietf:wg:oauth:2.0:oob";


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientId = getString(R.string.client_id);
        clientSecret = getString(R.string.client_secret);
        setContentView(R.layout.activity_chaplin_main);
        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                String token = sharedPref.getString(getString(R.string.access_token), "");
                if (token == "") {
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(LoginService.BASE_URL_FOR_LOGIN + "/authorize"
                                    + "?response_type=code&client_id="+ clientId
                                    +"&redirect_uri=" + redirectUri));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"You already have your token!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent =  getIntent();
        Uri uri = intent.getData();
        if (uri != null){
            authenticate(uri);
            Toast.makeText(getApplicationContext(),"You obtain an access token! YAY!",Toast.LENGTH_LONG).show();
        }
    }

    private void authenticate(Uri uri) {
        String code = uri.getLastPathSegment();

        JsonObject jsonObject = createJsonForTokenRequest(code);
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder().url(LoginService.BASE_URL+"/oauth/token")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                TokenResponse mTokenResponse = gson.fromJson(body, TokenResponse.class);
                saveAccessToken(mTokenResponse.getAccessToken());
            }

            private void saveAccessToken(String access_token) {
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.access_token), access_token);
                    editor.commit();
            }
        });
    }

    private JsonObject createJsonForTokenRequest(String code) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", code);
        jsonObject.addProperty("client_id", clientId);
        jsonObject.addProperty("client_secret", clientSecret);
        jsonObject.addProperty("redirect_uri", redirectUri);
        jsonObject.addProperty("grant_type", "authorization_code");
        return jsonObject;
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

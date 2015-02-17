package co.mobilemakers.chaplin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class LogInFragment extends Fragment {

    private String clientId = "e57d3ee05117f15cf6f5bd058d12f0a480406976c446f26963856812691c7dc2";
    private String clientSecret = "9572a76d16a29067d252efff05d8c4c8973bee76fe6452008e60d61a1bc7515c";
    private String redirectUri = "urn:ietf:wg:oauth:2.0:oob";

    public LogInFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = (Button) rootView.findViewById(R.id.button_login);
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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Uri uri = getActivity().getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)){
            String code = uri.getQueryParameter("code");
            if (code != null){
                Log.i("Information about code", code);
//                LoginService loginService = ServiceGenerator.
//                        createService(LoginService.class,
//                                      LoginService.BASE_URL,
//                                      clientId,
//                                      clientSecret);
            } else if (uri.getQueryParameter("error") != null) {
                Log.e("ERROR", "Error en el parametro code");
            }
        }
    }
}

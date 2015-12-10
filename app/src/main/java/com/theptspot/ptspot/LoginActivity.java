package com.theptspot.ptspot;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

    private Button bLogin;
    private EditText etEmail, etPassword;
    private TextView tvRegisterLink;

    private class FetchLoginTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loginWaiting();
        }

        protected JSONObject doInBackground(String... params) {
            try {
                LoginService loginService = new LoginService(params[0], params[1], params[2], params[3]);
                return loginService.login();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject response) {
            super.onPostExecute(response);

            if (response.has("error")) {
                try {
                    loginFailEvent(response.getString("error_description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (response.has("access_token")) {
                loginSuccessEvent(response.toString());
            } else {
                loginFailEvent("unknown error");
            }
        }
    }


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                new FetchLoginTask().execute(email, password, "TestClient", "TestSecret");

                //startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RegisterActivity.class));
            }
        });
    }

    private void loginFailEvent(String errorMessage) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(errorMessage);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void loginSuccessEvent(String accessToken) {

        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(this);
        try {
            persistentCookieStore.add(new URI("http://www.theptspot.com" + "/"), new HttpCookie("accessToken", accessToken));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }



        Log.i(TAG, persistentCookieStore.getCookies().toString());

        startActivity(new Intent(this, MainActivity.class));
    }

    private void loginWaiting() {
        ProgressDialog.show(this, "Logging in", "Please wait...");
    }

}

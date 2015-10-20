package com.theptspot.ptspot;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.Assert;

public class Login extends AppCompatActivity {

    private static final String TAG = Login.class.getName();
    private static final String SERVER_ADDRESS = "http://www.theptspot.com/api/";

    private Button bLogin;
    private EditText etEmail, etPassword;
    private TextView tvRegisterLink;
    UserLocalStore userLocalStore;

    private class FetchLoginTask extends AsyncTask<String, Void, String> {

        // Not sure what the three dots mean? See: http://stackoverflow.com/questions/3158730/java-3-dots-in-parameters?rq=1
        protected String doInBackground(String... parms) {

            // parms[0] is first parm, etc.
            LoginService loginService = new LoginService(parms[0], parms[1]);
            try {
                String id = loginService.getId();
                return id;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Not available";
        }

        protected void onProgressUpdate(Void... values) {

        }

        //  invoked on the UI thread after the background computation finishes
        protected void onPostExecute(String id) {
            // Example assert statements
            //Assert.assertNull("Error: id is null", id);
            //Assert.assertNotNull(id);
            //Assert.assertTrue(3 < 4);

            //updateUI(temperature);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

                new FetchLoginTask().execute(email, password);

                //startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Register.class));
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }

}

package com.theptspot.ptspot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = LoginService.class.getName();
    private Button bAccount, bFindATrainer;

    private class FetchLoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String stringURL = "http://www.theptspot.com/";
            URL url = null;
            try {
                url = new URL(stringURL);
                URLConnection urlConnection = url.openConnection();
                urlConnection.getContent();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            CookieStore cookieStore = cookieManager.getCookieStore();

            Log.i(TAG, cookieStore.getCookies().toString());
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAccount = (Button) findViewById(R.id.bAccount);
        bFindATrainer = (Button) findViewById(R.id.bFindATrainer);

        bAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LoginActivity.class));
            }
        });

        bFindATrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TrainerResultsActivity.class));
            }
        });


    }


}

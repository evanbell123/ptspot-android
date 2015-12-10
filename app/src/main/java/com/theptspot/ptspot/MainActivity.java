package com.theptspot.ptspot;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = LoginService.class.getName();
    private static final String DOMAIN = "http://www.theptspot.com";
    private Button bAccount, bFindATrainer;
    //private PersistentCookieStore persistentCookieStore = new PersistentCookieStore(this);
    //private OkHttpClient client;


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

        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(this);
        List<HttpCookie> cookies = persistentCookieStore.getCookies();

        if (isLoggedIn(cookies)) {
            showAlert(cookies.toString());
        } else {
            showAlert("You are not logged in");
        }
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Dialog box");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", null);
        builder.show();
    }

    /*
    if a cookie is found with the name "accessToken"
    then the user is logged in.
     */
    private boolean isLoggedIn(List<HttpCookie> cookies) {
        //PersistentCookieStore persistentCookieStore = new PersistentCookieStore(this);

        for (HttpCookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName() == "accessToken") {
                return true;
            }
        }

        return false;
    }


}

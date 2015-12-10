package com.theptspot.ptspot;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.net.HttpCookie;
import java.util.List;

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

        showAlert(cookies.toString());
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Dialog box");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", null);
        builder.show();
    }
}

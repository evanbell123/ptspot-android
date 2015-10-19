package com.theptspot.ptspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button bLogout;
    private EditText etFirstName, etLastName, etEmail, etBirthDate;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etBirthDate = (EditText) findViewById(R.id.etBirthDate);
        userLocalStore = new UserLocalStore(this);

        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(v.getContext(), Login.class));
            }
        });
    }

    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
        } else {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    private Boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        etFirstName.setText(user.getEmail());
        etLastName.setText(user.getEmail());
        etEmail.setText(user.getEmail());
        etBirthDate.setText(user.getEmail());
    }
}

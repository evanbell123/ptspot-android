package com.theptspot.ptspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Register extends AppCompatActivity {

    private Button bRegister;
    private EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword, etBirthDate;
    private RadioButton rbMale, rbFemale, rbTrainer, rbSeeker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etBirthDate = (EditText) findViewById(R.id.etBirthDate);
        bRegister = (Button) findViewById(R.id.bRegister);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbTrainer = (RadioButton) findViewById(R.id.rbTrainer);
        rbSeeker = (RadioButton) findViewById(R.id.rbSeeker);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etFirstName.getText().toString();
                String email = etFirstName.getText().toString();
                String password = etFirstName.getText().toString();
                String confirmPassword = etFirstName.getText().toString();
                String birthDate = etFirstName.getText().toString();
                Boolean gender;
                Integer role;
                if (rbMale.isChecked()) {
                    gender = true;
                } else {
                    gender = false;
                }

                if (rbTrainer.isChecked()) {
                    role = 1;
                } else {
                    role = 0;
                }

                //User user = new User(firstName, lastName, email, password, birthDate, gender, role);

                startActivity(new Intent(v.getContext(), Login.class));
            }
        });
    }
}

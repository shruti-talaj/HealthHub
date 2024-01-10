package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edusername,edpassword;
    Button bt;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edpassword =findViewById(R.id.editTextLoginPassword);
        edusername = findViewById(R.id.editTextLoginUsername);
        bt = findViewById(R.id.buttonLogin);
        txt= findViewById(R.id.textViewNewUser);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
                String password = edpassword.getText().toString();
                database db = new database(getApplicationContext(), "healthcareproject", null, 1);
//                startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill the required field", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getApplicationContext(),"Login succesfull",Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    if (db.login(username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }
}
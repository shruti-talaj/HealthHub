package com.example.healthhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edusername,edpassword,edemail,edconfirm;
    Button bt;
    TextView txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edusername = findViewById(R.id.editTextRegUsername);
        edpassword = findViewById(R.id.editTextRegPassword);
        edemail =findViewById(R.id.editTextRegemail);
        edconfirm =findViewById(R.id.editTextRegConfirmPassword);
        bt =findViewById(R.id.buttonregister);
        txt =findViewById(R.id.textViewExisting);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
                String password = edpassword.getText().toString();
                String confirm = edconfirm.getText().toString();
                String email = edemail.getText().toString();
                database db = new database(getApplicationContext(),"healthcareproject",null,1);

                if(username.length()==0 || password.length()==0 || email.length()==0 | confirm.length()==0){
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            db.register(username,email,confirm);
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Password must contain special characters", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password and confirm Password didn't matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean isValid(String Passwordcheck) {
        int f1=0,f2=0,f3=0;
        if(Passwordcheck.length() < 8){
            return false;
        }else{
            for(int i=0;i<Passwordcheck.length();i++){
                if(Character.isLetter(Passwordcheck.charAt(i))){
                    f1=1;
                }
            }
            for(int j=0;j<Passwordcheck.length();j++){
                if(Character.isDigit(Passwordcheck.charAt(j))){
                    f2=1;
                }
            }
            for(int k=0;k<Passwordcheck.length();k++){
                char c =Passwordcheck.charAt(k);
                if(c>= 33 && c<=46 || c==64){
                    f3=1;
                }
            }

            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }
}
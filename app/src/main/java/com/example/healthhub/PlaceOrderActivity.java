package com.example.healthhub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlaceOrderActivity extends AppCompatActivity {

    EditText fullname,address,email,contact,pincode;
    Button book,back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        fullname = findViewById(R.id.editTextFullNameC);
        address = findViewById(R.id.AddressC);
        email = findViewById(R.id.editTextRegemailC);
        contact = findViewById(R.id.ContactC);
        pincode = findViewById(R.id.PincodeC);
        back = findViewById(R.id.backPlace);
        book = findViewById(R.id.buttonBookPlace);

        Intent intent= getIntent();
        String[] price =intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database db = new database(getApplicationContext(),"healthcareproject",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                db.addorder(username,fullname.getText().toString(),address.getText().toString(),pincode.getText().toString(),contact.getText().toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");

                Toast.makeText(PlaceOrderActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PlaceOrderActivity.this,labTestPageActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlaceOrderActivity.this,itemsdeatailActivity.class) );
            }
        });
    }


}


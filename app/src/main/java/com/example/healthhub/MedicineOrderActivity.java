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

public class MedicineOrderActivity extends AppCompatActivity {

    EditText fullname,address,email,contact,pincode;
    Button book,back;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_order);

        fullname = findViewById(R.id.editTextFullNameMO);
        address = findViewById(R.id.AddressMO);
        email = findViewById(R.id.editTextRegemailMO);
        contact = findViewById(R.id.ContactMO);
        pincode = findViewById(R.id.PincodeMO);
        back = findViewById(R.id.backMO);
        book = findViewById(R.id.orderMO);


        Intent intent= getIntent();
        String[] price =intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");

                database db =new database(getApplicationContext(),"healthcareproject",null,1);
                db.addorder(username,fullname.getText().toString(),address.getText().toString(),pincode.getText().toString(),contact.getText().toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"medicine");

                Toast.makeText(MedicineOrderActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedicineOrderActivity.this,buymedicineActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineOrderActivity.this,BuymedicinedDeatailsActivity.class) );
            }
        });
    }

}
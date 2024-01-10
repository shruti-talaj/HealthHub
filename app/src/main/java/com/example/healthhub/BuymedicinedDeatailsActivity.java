package com.example.healthhub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuymedicinedDeatailsActivity extends AppCompatActivity {

    Button back,addtocart;
    EditText ed;
    TextView cost,medicinename;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buymedicined_deatails);

        back = findViewById(R.id.BMDback);
        addtocart =findViewById(R.id.BMDadddtocart);
        medicinename = findViewById(R.id.textViewBMD);
        cost = findViewById(R.id.BMDcost);
        ed = findViewById(R.id.editTextMultilineBMD);

        ed.setKeyListener(null);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuymedicinedDeatailsActivity.this,buymedicineActivity.class));
            }
        });

        Intent intent =getIntent();
        medicinename.setText(intent.getStringExtra("text1"));
        ed.setText(intent.getStringExtra("text2"));
        cost.setText("Total cost :"+intent.getStringExtra("text3")+"/-");

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = medicinename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db = new database(getApplicationContext(),"healthcareproject",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(BuymedicinedDeatailsActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addcart(username,product,price,"medicine");
                    Toast.makeText(BuymedicinedDeatailsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
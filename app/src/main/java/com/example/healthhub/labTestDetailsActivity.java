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

public class labTestDetailsActivity extends AppCompatActivity {

    Button back,addtocart;
    EditText ed;
    TextView cost,packagename;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        back = findViewById(R.id.buttonLBDBack);
        addtocart =findViewById(R.id.buttonLTDGotoCart);
        packagename = findViewById(R.id.textViewLBDlabtest);
        cost = findViewById(R.id.textViewLBDCost);
        ed = findViewById(R.id.editTextMultiline);

        ed.setKeyListener(null);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(labTestDetailsActivity.this,labTestPageActivity.class));
            }
        });

        Intent intent =getIntent();
        packagename.setText(intent.getStringExtra("text1"));
        ed.setText(intent.getStringExtra("text2"));
        cost.setText("Total cost :"+intent.getStringExtra("text3")+"/-");

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = packagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db = new database(getApplicationContext(),"healthcareproject",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(labTestDetailsActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addcart(username,product,price,"lab");
                    Toast.makeText(labTestDetailsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
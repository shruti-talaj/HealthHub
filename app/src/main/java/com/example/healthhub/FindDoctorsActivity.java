package com.example.healthhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FindDoctorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctors);

        CardView exit =findViewById(R.id.Logout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorsActivity.this,HomeActivity.class));
            }
        });


        CardView Family_Physicians = findViewById(R.id.cardFamilyPhysician);
        Family_Physicians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorsActivity.this,DoctorsDetailActivity.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
            }
        });


        CardView Dietician = findViewById(R.id.cardDietician);
        Dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorsActivity.this,DoctorsDetailActivity.class);
                it.putExtra("title","Dietician");
                startActivity(it);
            }
        });



        CardView Dentist = findViewById(R.id.cardDentist);
        Dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorsActivity.this,DoctorsDetailActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView Cardiologist = findViewById(R.id.cardcardiologist);
        Cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorsActivity.this,DoctorsDetailActivity.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });

        CardView Surgeon = findViewById(R.id.Surgeaon);
        Surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorsActivity.this,DoctorsDetailActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });


    }
}
package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class labTestPageActivity extends AppCompatActivity {

    private String[][] packages ={
            {"Package 1 : Full Body Chechup","","","","999"},
            {"Package 2 : Blood Glucose Fasting","","","","599"},
            {"Package 3 : COVID-19 Antibody - IgG","","","","699"},
            {"Package 4 : Thyroid Check","","","","899"},
            {"Package 5 : Immunity Check","","","","499"}
    };


    private String[] packages_details ={
            "Full Body Checkup\n"+"Complete Hemogram\n"+"HbA1c\n"+"Iron Studies\n"+"Lipipd Profile\n"+"Liver Function Test",
            "Blood Glucose Fasting",
            "Covid-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-Sensitive)",
            "Complete Hemogram\n"+"CRP,Serum\n"+"Iron Studies\n"+"Kidney Function Test\n"+"Vitamin D Total- 25 Hydroxy\n"+"Liver Function Test\n"+"Liquid Profile"
    };

    Button btn,gottocart;
    ListView listView;
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_page);

        btn = findViewById(R.id.backLB);
        gottocart = findViewById(R.id.GoTocart);
        listView = findViewById(R.id.listviewLB);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(labTestPageActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<packages_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(labTestPageActivity.this,labTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

        gottocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(labTestPageActivity.this,itemsdeatailActivity.class));
            }
        });
    }
}
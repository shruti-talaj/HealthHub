package com.example.healthhub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class AllorderDetailActivity extends AppCompatActivity {

    private String[][] order_details = {};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    ListView lst;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allorder_detail);

        btn= findViewById(R.id.backorderdetail);
        lst= findViewById(R.id.orderdetail);


        database db = new database(getApplicationContext(),"healthcareproject",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        ArrayList dbdata= db.getorderdata(username);
//        Toast.makeText(this, ""+dbdata, Toast.LENGTH_LONG).show();

        order_details = new String[dbdata.size()][];
        for(int i=0;i<order_details.length;i++){
            order_details[i]=new String[5];
        }

        for(int i=0;i<order_details.length;i++){
            String arrdata = dbdata.get(i).toString();
            String [] strdata = arrdata.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strdata[0];
            order_details[i][1] = "Address: "+strdata[1]+" Pincode: "+strdata[2];
            order_details[i][2] = "Contact- "+strdata[3];
            order_details[i][3] = "";
            order_details[i][4] = "Price: "+strdata[4];
//            Toast.makeText(this, ""+strdata[0], Toast.LENGTH_LONG).show();
        }

//
        list = new ArrayList();
        for(int i=0;i<order_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",order_details[i][0]);
            item.put("line2",order_details[i][1]);
            item.put("line3",order_details[i][2]);
            item.put("line4",order_details[i][3]);
            item.put("line5",order_details[i][4]);
            list.add(item);

        }
//
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllorderDetailActivity.this,HomeActivity.class));
            }
        });

    }
}
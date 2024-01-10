package com.example.healthhub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class itemsdeatailActivity extends AppCompatActivity {

    Button checkout,back;

    private String[][] packages = {};
    ListView lst;
    ArrayList list;
    HashMap item;
    SimpleAdapter sa;
    TextView tvtotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemsdeatail);


        back = findViewById(R.id.backitemdetail);
        checkout = findViewById(R.id.checkoutitemdetail);
        tvtotal =findViewById(R.id.totalamountitemdetail);
        lst = findViewById(R.id.labtestContentItemdetail);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        database db = new database(getApplicationContext(),"healthcareproject",null,1);

        float totalamount=0;
        ArrayList dbdata = db.getcartdata(username,"lab");
//        Toast.makeText(getApplicationContext(), ""+dbdata, Toast.LENGTH_LONG).show();

        packages = new String[dbdata.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }

        for (int i=0;i<dbdata.size();i++){
            String arrData = dbdata.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost :"+strData[1]+"/-";
            totalamount += Float.parseFloat(strData[1]);
//            Toast.makeText(this, ""+strData[0], Toast.LENGTH_LONG).show();

        }

        tvtotal.setText("Total Cost :"+totalamount);

        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);

        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(itemsdeatailActivity.this, labTestPageActivity.class));
            }
        });




        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(itemsdeatailActivity.this,PlaceOrderActivity.class);
                it.putExtra("price",tvtotal.getText().toString());
                startActivity(it);
            }
        });
    }

}
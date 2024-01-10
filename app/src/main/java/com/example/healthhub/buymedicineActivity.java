package com.example.healthhub;

import android.annotation.SuppressLint;
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

public class buymedicineActivity extends AppCompatActivity {

    private String[][] medicine ={
            {"Uprise-03 1000Iu capsule","","","","305"},
            {"HealthVit Chromium Picolinate 200mcg Capsule","","","","350"},
            {"Vitamin B Complex Capsules","","","","549"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","448"},
            {"Dolo 650 Advance Tablet","","","","40"},
            {"Crocin 650 Advance Tablet","","","","89"},
            {"Streplis Medicated Lozenges","","","","49"},
            {"Tata 1mg Calcium + Vitamin D3","","","","30"},
            {"Feronia -XT Tablet","","","","130"}
    };

    private String[] medcine_details = {"Building and keeping the bones and teeth strong\n"+
            "Reducing Fatigue/stress and mascular pain\n"+
            "Boosting imunity and incresing resistance against infection",
            "Chronium is an essential trace mineral that plays an important role in helping insulin",
                    "Provides relief from vitamin B deficiences\n"+
                    "Helps in formation of red blood cells\n"+
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n"+
                    "It helps reduce skin blemish and pigmentation.\n"+
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical",
                    "Helps relieve fever and bring down a heart condition or high blood pressure",
                    "Suitable for people with a high temperature\n"+
                    "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n"+
                            "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency ,Rickets and OSteroporosis\n"+
                    "Promotes mobility and flexibility o fjoints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"

    };

    HashMap<String,String > item;
    ArrayList list;
    ListView listview;
    SimpleAdapter sa;
    Button back,gottocart;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buymedicine);

        back = findViewById(R.id.backBM);
        listview = findViewById(R.id.listviewBM);
        gottocart =findViewById(R.id.GoTocartBM);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(buymedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<medicine.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",medicine[i][0]);
            item.put("line2",medicine[i][1]);
            item.put("line3",medicine[i][2]);
            item.put("line4",medicine[i][3]);
            item.put("line5","Cost"+medicine[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listview.setAdapter(sa);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =new Intent(buymedicineActivity.this,BuymedicinedDeatailsActivity.class);
                it.putExtra("text1",medicine[i][0]);
                it.putExtra("text2",medcine_details[i]);
                it.putExtra("text3",medicine[i][4]);
                startActivity(it);
            }
        });




        gottocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(buymedicineActivity.this,CartMedicineActivity.class));
            }
        });
    }
}
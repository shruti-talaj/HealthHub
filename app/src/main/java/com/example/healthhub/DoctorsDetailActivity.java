package com.example.healthhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorsDetailActivity extends AppCompatActivity {

    private String [][] doctorsDetail1 = {
            {"Doctor Name: Ajit Saste","Hospital Address: Pimpri","Exp: 5Yrs","Phone No:9898989898","Fees: 600"},
            {"Doctor Name: Parsad Pawar","Hospital Address: Mumbai","Exp: 4Yrs","Phone No: 8888887878","Fees: 900"},
            {"Doctor Name: Swaraj Kale","Hospital Address: Delhi","Exp: 10Yrs","Phone No: 9898999999","Fees: 500"},
            {"Doctor Name: Deepak Deshmukh","Hospital Address: Nagpur","Exp: 8Yrs","Phone No: 9889898998","Fees: 400"},
            {"Doctor Name: Ashok Panda","Hospital Address: Pune","Exp: 6Yrs","Phone No: 9898989999","Fees: 800"}
    };

    private String [][] doctorsDetail2 = {
            {"Doctor Name: Ajay Saste","Hospital Address: Pimpri","Exp: 5Yrs","Contact No: 9898989898","Fees: 600"},
            {"Doctor Name: Rajesh Pawar","Hospital Address: Mumbai","Exp: 4Yrs","Contact No: 8888887878","Fees: 900"},
            {"Doctor Name: Swati Kale","Hospital Address: Delhi","Exp: 10Yrs","Contact No: 9898999999","Fees: 500"},
            {"Doctor Name: Diksha Deshmukh","Hospital Address: Nagpur","Exp: 8Yrs","Contact No: 9889898998","Fees: 400"},
            {"Doctor Name: Alok Panda","Hospital Address: Pune","Exp: 6Yrs","Contact No: 9898989999","Fees: 800"}
    };

    private String [][] doctorsDetail3 = {
            {"Doctor Name: Aman Saste","Hospital Address: Pimpri","Exp: 5Yrs","Contact No: 9898989898","Fees: 600"},
            {"Doctor Name: Samrat Rajput","HospitalAddress: Mumbai","Exp: 4Yrs","Contact No: 8888887878","Fees: 900"},
            {"Doctor Name: Ashok Kale","Hospital Address: Delhi","Exp: 10Yrs","Contact No: 9898999999","Fees: 500"},
            {"Doctor Name: Divyanshi Deshmukh","Hospital Address: Nagpur","Exp: 8Yrs","Contact No: 9889898998","Fees: 400"},
            {"Doctor Name: Shivnai Yadav","Hospital Address: Pune","Exp: 6Yrs","Contact No: 9898989999","Fees: 800"}
    };

    private String [][] doctorsDetail4 = {
            {"Doctor Name: Ayush Shah","Hospital Address: Pimpri","Exp: 5Yrs","Contact No: 9898989898","Fees: 600"},
            {"Doctor Name: Pawan Parmar","Hospital Address: Mumbai","Exp: 4Yrs","Contact No: 8888887878","Fees: 900"},
            {"Doctor Name: Swaraj Kale","Hospital Address: Delhi","Exp: 10Yrs","Contact No: 9898999999","Fees: 500"},
            {"Doctor Name: Deepaka Patel","Hospital Address: Nagpur","Exp: 8Yrs","Contact No: 9889898998","Fees: 400"},
            {"Doctor Name: Ashika Jain","Hospital Address: Pune","Exp: 6Yrs","Contact No: 9898989999","Fees: 800"}
    };

    private String [][] doctorsDetail5 = {
            {"Doctor Name: Sonam Prasad","Hospital Address: Pimpri","Exp: 5Yrs","Contact No: 9898989898","Fees: 600"},
            {"Doctor Name: Ankit Pawar","Hospital Address: Mumbai","Exp: 4Yrs","Contact No: 8888887878","Fees: 900"},
            {"Doctor Name: Rahul Sharma","Hospital Address: Delhi","Exp: 10Yrs","Contact No: 9898999999","Fees: 500"},
            {"Doctor Name: Deepak dewakande","Hospital Address: Nagpur","Exp: 8Yrs","Contact No: 9889898998","Fees: 400"},
            {"Doctor Name: Asma khan","Hospital Address: Pune","Exp: 6Yrs","Contact No: 9898989999","Fees: 800"}
    };


    TextView txt;
    Button btn;
    String [][] doctordetail = {};
    ArrayList list;
    ListView listview;
    HashMap<String,String> item;
    SimpleAdapter sa;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_detail);


        txt = findViewById(R.id.textViewdoctorsheading);
        btn = findViewById(R.id.buttonDDBack);
        listview = findViewById(R.id.listviewdoctorscontent);

        Intent it= getIntent();
        String title = it.getStringExtra("title");
        txt.setText(title);

        if (title.compareTo("Family Physician")==0){
            doctordetail = doctorsDetail1;
        } else if (title.compareTo("Dietician")==0) {
            doctordetail = doctorsDetail2;
        } else if (title.compareTo("Dentist")==0) {
            doctordetail = doctorsDetail3;
        } else if (title.compareTo("Cardiologist")==0) {
            doctordetail = doctorsDetail4;
        } else if (title.compareTo("Surgeon")==0) {
            doctordetail = doctorsDetail5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorsDetailActivity.this,FindDoctorsActivity.class));
            }
        });


        list = new ArrayList();
        for (int i=0;i<doctordetail.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctordetail[i][0]);
            item.put("line2",doctordetail[i][1]);
            item.put("line3",doctordetail[i][2]);
            item.put("line4",doctordetail[i][3]);
            item.put("line5","Con"+doctordetail[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listview.setAdapter(sa);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorsDetailActivity.this,AppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctordetail[i][0]);
                it.putExtra("text3",doctordetail[i][1]);
                it.putExtra("text4",doctordetail[i][3]);
                it.putExtra("text5",doctordetail[i][4]);
                startActivity(it);
            }
        });


    }
}
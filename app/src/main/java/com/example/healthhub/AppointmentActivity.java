package com.example.healthhub;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton;
    Button back,bookappointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        tv =  findViewById(R.id.bookapptitle);
        e1 =  findViewById(R.id.editTextfullname);
        e2 =  findViewById(R.id.editTextAddress);
        e3 =  findViewById(R.id.editTextContact);
        e4 =  findViewById(R.id.editTextFees);


        dateButton = findViewById(R.id.selectdate);
        timeButton = findViewById(R.id.selecttime);

        back=findViewById(R.id.backbuttonBook);
        bookappointment=findViewById(R.id.bookbutton);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact  =it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        e1.setText(fullname);
        e2.setText(address);
        e3.setText(contact);
        e4.setText(fees);


//        initDatePicker();
//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                datePickerDialog.show();
//            }
//        });
//
//        initTimePicker();
//        timeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                timePickerDialog.show();
//            }
//        });



        bookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database db = new database(getApplicationContext(),"healthcareproject",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

//                if(db.checkappointexist(username,title,fullname,address,contact)==1){
//                    Toast.makeText(AppointmentActivity.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();
//                }else {
//                    db.addappoint(username,title,fullname,address,contact);
                    Toast.makeText(AppointmentActivity.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AppointmentActivity.this, HomeActivity.class));
//                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentActivity.this,HomeActivity.class));
            }
        });
    }

//    private void initTimePicker() {
//        TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                i1 =i1+1;
//                timeButton.setText(i+":"+i1);
//            }
//        };
//        Calendar cal= Calendar.getInstance();
//        int hrs = cal.get(Calendar.HOUR);
//        int mins = cal.get(Calendar.MINUTE);
//        int style = AlertDialog.THEME_HOLO_DARK;
//        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
//    }

//    private void initDatePicker() {
//        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                i1 =i1+1;
//                dateButton.setText(i2+"/i1"+i);
//            }
//        };
//        Calendar cal= Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        datePickerDialog= new DatePickerDialog(this,dateSetListener,year,month,day);
//        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

}
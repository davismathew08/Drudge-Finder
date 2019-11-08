package com.example.daviz.testone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;

public class Worker_Customer_Assign extends AppCompatActivity implements
        View.OnClickListener,Inter<String> {
    String worker_mail,cust_phoneno,date,time;
    Button btnDatePicker, btnTimePicker,btnassign;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__customer__assign);
        Intent intent = getIntent();
        cust_phoneno = intent.getStringExtra("cust_phone_no");
        worker_mail = intent.getStringExtra("worker_mail");
        Toast.makeText(this,cust_phoneno+worker_mail, Toast.LENGTH_LONG).show();
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnassign=(Button)findViewById(R.id.buttonassign);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnassign.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date


            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            if(year < mYear)
                            {
                                Toast.makeText(Worker_Customer_Assign.this, " Start Date is Before Today", Toast.LENGTH_SHORT).show();
                            }
                            else if(monthOfYear < mMonth && mYear <= year)
                            {
                                Toast.makeText(Worker_Customer_Assign.this, " Start Date is Before Today", Toast.LENGTH_SHORT).show();
                            }
                            else if(dayOfMonth < mDay && monthOfYear <= mMonth && year <= mYear)
                            {
                                Toast.makeText(Worker_Customer_Assign.this, " Start Date is Before Today", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                txtDate.setText(year + "-" + (monthOfYear + 1) + "-" +dayOfMonth);
                            }



                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnassign) {

            date=txtDate.getText().toString();
            time=txtTime.getText().toString();
            start("inserttowokercustomerassign",worker_mail,cust_phoneno,date,time);
        }
    }
    public void start(String flag,String worker_mail,String cust_phoneno,String date,String time){
        Webface upload=new Webface(this, (Inter<String>)Worker_Customer_Assign.this);
        Toast.makeText(this,"in start"+date+time, Toast.LENGTH_LONG).show();
        upload.execute(flag,worker_mail,cust_phoneno,date,time);
    }

    @Override
    public void complete(String result) {
        if(result.equals("New_record_created_successfully"))
        {
            Log.d("insert..","to w_c_assign");
            finish();
        }

    }
}

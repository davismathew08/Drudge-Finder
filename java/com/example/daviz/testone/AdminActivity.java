package com.example.daviz.testone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class AdminActivity extends AppCompatActivity implements  Inter<String>{

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setTitle("Admin");
        setContentView(R.layout.customer_logined);
        Intent intent = getIntent();
        Log.i("hello davis","helo");

    }


    @Override
    public void complete(String result) {

    }
}

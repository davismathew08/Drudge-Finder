package com.example.daviz.testone;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Inter<String> {
    EditText euname, eupass;
    String uname, upass;
    String type1,type1_new;
    int flag;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        euname = (EditText) findViewById(R.id.e3);
        eupass = (EditText) findViewById(R.id.e4);
        flag=0;
        type1="";
        type1_new="one";
        Log.i("hello davis", "helo");
    }


    public void onpress(View v) {

        uname = euname.getText().toString();
        upass = eupass.getText().toString();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cust_name",uname);
        editor.apply();
        eupass.setText("");
        if (uname.equals("davis@gmail.com") && upass.equals(("davismathew"))) {
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
            //finish();
        } else {
            start("login", uname, upass);
           /* try {

                //Thread.sleep(2000);

            } catch (InterruptedException e) {
                Log.e("Error in Tsleep",e.getMessage());
            }*/



        }

    }

    public void onRegister(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Registeration type");
        builder.setItems(new CharSequence[]
                        {"Worker", "Customer"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                Toast.makeText(getApplicationContext(), "Worker selected", Toast.LENGTH_SHORT).show();
                                worker_register();
                                break;
                            case 1:
                                Toast.makeText(getApplicationContext(), "Customer selected", Toast.LENGTH_SHORT).show();
                                customer_register();
                                Log.i("customer","customer act,,,,");
                                break;

                        }
                    }
                });
        builder.create().show();
    }

    public void worker_register()
    {
        Intent intent2 = new Intent(this, WorkerActivity.class);
        startActivity(intent2);
        //finish();
    }

    public void customer_register()
    {
        Log.i("customer","customer act");
        Intent intent3 = new Intent(this, CustomerActivity.class);
        startActivity(intent3);
        //finish();
    }
    public void start(String flag,String user,String pass){
        Webface upload=new Webface(this, (Inter<String>)MainActivity.this);

        upload.execute(flag,user, pass);
    }

    @Override
    public void complete(String result) {
        if(result.equals("nothing"))
        {
            Toast.makeText(this,"nothing",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d("debug",result);
            //Toast.makeText(getApplication(),"welcome",Toast.LENGTH_LONG).show();
            try
            {
                Log.d("debug","errorrrr..");
                JSONObject ob= new JSONObject(result);
                type1=ob.getString("usertype");
                flag=1;
                if(flag==1)
                {
                    if(type1.equals("one"))
                    {
                        Log.d("debug",type1);
                        Intent intent1 = new Intent(this, CustomerLogined .class);
                        startActivity(intent1);
                        //finish();
                    }
                    else if(type1.equals("two"))
                    {
                        Intent intent2 = new Intent(this, WorkerLogined .class);
                        startActivity(intent2);
                        //finish();
                    }
                    else
                    {
                        Toast.makeText(getApplication(),"unauthorized",Toast.LENGTH_LONG).show();
                    }
                }
                Log.d("debug",type1);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplication(),"unauthorized",Toast.LENGTH_LONG).show();
                Log.i("Error in logact",e.getMessage());
            }
        }


    }
}

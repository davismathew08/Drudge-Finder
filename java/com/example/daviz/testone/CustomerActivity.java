package com.example.daviz.testone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerActivity extends AppCompatActivity implements  Inter<String>{

    String c6,c1,c2,c3,c4,c5;
    EditText ed1,ed2,ed3,ed4,ed5;

    String MobilePattern = "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namevalidate="[a-zA-Z]+\\.?";

    //int i=1;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setTitle("Admin");
        setContentView(R.layout.customer_registeration);
        ed1=(EditText)findViewById(R.id.e1);
        ed2=(EditText)findViewById(R.id.e2);
        ed3=(EditText)findViewById(R.id.e3);
        ed4=(EditText)findViewById(R.id.e4);
        ed5=(EditText)findViewById(R.id.e5);
        Log.d("initi","created...");

    }
    public void onSave(View v)
    {
        //Toast.makeText(this, "davis mathew clicked", Toast.LENGTH_SHORT).show();



        c1=ed1.getText().toString();
        c2=ed2.getText().toString();
        c3=ed3.getText().toString();
        c4=ed4.getText().toString();
        c5=ed5.getText().toString();

        if(!ed1.getText().toString().matches(namevalidate))
        {
            //c4=ed4.getText().toString();
            ed1.setError("This is invalid ");

        }
       else if (ed2.getText().toString().trim().equalsIgnoreCase("")) {
            ed2.setError("This field can not be blank");
        }
        else if(!ed3.getText().toString().matches(MobilePattern))
        {
            //c3=ed3.getText().toString();
            ed3.setError("This is invalid");
        }
        else if(!ed4.getText().toString().matches(emailPattern))
        {
            //c4=ed4.getText().toString();
            ed4.setError("This is invalid ");

        }
        else if (ed5.getText().toString().trim().equalsIgnoreCase("")) {
            ed5.setError("This field can not be blank");
        }
        else
        {
            start("customerinsert",c1,c2,c3,c4,c5);
        }


       // c6=Integer.toString(i);
        //Log.d("debug1",ed1.getText().toString()+ed3.getText().toString()+c3+c4+c5);
       // Toast.makeText(this, ed1.getText().toString()+ed3.getText().toString()+c3+c4+c5, Toast.LENGTH_SHORT).show();

        //i++;
    }
    public void start(String flag,String c1,String c2,String c3,String c4,String c5){
        Webface upload=new Webface(this, (Inter<String>)CustomerActivity.this);
        Log.d("debug2",c1+c2+c3+c4+c5);
        upload.execute(flag,c1,c2,c3,c4,c5);
    }
    @Override
    public void complete(String result) {
        Log.d("debug",result);
        Toast.makeText(getApplication(),""+result,Toast.LENGTH_LONG).show();
        try {
            JSONObject ob= new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent iback=new Intent(this,MainActivity.class);
        startActivity(iback);
        finish();
    }
}

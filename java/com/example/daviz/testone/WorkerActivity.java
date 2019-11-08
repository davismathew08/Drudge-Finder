package com.example.daviz.testone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,LocationListener, Inter<String>{
    Button getLocationBtn,getsaved;
    TextView locationText,locationsaved;
    EditText et1,et2,et3,et4,et5,et6,et7;
    String se1,se2,se3,se4,se5,se6,se7,se8,se9,se10;
    Double la,lo,test_la,test_lo;
    String sla,slo;
    Integer id1=4;
    Integer f=0;
    String[] mstring=new String[10];
    Spinner spinner;
    List<String> list;
    LocationManager locationManager;
    String MobilePattern = "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namevalidate="[a-zA-Z]+\\.?";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setTitle("Admin");
        setContentView(R.layout.worker_registeration);
        spinner=findViewById(R.id.spinner);
        start("dropdownworker");

       /* dropdown=findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,mstring);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);*/


        getLocationBtn = (Button)findViewById(R.id.blocation);
        locationText = (TextView)findViewById(R.id.tlatlong);

        et1=(EditText)findViewById(R.id.e2);//name
        et2=(EditText)findViewById(R.id.e4);//rate
        et3=(EditText)findViewById(R.id.editText5);//gmail
        et4=(EditText)findViewById(R.id.e6);//password
        et5=(EditText)findViewById(R.id.editText3);//number
        et6=(EditText)findViewById(R.id.e8);//place
        et7=(EditText)findViewById(R.id.e9);//district


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        //Intent intent = getIntent();
        //Log.i("hello davis","helo");

    }
    public void onsaveworker(View v)
    {
        se1=et1.getText().toString();//name
        se2=et2.getText().toString();//rate
        se3=et3.getText().toString();//gmail
        se4=et4.getText().toString();//password
        se5=et5.getText().toString();//number
        se6=et6.getText().toString();//place
        se7=et7.getText().toString();//district

        if(!et1.getText().toString().matches(namevalidate))
        {
            //c4=ed4.getText().toString();
            et1.setError("This is invalid ");

        }
        else if (et2.getText().toString().trim().equalsIgnoreCase("")) {
            et2.setError("This field can not be blank");
        }
        else if(!et3.getText().toString().matches(emailPattern))
        {
            //c4=ed4.getText().toString();
            et3.setError("This is invalid ");

        }
        else if (et4.getText().toString().trim().equalsIgnoreCase("")) {
            et4.setError("This field can not be blank");
        }
        else if(!et5.getText().toString().matches(MobilePattern))
        {
            //c3=ed3.getText().toString();
            et5.setError("This is invalid");
        }
        else if (et6.getText().toString().trim().equalsIgnoreCase("")) {
            et6.setError("This field can not be blank");
        }
        else if (et7.getText().toString().trim().equalsIgnoreCase("")) {
            et7.setError("This field can not be blank");
        }
        else
        {
            start("insertworker",se1,se2,se3,se4,se5,se6,se7,se9,sla,slo);
        }

        //se8from on drop down
        //se9 form on complete of type_id
        //sla && slo on getlocation for lat long

        Toast.makeText(this,se1+se2+se3+se4+se5+se6+se7+se9+sla+slo,Toast.LENGTH_LONG).show();
        Log.d("insave",se1+se2+se3+se4+se5+se6+se7+se9+sla+slo);
    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        la=location.getLatitude();
        lo=location.getLongitude();
        sla=la.toString();
        slo=lo.toString();
        String sid=id1.toString();
        //start("gps", sid,sla, slo);
        //Toast.makeText(this,String.valueOf(la),Toast.LENGTH_LONG).show();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText("Latitude: " + location.getLatitude() + "Longitude: " + location.getLongitude()+addresses.get(0).getAddressLine(0)+" "+addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
            //locationText.setText(addresses.get(0).getAddressLine(0)+", "+
                  //  addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }

    }
    @Override
    public void complete(String result) {
        //Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        if(result.equals("New record created successfully"))
        {
            Intent iback=new Intent(this,MainActivity.class);
            startActivity(iback);
            finish();
        }
        JSONObject ob= null;
        try {
            ob = new JSONObject(result);
            se9=ob.getString("type_id");
            //Toast.makeText(getApplicationContext(), "Selected: " + se9, Toast.LENGTH_LONG).show();

            f=1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(f!=1)
        {
            JSONArray jsonArray= null;
            try {
                jsonArray = new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    // Log.d("Jsonobj","col1"+jsonObject.getString("type_id"));
                    Log.d("Jsonobj","col1"+jsonObject.getString("type_name"));
                    mstring[i]=jsonObject.getString("type_name");

                }
                for(int i=0;i<jsonArray.length();i++){
                    Log.d("incomplete","" +mstring[i]);
                }

                list=new ArrayList<String>();
                for(int i=0;i<jsonArray.length();i++){
                    list.add(mstring[i]);
                }
                spinner.setOnItemSelectedListener(this);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                Log.d("TETS","" + jsonArray.length());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }



    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }
    public void start(String flag){
        Webface upload=new Webface(this, (Inter<String>)WorkerActivity.this);

        upload.execute(flag);
    }
    public void start(String flag,String se8){
        Webface upload=new Webface(this, (Inter<String>)WorkerActivity.this);

        upload.execute(flag,se8);
    }
    public void start(String flag,String se1,String se2,String se3,String se4,String se5,String se6,String se7,String se9,String sla,String slo){
        Webface upload=new Webface(this, (Inter<String>)WorkerActivity.this);
        //Toast.makeText(this,se1+se2+se3+se4+se5+se6+se7+se9+sla+slo,Toast.LENGTH_LONG).show();
        Log.d("instart",se1+se2+se3+se4+se5+se6+se7+se9+sla+slo);
        upload.execute(flag,se1,se2,se3,se4,se5,se6,se7,se9,sla,slo);
    }
    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        se8 = adapterView.getItemAtPosition(i).toString();
        start("findtypeid",se8);

        Toast.makeText(adapterView.getContext(), "Selected: " + se8, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

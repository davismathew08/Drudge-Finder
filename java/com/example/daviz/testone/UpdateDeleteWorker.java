package com.example.daviz.testone;

/**
 * Created by Daviz on 2/6/2019.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Belal on 18/09/16.
 */


public class UpdateDeleteWorker extends Fragment implements AdapterView.OnItemSelectedListener,LocationListener,Inter<String>{

        EditText ed1,ed2,ed3,ed4,ed5;
        TextView tv1;
        String s1,s2,s3,s4,s5,s6,s7,s8,se9;
        String find;
        String value;
        String worker;

    Button getLocationBtn,getsaved;
    TextView locationText,locationsaved;
    Double la,lo,test_la,test_lo;
    String sla,slo;
    LocationManager locationManager;
    Integer f=0;
    String[] mstring=new String[10];
    Spinner spinner;
    List<String> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_up_del_worker, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
        spinner=view.findViewById(R.id.spineerdrop);
        tv1=view.findViewById(R.id.twid);
        ed1=view.findViewById(R.id.etwname);
        ed2=view.findViewById(R.id.etplace);
        ed3=view.findViewById(R.id.etphoneno);
        ed4=view.findViewById(R.id.etdistrict);
        ed5=view.findViewById(R.id.etrateperhour);
        start("findworkerdetails",value);
        //start("dropdownworker");

        getLocationBtn = view.findViewById(R.id.bgetchangelocation);
        locationText = view.findViewById(R.id.tvlocation);
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });



        view.findViewById(R.id.bupdateworker).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            s1=tv1.getText().toString();//id
            s2=ed1.getText().toString();//name
            s3=ed2.getText().toString();//place
            s4=ed3.getText().toString();//phoneno
            s5=ed4.getText().toString();//district
            s6=ed5.getText().toString();//rate
               //sla
               //slo
               //se9
            Log.d("updateworker",s1+s2+se9+s3+s4+s5+s6+sla+slo);
                start("updateworker",s1,s2,se9,s3,s4,s5,s6,sla,slo);

               Toast.makeText(getActivity(),"you are inside fragement"+tv1.getText().toString(),Toast.LENGTH_LONG).show();
           }
       });
        view.findViewById(R.id.bdeleteworker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=tv1.getText().toString();

               start("deleteworker",s1,value);
                Toast.makeText(getActivity(),"you are inside fragement"+tv1.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
    public void getLocation() {
        try {

            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
    public void start(String flag,String s1,String s2,String se9,String s3,String s4,String s5,String s6,String sla,String slo){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteWorker.this);
        Log.d("infun_start",s1+s2+se9+s3+s4+s5+s6+sla+slo);
        upload.execute(flag,s1,s2,se9,s3,s4,s5,s6,sla,slo);
    }
    public void start(String flag,String s1){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteWorker.this);

        upload.execute(flag,s1);
    }
    public void start(String flag){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteWorker.this);

        upload.execute(flag);
    }
    public void start(String flag,String s1,String s2){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteWorker.this);
        Toast.makeText(getActivity(),s1+s2,Toast.LENGTH_LONG).show();
        upload.execute(flag,s1,s2);
    }

    @Override
    public void complete(String result) {
        if(result.equals("changedsuccessfully"))
        {
            Toast.makeText(getContext(),"changed successfully",Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("deletedsuccessfully"))
        {
            Intent ii=new Intent(getContext(),MainActivity.class);
            startActivity(ii);
            getActivity().finish();

        }
        else
        {
            JSONObject ob= null;
            try {
                ob = new JSONObject(result);
                if(ob.names().toString().equals("[\"type_id\"]"))
                {
                    se9=ob.getString("type_id");
                    Toast.makeText(getContext(), "Selected: " + se9, Toast.LENGTH_LONG).show();

                    f=1;
                }

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
                        worker=jsonObject.names().toString();
                        // Log.d("Jsonobj","col1"+jsonObject.getString("type_id"));
                        if( worker.equals("[\"type_name\"]"))
                        {
                            Log.d("Jsonobj","col1"+jsonObject.getString("type_name"));
                            mstring[i]=jsonObject.getString("type_name");
                        }
                        else
                        {
                            tv1.setText(jsonObject.getString("w_id"));
                            ed1.setText(jsonObject.getString("w_name"));
                            ed3.setText(jsonObject.getString("phone_no"));
                            ed2.setText(jsonObject.getString("place"));
                            ed4.setText(jsonObject.getString("district"));
                            ed5.setText(jsonObject.getString("rateperhour"));

                        }

                    }
                    if(worker.equals("[\"type_name\"]"))
                    {
                        list=new ArrayList<String>();
                        for(int i=0;i<jsonArray.length();i++){
                            list.add(mstring[i]);
                        }
                        spinner.setOnItemSelectedListener(this);
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                    }
                    else
                    {
                        start("dropdownworker");
                    }

                    Log.d("TETS","" + jsonArray.length());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        s8 = adapterView.getItemAtPosition(i).toString();
        start("findtypeid",s8);

        Toast.makeText(adapterView.getContext(), "Selected: " + s8, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onLocationChanged(Location location) {
        la=location.getLatitude();
        lo=location.getLongitude();
        sla=la.toString();
        slo=lo.toString();
        //String sid=id1.toString();
        //start("gps", sid,sla, slo);
        //Toast.makeText(this,String.valueOf(la),Toast.LENGTH_LONG).show();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText("Latitude: " + location.getLatitude() + "Longitude: " + location.getLongitude()+addresses.get(0).getAddressLine(0)+" "+addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
            //locationText.setText(addresses.get(0).getAddressLine(0)+", "+
            //  addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
package com.example.daviz.testone;

/**
 * Created by Daviz on 2/6/2019.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 18/09/16.
 */


public class UpdateDeleteCustomer extends Fragment implements Inter<String>{

        EditText ed1,ed2,ed3;
        TextView tv1;
        String s1,s2,s3,s4;
        String find;
        String value;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_up_del_customer, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
        //you can set the title for your toolbar here for different fragments different titles
        tv1=view.findViewById(R.id.tid);
        ed1=view.findViewById(R.id.etname);
        ed2=view.findViewById(R.id.etplace);
        ed3=view.findViewById(R.id.etnumber);
        start("findcustomerdetails",value);


        view.findViewById(R.id.bup_date).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            s1=tv1.getText().toString();
            s2=ed1.getText().toString();
            s3=ed2.getText().toString();
            s4=ed3.getText().toString();
            Log.d("updatecustomer",s1+s2+s3+s4);
                start("updatecustomer",s1,s2,s3,s4);

               Toast.makeText(getActivity(),"you are inside fragement"+tv1.getText().toString(),Toast.LENGTH_LONG).show();
           }
       });
        view.findViewById(R.id.b_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=tv1.getText().toString();

               start("deletecustomer",s1,value);
                Toast.makeText(getActivity(),"you are inside fragement"+tv1.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
    public void start(String flag,String s1,String s2,String s3,String s4){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteCustomer.this);

        upload.execute(flag,s1,s2,s3,s4);
    }
    public void start(String flag,String s1){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteCustomer.this);

        upload.execute(flag,s1);
    }
    public void start(String flag,String s1,String s2){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteCustomer.this);
        Toast.makeText(getActivity(),s1+s2,Toast.LENGTH_LONG).show();
        upload.execute(flag,s1,s2);
    }

    @Override
    public void complete(String result) {
        if(result.equals("Newrecordcreatedsuccessfully"))
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
            Log.d("test debug", result);
            JSONArray jsonArray= null;
            try {
                jsonArray = new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Log.d("Jsonobj","col1"+jsonObject.getString("cust_id"));
                    Log.d("Jsonobj","col1"+jsonObject.getString("cust_name"));
                    Log.d("Jsonobj","col1"+jsonObject.getString("place"));
                    Log.d("Jsonobj","col1"+jsonObject.getString("phone_no"));

                    tv1.setText(jsonObject.getString("cust_id"));
                    ed1.setText(jsonObject.getString("cust_name"));
                    ed2.setText(jsonObject.getString("place"));
                    ed3.setText(jsonObject.getString("phone_no"));
                }

                Log.d("TETS","" + jsonArray.length());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}
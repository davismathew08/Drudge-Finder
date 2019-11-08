package com.example.daviz.testone;

/**
 * Created by Daviz on 2/6/2019.
 */
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 18/09/16.
 */


public class Assignedworker extends Fragment implements Inter<String>{

        TableLayout table;
        TextView t3,t4,txt_text;
        String s1;
        String idd,value;
        String date[]=new String[30];
        String wname[]=new String[50];
        String time[]=new String[50];
        Button lin_btn;
        int j,count=0,k=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_assignedworkerforcust, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_text=view.findViewById(R.id.textview12);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
        //you can set the title for your toolbar here for different fragments different titles
        table = view.findViewById(R.id.mytable);
       // t3=view.findViewById(R.id.tv3);

        start("assignedworkerforcust",value);


    }
public void function()
{

    table.setWeightSum(3f);
    for (j=0; j < count; j++) {
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(10, 0, 0, 10);
        params1.weight = 1.0f;

        LinearLayout ll;
        ll = new LinearLayout(getContext());
        ll.setGravity(Gravity.CENTER_VERTICAL);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(params1);

        final Button btn;
        btn = new Button(getContext());

        final TextView tv;
        tv=new TextView(getContext());

        final TextView tvv;
        tvv=new TextView(getContext());

        final TextView tvvv;
        tvvv=new TextView(getContext());

        btn.setText("Delete");
        btn.setTextSize(15);
        //btn.setId(Integer.parseInt(wname[j]));
        btn.setPadding(10, 8, 10, 10);

        tv.setText(wname[j]);
        tv.setTextSize(15);
        tv.setId(j);
        tv.setPadding(20,16,20,20);

        tvv.setText(date[j]);
        tvv.setTextSize(15);
        tvv.setId(j);
        tvv.setPadding(30,24,30,30);

        tvv.setText(time[j]);
        tvv.setTextSize(15);
        tvv.setId(j);
        tvv.setPadding(30,24,30,30);

        ll.addView(btn);
        ll.addView(tv);
        ll.addView(tvv);
        ll.addView(tvvv);

        table.addView(ll);


        btn.setOnClickListener(new View.OnClickListener() {
            int count2=0;

            @SuppressLint("ResourceType")
            @Override

            public void onClick(View v) {


                txt_text.setText("deleted");
                v.setVisibility(View.GONE);
                idd=tv.getText().toString();
                Toast.makeText(getActivity(),idd, Toast.LENGTH_LONG).show();
                start("deleteassignment",idd);


                }


        });
    }


}
    public void start(String flag){
        Webface upload=new Webface(getContext(), (Inter<String>)Assignedworker.this);

        upload.execute(flag);
    }
    public void start(String flag,String idd){
        Webface upload=new Webface(getContext(), (Inter<String>)Assignedworker.this);
        Toast.makeText(getActivity(),"in start"+idd, Toast.LENGTH_LONG).show();
        upload.execute(flag,idd);
    }
    public void startagain()
    {
        start("forapproval");
    }
    @Override
    public void complete(String result) {
        Log.d("test debug", result);
        if(result.equals("nothing"))
        {
            Log.d("in complte","nothing");
            Toast.makeText(getContext(),"nothing",Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("deletedsuccessfully"))
        {
            Log.d("in complte","deletedsuccessfully");
            Toast.makeText(getContext(),"deletedsuccessfully",Toast.LENGTH_SHORT).show();
        }

        else
        {
            JSONArray jsonArray= null;
            try {
                jsonArray = new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Log.d("Jsonobj","col1"+jsonObject.getString("w_name"));
                    wname[i]=jsonObject.getString("w_name");
                    Log.d("Jsonobj","col1"+jsonObject.getString("date"));
                    date[i]=jsonObject.getString("date");
                    Log.d("Jsonobj","col1"+jsonObject.getString("time"));
                    time[i]=jsonObject.getString("time");
                    count++;



                }
                Log.d("count","" +count);
                for(int i=0;i<jsonArray.length();i++){
                    Log.d("id","" + wname[i]);
                    Log.d("name","" +date[i]);
                    Log.d("name","" +time[i]);
                }
                function();
                Log.d("TETS","" + jsonArray.length());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
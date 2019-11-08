package com.example.daviz.testone;

/**
 * Created by Daviz on 2/6/2019.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Belal on 18/09/16.
 */


public class ViewWorkType extends Fragment implements Inter<String>{

        TableLayout table;
        TextView t3,t4;
        String s1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_view_work_type, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        table = view.findViewById(R.id.mytable);
       // t3=view.findViewById(R.id.tv3);
        t4=view.findViewById(R.id.tv4);
        start("viewworktype");

      /* view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                s1=ed1.getText().toString();
                start("insertworktype",s1);
               Toast.makeText(getActivity(),"you are inside fragement"+ed1.getText().toString(),Toast.LENGTH_LONG).show();
           }
       });*/

    }

    public  void showTableLayout(String type_id,String type_name){
       // Date date = new Date();
        int rows = 30;
        int colums  = 10;
        table.setStretchAllColumns(true);
        table.bringToFront();
       String ss= Calendar.getInstance().getTime().toString();
        for(int i = 0; i < rows; i++){

            TableRow tr =  new TableRow(getContext());
            for(int j = 0; j < colums; j++)
            {
                TextView txtGeneric = new TextView(getContext());
                txtGeneric.setTextSize(18);
                txtGeneric.setText( ss );
                tr.addView(txtGeneric);
            /*txtGeneric.setHeight(30); txtGeneric.setWidth(50);   txtGeneric.setTextColor(Color.BLUE);*/
            }
            table.addView(tr);

        }
    }


    public void start(String flag){
        Webface upload=new Webface(getContext(), (Inter<String>)ViewWorkType.this);

        upload.execute(flag);
    }

    @Override
    public void complete(String result) {
        Log.d("test debug", result);
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Log.d("Jsonobj","col1"+jsonObject.getString("type_id"));
                Log.d("Jsonobj","col1"+jsonObject.getString("type_name"));


                TableRow tr =  new TableRow(getContext());
                for(int j = 0; j < 1; j++)
                {
                    TextView t4 = new TextView(getContext());
                    TextView t3 = new TextView(getContext());
                    t3.setTextSize(18);
                    t4.setTextSize(18);
                    t3.setText( jsonObject.getString("type_id") );
                    t4.setText( jsonObject.getString("type_name") );
                    t4.setPaddingRelative(120,0,0,0);
                    tr.addView(t3);
                    tr.addView(t4);
            /*txtGeneric.setHeight(30); txtGeneric.setWidth(50);   txtGeneric.setTextColor(Color.BLUE);*/
                    table.addView(tr);
                }



            }

            Log.d("TETS","" + jsonArray.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
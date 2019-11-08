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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 18/09/16.
 */


public class UpdateDeleteWorkType extends Fragment implements Inter<String>{

        EditText ed1,ed2;
        String s1,s2,s3;
        String find;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_up_del_work_type, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        ed1=view.findViewById(R.id.et1);
        //ed1=EditText.findViewById(R.id.et1);
        ed2=view.findViewById(R.id.et2);
       view.findViewById(R.id.bfind).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            s1=ed1.getText().toString();
            Log.d("insdefind=",s1);
                start("findworktype",s1);

               Toast.makeText(getActivity(),"you are inside fragement"+ed1.getText().toString(),Toast.LENGTH_LONG).show();
           }
       });
        view.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=ed1.getText().toString();
                s2=ed2.getText().toString();
                start("updateworktype",s1,s2);
                Toast.makeText(getActivity(),"you are inside fragement"+ed2.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        view.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=ed1.getText().toString();
                start("deleteworktype",s1);
                Toast.makeText(getActivity(),"you are inside fragement"+ed1.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void start(String flag,String s1){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteWorkType.this);

        upload.execute(flag,s1);
    }
    public void start(String flag,String s1,String s2){
        Webface upload=new Webface(getContext(), (Inter<String>)UpdateDeleteWorkType.this);
        Toast.makeText(getActivity(),s1+s2,Toast.LENGTH_LONG).show();
        upload.execute(flag,s1,s2);
    }

    @Override
    public void complete(String result) {
        JSONObject ob;
        if(result.equals("New record created successfully"))
        {
            Log.d("infirstcomplete",result);

        }
        else
        {
            Log.d("else",result);
             try {
               ob= new JSONObject(result);
                find=ob.getString("type_name");
                ed2.setText(find);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
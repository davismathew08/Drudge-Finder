package com.example.daviz.testone;

/**
 * Created by Daviz on 2/6/2019.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class AddWorkType extends Fragment implements Inter<String>{

        EditText ed1;
        String s1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_add_work_type, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        ed1=view.findViewById(R.id.e1);
       view.findViewById(R.id.bfind).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                s1=ed1.getText().toString();
                start("insertworktype",s1);
               Toast.makeText(getActivity(),"you are inside fragement"+ed1.getText().toString(),Toast.LENGTH_LONG).show();
           }
       });

    }
    public void start(String flag,String s1){
        Webface upload=new Webface(getContext(), (Inter<String>)AddWorkType.this);

        upload.execute(flag,s1);
    }

    @Override
    public void complete(String result) {
        try {
            JSONObject ob= new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
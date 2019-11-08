package com.example.daviz.testone;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageAdminActivity extends Fragment implements AdapterView.OnItemSelectedListener,Inter<String>{
    String value,s8;
    private EditText editTextId;
    private Button buttonGetImage;
    private ImageView imageView;
    String worker;
    String [] mstring=new String[30];
    List<String> list;
    Spinner spinner;
    private RequestHandler requestHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.image_admin_activity_main, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
        spinner=view.findViewById(R.id.spinnerdropdown);
        imageView = view.findViewById(R.id.imageViewShow);

        requestHandler = new RequestHandler();
        start("viewworkerimage");


    }


    private void getImage() {
        String id = s8;
        class GetImage extends AsyncTask<String,Void,Bitmap>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                Log.d("on pre execute","hai");
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
//                Log.d("on post execute",b.toString());
                super.onPostExecute(b);
                loading.dismiss();
                imageView.setImageBitmap(b);
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String id = params[0];
                //String id = "3";
                String add = "http://b8744552.ngrok.io//project//testimage.php?id="+id;
                Log.d("in view image",add);
                URL url = null;
                Bitmap image = null;
                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    Log.d("in view image bitmap",image.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return image;
            }
        }

        GetImage gi = new GetImage();
        Log.d("in getimage",gi.toString());
        gi.execute(id);
    }


    @Override
    public void complete(String result) {
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                worker=jsonObject.names().toString();
                // Log.d("Jsonobj","col1"+jsonObject.getString("type_id"));
                if( worker.equals("[\"user_mail\"]"))
                {
                    Log.d("Jsonobj","col1"+jsonObject.getString("user_mail"));
                    mstring[i]=jsonObject.getString("user_mail");
                }

            }
            if(worker.equals("[\"user_mail\"]"))
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


            Log.d("TETS","" + jsonArray.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void start(String flag){
        Webface upload=new Webface(getContext(), (Inter<String>)ImageAdminActivity.this);

        upload.execute(flag);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        s8 = adapterView.getItemAtPosition(i).toString();


        Toast.makeText(adapterView.getContext(), "Selected: " + s8, Toast.LENGTH_LONG).show();
        getImage();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
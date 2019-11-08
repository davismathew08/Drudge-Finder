package com.example.daviz.testone;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewImage extends AppCompatActivity implements View.OnClickListener{
    String value;
    private EditText editTextId;
    private Button buttonGetImage;
    private ImageView imageView;

    private RequestHandler requestHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
         value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGetImage = (Button) findViewById(R.id.buttonGetImage);
        imageView = (ImageView) findViewById(R.id.imageViewShow);
        editTextId.setText(value);
        requestHandler = new RequestHandler();

        buttonGetImage.setOnClickListener(this);
    }


    private void getImage() {
        String id = editTextId.getText().toString().trim();
        class GetImage extends AsyncTask<String,Void,Bitmap>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                Log.d("on pre execute","hai");
                super.onPreExecute();
                loading = ProgressDialog.show(ViewImage.this, "Uploading...", null,true,true);
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
    public void onClick(View v) {
        getImage();
    }
}
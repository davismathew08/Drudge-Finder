package com.example.daviz.testone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class ImageActivity extends Fragment implements View.OnClickListener,Inter<String> {

    public static final String UPLOAD_URL = "http://b8744552.ngrok.io//project//upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;
    String value;
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;

    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.imageactivity_main, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonChoose = view.findViewById(R.id.buttonChoose);
        buttonUpload = view.findViewById(R.id.buttonUpload);
        buttonView = view.findViewById(R.id.buttonViewImage);

        imageView = view.findViewById(R.id.imageView2);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.d("ingetString image",encodedImage);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getContext(),"davis",Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                start("imageuploadd",uploadImage,value);
                Log.d("indoinback",uploadImage);
                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL,data);
                Log.d("indoinback other",result);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){
            uploadImage();
        }
        if(v==buttonView){
            viewImage();
        }
    }
    private void viewImage(){
        startActivity(new Intent(getContext(),ViewImage.class));
    }

    @Override
    public void complete(String result) {

    }
    public void start(String flag,String uploadImage,String value){
        Webface upload=new Webface(getContext(), (Inter<String>)ImageActivity.this);

        upload.execute(flag,uploadImage,value);
    }
}
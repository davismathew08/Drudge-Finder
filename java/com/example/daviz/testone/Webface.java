package com.example.daviz.testone;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Daviz on 1/8/2019.
 */

public class Webface extends AsyncTask<String,String,String> {

    private Context context;
    public String link,data=null,flag;
    ProgressDialog pd;
    private Inter<String> callback;
    public Webface(Context context, Inter<String> cb)
    {
        this.context=context;
        this.callback=cb;
    }
    @Override
    protected String doInBackground(String... arg) {

       String urlhead= "http://b8744552.ngrok.io";
        Log.d("debug1","doinback");
        flag = (String)arg[0];
        try {
            if (flag.equals("login")) {
                String uname = (String) arg[1];
                String password = (String) arg[2];
                String usertype="";
                link = urlhead+"\\project\\userlogin.php";

                data = URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                data += "&" + URLEncoder.encode("usertype", "UTF-8") + "=" + URLEncoder.encode(usertype, "UTF-8");
                Log.d("debug",data);
            }
            if (flag.equals("imageuploadd")) {
                String uploadImage = (String) arg[1];
                String value = (String) arg[2];

                link = urlhead+"\\project\\imageuploadd.php";

                data = URLEncoder.encode("uploadImage", "UTF-8") + "=" + URLEncoder.encode(uploadImage, "UTF-8");
                data += "&" + URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");

                Log.d("debug",data);
            }
            if (flag.equals("customerinsert")) {

                String cname = (String) arg[1];
                String cplace = (String) arg[2];
                String cphoneno = (String) arg[3];
                String cmail = (String) arg[4];
                String cpass = (String) arg[5];
                //String id=(String) arg[6];
                Log.d("debug1",cname+cplace+cphoneno+cmail+cpass);
               // Log.i(name,batch);
                link = urlhead+"\\project\\customerinsert.php";
                data = URLEncoder.encode("cname", "UTF-8") + "=" + URLEncoder.encode(cname, "UTF-8");
                data += "&" + URLEncoder.encode("cplace", "UTF-8") + "=" + URLEncoder.encode(cplace, "UTF-8");
                data += "&" + URLEncoder.encode("cphoneno", "UTF-8") + "=" + URLEncoder.encode(cphoneno, "UTF-8");
                data += "&" + URLEncoder.encode("cmail", "UTF-8") + "=" + URLEncoder.encode(cmail, "UTF-8");
                data += "&" + URLEncoder.encode("cpass", "UTF-8") + "=" + URLEncoder.encode(cpass, "UTF-8");
               // data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            }
            if (flag.equals("updatecustomer")) {

                String cust_id = (String) arg[1];
                String cust_name = (String) arg[2];
                String place = (String) arg[3];
                String phone_no = (String) arg[4];

                link = urlhead+"\\project\\updatecustomer.php";
                data = URLEncoder.encode("cust_id", "UTF-8") + "=" + URLEncoder.encode(cust_id, "UTF-8");
                data += "&" + URLEncoder.encode("cust_name", "UTF-8") + "=" + URLEncoder.encode(cust_name, "UTF-8");
                data += "&" + URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8");
                data += "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");

            }
            if (flag.equals("updateworker")) {

                String w_id = (String) arg[1];
                String w_name = (String) arg[2];
                String type_id = (String) arg[3];
                String place = (String) arg[4];

                String phone_no = (String) arg[5];
                String district = (String) arg[6];
                String rateperhour = (String) arg[7];
                String lattitude = (String) arg[8];
                String longitude = (String) arg[9];
                Log.d("updateworker",w_id+w_name+type_id+place+phone_no+district+rateperhour+lattitude+longitude);
                link = urlhead+"\\project\\updateworker.php";
                data = URLEncoder.encode("w_id", "UTF-8") + "=" + URLEncoder.encode(w_id, "UTF-8");
                data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                data += "&" + URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                data += "&" + URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8");

                data += "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");
                data += "&" + URLEncoder.encode("district", "UTF-8") + "=" + URLEncoder.encode(district, "UTF-8");
                data += "&" + URLEncoder.encode("rateperhour", "UTF-8") + "=" + URLEncoder.encode(rateperhour, "UTF-8");

                data += "&" + URLEncoder.encode("lattitude", "UTF-8") + "=" + URLEncoder.encode(lattitude, "UTF-8");
                data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8");


            }
            if (flag.equals("insertworker")) {
                Log.d("insertworker","hello");

                String w_name = (String) arg[1];
                String rateperhour = (String) arg[2];
                String user_mail = (String) arg[3];
                String password = (String) arg[4];
                String phone_no = (String) arg[5];
                String place = (String) arg[6];
                String district = (String) arg[7];
                String type_id = (String) arg[8];
                String lattitude = (String) arg[9];
                String longitude = (String) arg[10];
                //String id=(String) arg[6];
                Log.d("debug1",w_name+rateperhour+user_mail+phone_no+lattitude);
                // Log.i(name,batch);
                link = urlhead+"\\project\\workerinsert.php";
                data = URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                data += "&" + URLEncoder.encode("rateperhour", "UTF-8") + "=" + URLEncoder.encode(rateperhour, "UTF-8");
                data += "&" + URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                data += "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");
                data += "&" + URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8");
                data += "&" + URLEncoder.encode("district", "UTF-8") + "=" + URLEncoder.encode(district, "UTF-8");
                data += "&" + URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                data += "&" + URLEncoder.encode("lattitude", "UTF-8") + "=" + URLEncoder.encode(lattitude, "UTF-8");
                data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8");
               // data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            }
            if (flag.equals("insertworktype")) {

                String wtype = (String) arg[1];


                link = urlhead+"\\project\\insertworktype.php";
                data = URLEncoder.encode("wtype", "UTF-8") + "=" + URLEncoder.encode(wtype, "UTF-8");

            }
            if (flag.equals("insertto_call_logs")) {

                String called_name = (String) arg[1];
                String called_phone = (String) arg[2];
                String user_mail = (String) arg[3];

                Log.d("in call webface",called_name+called_phone);

                link = urlhead+"\\project\\insertto_call_logs.php";
                data = URLEncoder.encode("called_name", "UTF-8") + "=" + URLEncoder.encode(called_name, "UTF-8");
                data += "&" + URLEncoder.encode("called_phone", "UTF-8") + "=" + URLEncoder.encode(called_phone, "UTF-8");
                data += "&" + URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");
            }if (flag.equals("inserttowokercustomerassign")) {

                String user_mail = (String) arg[1];
                String phone_no = (String) arg[2];
                String date = (String) arg[3];
                String time = (String) arg[4];

                Log.d("in call webface inserttowokercustomerassign","...");

                link = urlhead+"\\project\\inserttowokercustomerassign.php";
                data = URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");
                data += "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");
                data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                data += "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");

            }
            if (flag.equals("viewworktype")) {
                Log.d("debug1","viewworktype");
                String type_id="";
                String type_name="";


                link = urlhead+"\\project\\test1.php";
                data = URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                data += "&" + URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");

            }
            if (flag.equals("viewworkerimage")) {
                Log.d("debug1","viewworkerimage");
                String user_mail="";


                link = urlhead+"\\project\\viewworkerimage.php";
                data = URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");

            }
            if (flag.equals("findcustomerdetails")) {
                Log.d("debug1","findcustomerdetails");
                String user_mail = (String) arg[1];
                String cust_id="";
                String cust_name="";
                String place="";
                String phone_no="";

                link = urlhead+"\\project\\findcustomerdetails.php";
                data = URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");
                data += "&" + URLEncoder.encode("cust_id", "UTF-8") + "=" + URLEncoder.encode(cust_id, "UTF-8");
                data += "&" + URLEncoder.encode("cust_name", "UTF-8") + "=" + URLEncoder.encode(cust_name, "UTF-8");
                data += "&" + URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8");
                data += "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");

            }
            if (flag.equals("customerviewworktype")) {
                Log.d("debug1","customerviewworktype");
                //String type_id="";
                String type_name="";


                link = urlhead+"\\project\\customerviewworktype.php";
                data = URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");
               // data += "&" + URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");

            }
            if (flag.equals("customerviewplace")) {
                Log.d("debug1","customerviewplace");
                //String type_id="";
                String place="";


                link = urlhead+"\\project\\customerviewplace.php";
                data = URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8");
                // data += "&" + URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");

            }
            if (flag.equals("findworktype")) {

                String type_id=(String) arg[1];
                String type_name="";


                link = urlhead+"\\project\\findworktype.php";
                data = URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                data += "&" + URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");

            }
            if (flag.equals("deletecustomer")) {

                String cust_id=(String) arg[1];
                String user_mail=(String) arg[2];
               // String type_name="";


                link = urlhead+"\\project\\deletecustomer.php";
                data = URLEncoder.encode("cust_id", "UTF-8") + "=" + URLEncoder.encode(cust_id, "UTF-8");
                data += "&" + URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");

            }
            if (flag.equals("deleteworker")) {

                String w_id=(String) arg[1];
                String user_mail=(String) arg[2];
                // String type_name="";


                link = urlhead+"\\project\\deleteworker.php";
                data = URLEncoder.encode("w_id", "UTF-8") + "=" + URLEncoder.encode(w_id, "UTF-8");
                data += "&" + URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");

            }
            if (flag.equals("findworkerdetails")) {

                String item=(String) arg[1];
                String w_id="";
                String w_name="";
                String Phone_no="";
                String place="";
                String district="";
                String rateperhour="";

                link = urlhead+"\\project\\findworkerdetails.php";
                data = URLEncoder.encode("item", "UTF-8") + "=" + URLEncoder.encode(item, "UTF-8");
                data += "&" + URLEncoder.encode("w_id", "UTF-8") + "=" + URLEncoder.encode(w_id, "UTF-8");
                data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                data += "&" + URLEncoder.encode("Phone_no", "UTF-8") + "=" + URLEncoder.encode(Phone_no, "UTF-8");
                data += "&" + URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode(place, "UTF-8");
                data += "&" + URLEncoder.encode("district", "UTF-8") + "=" + URLEncoder.encode(district, "UTF-8");
                data += "&" + URLEncoder.encode("rateperhour", "UTF-8") + "=" + URLEncoder.encode(rateperhour, "UTF-8");
            }
            if (flag.equals("customerviewworkerforsearch")) {

                String item=(String) arg[1];
                String w_name="";
                String Phone_no="";
                String lattitude="";
                String longitude="";

                link = urlhead+"\\project\\customerviewworkerforsearch.php";
                data = URLEncoder.encode("item", "UTF-8") + "=" + URLEncoder.encode(item, "UTF-8");
                data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                data += "&" + URLEncoder.encode("Phone_no", "UTF-8") + "=" + URLEncoder.encode(Phone_no, "UTF-8");
                data += "&" + URLEncoder.encode("lattitude", "UTF-8") + "=" + URLEncoder.encode(lattitude, "UTF-8");
                data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8");
            }
            if (flag.equals("updateworktype")) {
                Log.d("debug","updateworktype.....");
                String type_id=(String) arg[1];
                String type_name=(String) arg[2];

                Log.d("debug",type_id+type_name);
                link = urlhead+"\\project\\updateworktype.php";
                data = URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                data += "&" + URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");
                //Toast.makeText(null,"updated successfully",Toast.LENGTH_LONG).show();
                Log.d("debug",data);
                Log.d("debug",type_id+type_name);
            }
            if (flag.equals("deleteworktype")) {

                String type_id=(String) arg[1];
                String type_name="";


                link = urlhead+"\\project\\deleteworktype.php";
                data = URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                data += "&" + URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");
                //Toast.makeText(null,"deleted successfully",Toast.LENGTH_LONG).show();
            }
            if (flag.equals("dropdownworker")) {
                //String uname = (String) arg[1];
               // String password = (String) arg[2];
                String usertype="";
                link = urlhead+"\\project\\dropdownworker.php";

                data = URLEncoder.encode("usertype", "UTF-8") + "=" + URLEncoder.encode(usertype, "UTF-8");
               // data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
               // data += "&" + URLEncoder.encode("usertype", "UTF-8") + "=" + URLEncoder.encode(usertype, "UTF-8");

            }
            if (flag.equals("findtypeid")) {
                String type_name = (String) arg[1];
                String type_id = "";
                //String usertype="";
                link = urlhead+"\\project\\findtypeid.php";

                data = URLEncoder.encode("type_name", "UTF-8") + "=" + URLEncoder.encode(type_name, "UTF-8");
                 data += "&" + URLEncoder.encode("type_id", "UTF-8") + "=" + URLEncoder.encode(type_id, "UTF-8");
                // data += "&" + URLEncoder.encode("usertype", "UTF-8") + "=" + URLEncoder.encode(usertype, "UTF-8");
                Log.d("debug",data);
            }

            if (flag.equals("deleteassignment")) {
                String w_name=(String) arg[1];
                link = urlhead+"\\project\\deleteassignment.php";
                data = URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                //data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");

                Log.d("debug","deleteassignment");
            }
            if (flag.equals("assignedworkerforcust")) {
                String user_mail = (String) arg[1];
                String w_name="";
                String date="";
                String time="";
                link = urlhead+"\\project\\assignedworkerforcust.php";
                data = URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");
                data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                data += "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");
                Log.d("debug","assignedworkerforcust");
            }
            if (flag.equals("workassignments")) {
                String user_mail = (String) arg[1];
                String cust_name="";
                String phone_no_="";
                link = urlhead+"\\project\\workassignments.php";
                data = URLEncoder.encode("user_mail", "UTF-8") + "=" + URLEncoder.encode(user_mail, "UTF-8");
                data += "&" + URLEncoder.encode("cust_name", "UTF-8") + "=" + URLEncoder.encode(cust_name, "UTF-8");
                data += "&" + URLEncoder.encode("phone_no_", "UTF-8") + "=" + URLEncoder.encode(phone_no_, "UTF-8");

                Log.d("debug","workassignments");
            }
            if (flag.equals("forapproval")) {
                String w_id="";
                String w_name="";
                link = urlhead+"\\project\\forapproval.php";
                data = URLEncoder.encode("w_id", "UTF-8") + "=" + URLEncoder.encode(w_id, "UTF-8");
                data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");

                Log.d("debug","forapproval");
            }
            if (flag.equals("updateapproveworker")) {
                Log.d("updateeee","eeeeeeeeeeee");
                String w_id=(String) arg[1];
                //String w_name="";
                link = urlhead+"\\project\\updateapproveworker.php";
                data = URLEncoder.encode("w_id", "UTF-8") + "=" + URLEncoder.encode(w_id, "UTF-8");
                //data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");

                Log.d("debug","updateapproveworker");
            }
            if (flag.equals("viewallworkers")) {
                String w_id="";
                String w_name="";
                link = urlhead+"\\project\\viewallworkers.php";
                data = URLEncoder.encode("w_id", "UTF-8") + "=" + URLEncoder.encode(w_id, "UTF-8");
                data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");

                Log.d("debug","forapproval");
            }
            if (flag.equals("findworkerformtype")) {
                //String w_id="";
                String w_name="";
                link = urlhead+"\\project\\findworkerformtype.php";
                data = URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");
                //data += "&" + URLEncoder.encode("w_name", "UTF-8") + "=" + URLEncoder.encode(w_name, "UTF-8");

                Log.d("debug","findworkerformtype");
            }

            URL url;
            url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            Log.d("debug1",data);
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            String line1="nothing";
            while((line = reader.readLine()) != null) {
                sb.append(line);
                Log.i("LINE IN UPLOADed",line);
            }

            //String strm = sb.toString();

            return sb.toString();
        }
        catch (Exception e)
        {
            Log.i("Error in Login",e.getMessage());
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        callback.complete(result);
    }
}

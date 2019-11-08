package com.example.daviz.testone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import android.widget.SearchView;

import static com.example.daviz.testone.R.id.scree_area;
import static com.example.daviz.testone.R.id.scree_area1;


public class CustomerLogined extends AppCompatActivity implements Inter<String> {



    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv1;

    public ListView list;
    public ArrayList<String> list1;
    public ListViewAdapter adapter;
    TextView tvv1;
    Spinner spinner,spinner1;
    public static ArrayList<MovieNames> movieNamesArrayList = new ArrayList<MovieNames>();
    public static ArrayList<PhoneNames> phonnumberArrayList = new ArrayList<PhoneNames>();
    public static ArrayList<LattNames> lattArrayList = new ArrayList<LattNames>();
    public static ArrayList<LongNames> longArrayList = new ArrayList<LongNames>();
    public String[] moviewList;
    public String[] phonenumber;
    public String[] lat;
    public String[] lot;
    public String[] worktypeget = new String[50];
    public String[] placeget=new String[50];
    public String workname,typename,value;
    int count,setpostion;
    String number,n1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_logined);
        list = (ListView) findViewById(R.id.listview);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString("cust_name", "defaultValue");
        Log.d("sharedpreference",value);
        // Locate the ListView in listview_main.xml
        spinner1=(Spinner)findViewById(R.id.spinner3);
        spinner=(Spinner)findViewById(R.id.spinner2);
        spinner.setVisibility(View.VISIBLE);
        spinner1.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                // String selectitem=String.valueOf(spinner.getSelectedItem());
                //Log.d("error",item+selectitem);
                // Showing selected spinner item
                if(item.equals("work_type"))
                {
                    start("customerviewworktype");
                    Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                }
                else
                {
                    start("customerviewplace");
                    Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.spinner_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv1 = (NavigationView)findViewById(R.id.nvc);
        nv1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                int id = item.getItemId();
                if (id == R.id.profile)
                {
                    spinner.setVisibility(View.GONE);
                    spinner1.setVisibility(View.GONE);
                    list.setVisibility(View.GONE);
                    fragment=new UpdateDeleteCustomer();
                    Toast.makeText(CustomerLogined.this, "Edit profile", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.cust_search)
                {
                    //((DrawerLayout)findViewById(R.id.activity_main)).removeAllViewsInLayout();
                    Intent ii=new Intent(getApplicationContext(),CustomerLogined.class);
                    startActivity(ii);
                    finish();

                    Toast.makeText(CustomerLogined.this, "Edit profile", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.cust_assignment)
                {
                    spinner.setVisibility(View.GONE);
                    spinner1.setVisibility(View.GONE);
                    list.setVisibility(View.GONE);
                    //((DrawerLayout)findViewById(R.id.activity_main)).removeAllViewsInLayout();
                    fragment=new Assignedworker();
                    Toast.makeText(CustomerLogined.this, "Assignedworker", Toast.LENGTH_SHORT).show();
                }



                else if (id == R.id.cust_logout) {
                    //customerlogout();
                    finish();
                    Toast.makeText(CustomerLogined.this, "logout", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(CustomerLogined.this, "wrong choice", Toast.LENGTH_SHORT).show();
                if(fragment!=null)
                {

                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    //fragmentTransaction.add(scree_area1,fragment);
                    fragmentTransaction.replace(scree_area1,fragment);
                    fragmentTransaction.commit();
                }
                DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;

            }
        });
        //start("findworkerformtype");
       // callfuction();

    }
    public void start(String flag){
        Webface upload=new Webface(this, (Inter<String>)CustomerLogined.this);

        upload.execute(flag);
    }
    public void start(String flag,String n1,String number,String value){
        Webface upload=new Webface(this, (Inter<String>)CustomerLogined.this);
        Log.d("in insert start","...");
        upload.execute(flag,n1,number,value);
    }
    public void callfuction()
    {
        movieNamesArrayList = new ArrayList<>();
        phonnumberArrayList=new ArrayList<>();
        lattArrayList=new ArrayList<>();
        longArrayList=new ArrayList<>();

        for (int i = 0; i < moviewList.length; i++) {
            MovieNames movieNames = new MovieNames(moviewList[i]);
            // Binds all strings into an array
            movieNamesArrayList.add(movieNames);

        }
        for (int i = 0; i < lat.length; i++) {
            LattNames lattNames = new LattNames(lat[i]);
            // Binds all strings into an array
            lattArrayList.add(lattNames);

        }
        for (int i = 0; i < phonenumber.length; i++) {
            PhoneNames phoneNames = new PhoneNames(phonenumber[i]);
            // Binds all strings into an array
            phonnumberArrayList.add(phoneNames);

        }
        for (int i = 0; i < lot.length; i++) {
            LongNames longNames = new LongNames(lot[i]);
            // Binds all strings into an array
            longArrayList.add(longNames);

        }
        for(int k=0;k<count;k++)
        {
            Log.d("fun","nm"+moviewList[k]);
            Log.d("fun","ph"+phonenumber[k]);
            Log.d("fun","ll"+lat[k]);
            Log.d("fun","lo"+lot[k]);
        }
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);

        // Locate the EditText in listview_main.xml


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(CustomerLogined.this, movieNamesArrayList.get(position).getAnimalName(), Toast.LENGTH_SHORT).show();
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);
                n1=movieNamesArrayList.get(position).getAnimalName();
                Log.d("moviename","me"+n1);
                final int ph=fun_get_phone(n1);

                Log.d("phoneee","noo"+ph);
                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                TextView t=(TextView)popupView.findViewById(R.id.tvv1);
                ImageButton bphone=(ImageButton)popupView.findViewById(R.id.imagebutton_call);
                Log.d("st","st"+phonenumber[ph]);
                Log.d("st","st"+lat[ph]);
                Log.d("st","st"+lot[ph]);
                bphone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("stringggg","str"+phonenumber[ph]);
                        number=phonenumber[ph];
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        value = preferences.getString("cust_name", "defaultValue");
                        Log.d("sharedpreference",value);
                        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("worker_phone",number);
                        editor.putString("woker_name",n1);
                        editor.apply();*/
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        String p = "tel:" + number;
                        i.setData(Uri.parse(p));
                        startActivity(i);
                        start("insertto_call_logs",n1,number,value);
                    }
                });
                ImageButton bgps=(ImageButton)popupView.findViewById(R.id.imagebutton_map);
                bgps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("mapp","str"+lat[ph]+lot[ph]);
                        String dla=lat[ph];
                        String dlo=lot[ph];

                        Intent i=new Intent(getApplicationContext(),MapsActivity.class);
                        i.putExtra("latt",dla);
                        i.putExtra("longg",dlo);
                        startActivity(i);


                    }
                });

                t.setText(movieNamesArrayList.get(position).getAnimalName());
                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
    public int fun_get_phone(String n1)
    {
        for(int k=0;k<count;k++)
        {
            if(moviewList[k].equals(n1))
            {
                setpostion=k;
            }
        }
        return setpostion;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    public void customerlogout()
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }






    @Override
    public void complete(String result) {
        if(result.equals("New_record_created_successfully"))
        {
            Log.d("insert..","to call logs");

        }
        else
        {
            moviewList = new String[50];
            phonenumber = new String[50];
            lat = new String[50];
            lot = new String[50];
            JSONArray jsonArray= null;
            try {

                jsonArray = new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    //Toast.makeText(this,jsonObject.names().toString(),Toast.LENGTH_LONG).show();
                    count=jsonArray.length();
                    workname=jsonObject.names().toString();
                    //Toast.makeText(this,workname,Toast.LENGTH_LONG).show();
                    if(workname.equals("[\"type_name\"]"))
                    {

                        // Toast.makeText(this,workname,Toast.LENGTH_LONG).show();
                        Log.d("Jsonobj","col1"+jsonObject.getString("type_name"));
                        worktypeget[i]=jsonObject.getString("type_name");
                    }
                    else if(workname.equals("[\"place\"]"))
                    {
                        // Toast.makeText(this,workname,Toast.LENGTH_LONG).show();
                        Log.d("Jsonobj","col1"+jsonObject.getString("place"));
                        placeget[i]=jsonObject.getString("place");
                    }
                    else
                    {

                        if(jsonObject.getString("w_name").equals("nothing"))
                        {
                            // Toast.makeText(this,"nothingggggg",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            //Toast.makeText(this,workname,Toast.LENGTH_LONG).show();
                            Log.d("Jsonobj","col1"+jsonObject.getString("w_name"));
                            moviewList[i]=jsonObject.getString("w_name");
                            phonenumber[i]=jsonObject.getString("phone_no");
                            lat[i]=jsonObject.getString("lattitude");
                            lot[i]=jsonObject.getString("longitude");
                        }

                    }

                    for(int k=0;k<count;k++)
                    {
                        Log.d("arr","v"+phonenumber[k]);
                        Log.d("arr","v1"+lat[k]);
                        Log.d("arr","v2"+lot[k]);
                    }


                }
                if(workname.equals("[\"type_name\"]"))
                {
                    selecttype();
                    // Toast.makeText(this,"intype",Toast.LENGTH_LONG).show();
                    //Log.d("TETS","" + jsonArray.length());
                }
                else if(workname.equals("[\"place\"]"))
                {
                    selectplace();
                    //Toast.makeText(this,"inplace",Toast.LENGTH_LONG).show();
                }
                else
                {
                    // Toast.makeText(this,"wname...",Toast.LENGTH_LONG).show();
                    callfuction();
                    Log.d("Jsonobj","col1eeee");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }
    public void selecttype()
    {
        list1=new ArrayList<String>();
        for(int i=0;i<count;i++){
            list1.add(worktypeget[i]);
        }
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> ada, View view, int i, long l) {
                String item = ada.getItemAtPosition(i).toString();
                // String selectitem=String.valueOf(spinner.getSelectedItem());
                //Log.d("error",item+selectitem);
                // Showing selected spinner item
                start("customerviewworkerforsearch",item);
                Toast.makeText(ada.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

    }
    public void selectplace()
    {
        list1=new ArrayList<String>();
        for(int i=0;i<count;i++){
            list1.add(placeget[i]);
        }
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> ada, View view, int i, long l) {
                String item = ada.getItemAtPosition(i).toString();
                // String selectitem=String.valueOf(spinner.getSelectedItem());
                //Log.d("error",item+selectitem);
                // Showing selected spinner item
                start("customerviewworkerforsearch",item);
                Toast.makeText(ada.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
    }

    public void start(String flag,String item){
        Webface upload=new Webface(this, (Inter<String>)CustomerLogined.this);

        upload.execute(flag,item);
    }

/*
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        // String selectitem=String.valueOf(spinner.getSelectedItem());
        //Log.d("error",item+selectitem);
        // Showing selected spinner item
        start("customerviewworktype");
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}

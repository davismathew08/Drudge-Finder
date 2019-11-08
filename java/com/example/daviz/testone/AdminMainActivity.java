package com.example.daviz.testone;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class AdminMainActivity extends AppCompatActivity {


    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main);
        t1=(TextView)findViewById(R.id.textView2);
        t1.setVisibility(View.VISIBLE);
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //add this line to display menu1 when the activity is loaded

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                int id = item.getItemId();
                /*switch(id)
                {
                    case R.id.account:
                        fragment=new AddWorkType();

                        Toast.makeText(AdminMainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.addworktype:
                        Toast.makeText(AdminMainActivity.this, "add",Toast.LENGTH_SHORT).show();
                    case R.id.viewworktype:
                        Toast.makeText(AdminMainActivity.this, "view",Toast.LENGTH_SHORT).show();
                    case R.id.logout:
                        adminlogout();
                        finish();
                        Toast.makeText(AdminMainActivity.this, "logout",Toast.LENGTH_SHORT).show();

                    default:
                        return true;
                }*/
                if(id==R.id.adminhome)
                {
                    Intent i=new Intent(getApplicationContext(),AdminMainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(AdminMainActivity.this, "Home",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.account)
                {
                    fragment=new ApproveWorker();
                    t1.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "approve worker",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.viewallworkers)
                {
                    fragment=new ViewAllWorkers();
                    t1.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "view worker",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.addworktype)
                {
                    fragment=new AddWorkType();
                    t1.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "add",Toast.LENGTH_SHORT).show();

                }
                else if(id==R.id.viewworktype)
                {
                    fragment=new ViewWorkType();
                    t1.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "view",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.updatedeleteworktype)
                {
                    fragment=new UpdateDeleteWorkType();
                    t1.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "updatedeleteworktype",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.viewworkerimage)
                {

                    t1.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "image view",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.logout)
                {
                   // adminlogout();
                    finish();
                    Toast.makeText(AdminMainActivity.this, "logout",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AdminMainActivity.this, "no data found",Toast.LENGTH_SHORT).show();
                }
                if(fragment!=null)
                {
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.scree_area,fragment);
                    fragmentTransaction.commit();
                }
                DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });



    }

    public void adminlogout()
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}

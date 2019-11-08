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
import android.widget.Toast;

import static com.example.daviz.testone.R.id.scree_area1;
import static com.example.daviz.testone.R.id.scree_area2;


public class WorkerLogined extends AppCompatActivity {


    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_logined);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv1 = (NavigationView)findViewById(R.id.nvw);
        nv1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                int id = item.getItemId();

                if (id == R.id.w_profile)
                {
                    fragment=new UpdateDeleteWorker();
                    Toast.makeText(WorkerLogined.this, "My Account", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.upload_photo)
                {
                    fragment=new ImageActivity();
                    Toast.makeText(WorkerLogined.this, "upload photo", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.work_assignment)
                {
                    fragment=new WorkerAssignments();
                    Toast.makeText(WorkerLogined.this, "WorkAssignments", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.w_logout) {
                   // workerlogout();
                    finish();
                    Toast.makeText(WorkerLogined.this, "logout", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(WorkerLogined.this, "wrong choice", Toast.LENGTH_SHORT).show();
                if(fragment!=null)
                {

                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    //fragmentTransaction.add(scree_area1,fragment);
                    fragmentTransaction.replace(scree_area2,fragment);
                    fragmentTransaction.commit();
                }
                DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
                drawerLayout.closeDrawer(GravityCompat.START);
                    return true;

            }

        });


    }
    public void workerlogout()
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

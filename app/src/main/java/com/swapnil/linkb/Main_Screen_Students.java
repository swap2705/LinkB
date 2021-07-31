package com.shweta.linkb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Main_Screen_Students extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView =null;
    Toolbar toolbar =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_main__screen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BlankFragment fragment=new BlankFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) Main_Screen_Students.this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id)
        {
            case R.id.Aim:
                startActivity(new Intent(Main_Screen_Students.this,Aim.class));
                break;
            case R.id.Vani:
                startActivity(new Intent(Main_Screen_Students.this,Vani.class));
                break;
            case R.id.Wisdom:
                startActivity(new Intent(Main_Screen_Students.this,Wisdom.class));
                break;
            case R.id.Bhu:
                startActivity(new Intent(Main_Screen_Students.this,Bhu.class));
                break;
            case R.id.Jeev:
                startActivity(new Intent(Main_Screen_Students.this,Jeev.class));
                break;
            case R.id.Surya:
                startActivity(new Intent(Main_Screen_Students.this,Surya.class));
                break;
            case R.id.Urja:
                startActivity(new Intent(Main_Screen_Students.this,Urja.class));
                break;
            case R.id.Vigyan:
                startActivity(new Intent(Main_Screen_Students.this,Vigyan.class));
                break;
            case R.id.Change:
                startActivity(new Intent(Main_Screen_Students.this,Change_Password.class));
                break;
            case R.id.AboutUs:
                startActivity(new Intent(Main_Screen_Students.this,About_Us.class));
                break;
            case R.id.ContactUs:
                startActivity(new Intent(Main_Screen_Students.this,Contact_Us.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
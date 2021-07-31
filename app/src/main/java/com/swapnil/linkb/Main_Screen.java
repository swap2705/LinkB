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

public class Main_Screen extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView =null;
    Toolbar toolbar =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__screen);

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
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) Main_Screen.this);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id)
        {
            case R.id.Aim:
                startActivity(new Intent(Main_Screen.this,Aim.class));
                break;
            case R.id.Vani:
                startActivity(new Intent(Main_Screen.this,Vani.class));
                break;
            case R.id.Wisdom:
                startActivity(new Intent(Main_Screen.this,Wisdom.class));
                break;
            case R.id.Bhu:
                startActivity(new Intent(Main_Screen.this,Bhu.class));
                break;
            case R.id.Jeev:
                startActivity(new Intent(Main_Screen.this,Jeev.class));
                break;
            case R.id.Surya:
                startActivity(new Intent(Main_Screen.this,Surya.class));
                break;
            case R.id.Urja:
                startActivity(new Intent(Main_Screen.this,Urja.class));
                break;
            case R.id.Vigyan:
                startActivity(new Intent(Main_Screen.this,Vigyan.class));
                break;
            case R.id.Change:
                startActivity(new Intent(Main_Screen.this,Change_Password.class));
                break;
            case R.id.AboutUs:
                startActivity(new Intent(Main_Screen.this,About_Us.class));
                break;
            case R.id.ContactUs:
                startActivity(new Intent(Main_Screen.this,Contact_Us.class));
                break;
            case R.id.AddNotice:
                startActivity(new Intent(Main_Screen.this,Add_Notice.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
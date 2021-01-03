package com.example.tudien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    Intent intentHome;
    private DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);         //xem lại
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_Home:
                intentHome = new Intent(this, HomeScreen.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intentHome);
                break;
            case R.id.nav_ENEN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EnEnFragment()).addToBackStack(null).commit();                            //addToBackStack : added fragment -> removed replaced fragment -> restored removed fragment -> added.
                break;
            case R.id.nav_ENtoVN:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ENtoVNFragment()).addToBackStack(null).commit();
                Intent intent=new Intent(this,SearchActivity.class);
                this.startActivity(intent);
                break;
            case R.id.nav_VNtoEN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new VNtoENFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_FavWord:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavWordFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_IrrVerb:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IrrVerbFragment()).addToBackStack(null).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed()
    {
        FragmentManager manager = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }


}

package com.example.tudien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.navigation.NavigationView;

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
                intentHome = new Intent(this, ActivityEnEn.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intentHome);
                break;
            case R.id.nav_ENtoVN:
                intentHome = new Intent(this, ActivityEnToVn.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intentHome);
                break;
            case R.id.nav_VNtoEN:
                intentHome = new Intent(this, ActivityVnToEn.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intentHome);
                break;
            case R.id.nav_FavWord:
                intentHome = new Intent(this, ActivityFavWord.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intentHome);
                break;
            case R.id.nav_IrrVerb:
                intentHome = new Intent(this, ActivityIrrVerb.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intentHome);
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

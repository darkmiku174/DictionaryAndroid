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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent intentHome;
    private DrawerLayout drawer;
    NavigationView navigationView;
    Button EntoVn, VntoEn, IrrVerb, EntoEn, Bookmark, History;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);         //xem lại
        EntoVn = findViewById(R.id.btn_en_vn);
        VntoEn = findViewById(R.id.btn_vn_en);
        IrrVerb = findViewById(R.id.btn_irr_verb);
        EntoEn = findViewById(R.id.btn_en_en);
        Bookmark = findViewById(R.id.btn_bookmark);
        History = findViewById(R.id.btn_history);


        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);

        EntoEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
                intent.putExtra("db", 0);
                startActivity(intent);
            }
        });

        EntoVn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
                intent.putExtra("db", 1);
                startActivity(intent);
            }
        });

        VntoEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
                intent.putExtra("db", 2);
                startActivity(intent);
            }
        });

        Bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
//                intent.putExtra("db", 3);
//                startActivity(intent);
            }
        });

        IrrVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, ActivityIrrVerb.class);
                intent.putExtra("db", 4);
                startActivity(intent);
            }
        });

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
                intentHome = new Intent(this, SearchActivity.class);
                intentHome.putExtra("db", 0);
                this.startActivity(intentHome);
                break;
            case R.id.nav_ENtoVN:
                intentHome = new Intent(this, SearchActivity.class);
                intentHome.putExtra("db", 1);
                this.startActivity(intentHome);
                break;
            case R.id.nav_VNtoEN:
                intentHome = new Intent(this, SearchActivity.class);
                intentHome.putExtra("db", 2);
                this.startActivity(intentHome);
                break;
            case R.id.nav_FavWord:
                intentHome = new Intent(this, ActivityFavWord.class);
                intentHome.putExtra("db", 3);
                this.startActivity(intentHome);
                break;
            case R.id.nav_IrrVerb:
                intentHome = new Intent(this, ActivityIrrVerb.class);
                intentHome.putExtra("db", 4);
                this.startActivity(intentHome);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}

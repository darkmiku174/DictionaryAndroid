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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    //ActionBarDrawerToggle dùng để tạo nút "burger icon"
    //ActionBarDrawerToggle cần phải khởi tạo 2 biến string trong "res->values->strings.xml" để sử sử dụng 2 giá trị này như 1 kiểu input
    // ActionBarDrawerToggle(context, drawer layout variable, toolbar variable,string 1 variable, string 2 variable)

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { //bắt sự kiện trên navigation View)

        switch (item.getItemId()) {
            case R.id.nav_ENEN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EnEnFragment()).addToBackStack(null).commit();                            //addToBackStack : added fragment -> removed replaced fragment -> restored removed fragment -> added.
                break;
            case R.id.nav_ENtoVN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ENtoVNFragment()).addToBackStack(null).commit();
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
            case R.id.nav_Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed()                                                                      //hàm tạo ra khi nhấn Back: màng hình chỉ tắt Navagation Drawer, ko tắt Activity
    {
        FragmentManager manager = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START))                                                //START = bên trái màng hình
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }


}

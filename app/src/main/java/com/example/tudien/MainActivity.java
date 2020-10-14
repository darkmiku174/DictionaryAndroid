package com.example.tudien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer; //tạo 1 biến thành viên cho Drawer Layout

    ListView listview;
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*listview = findViewById(R.id.list_view);
        for (int i = 0; i <= 100; i++)
        {
            stringArrayList.add("Item " + i);
        }

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, stringArrayList);
        //tạo adapter
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_LONG).show();
            }
        }); tạo sự kiên cho các item trong listview*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar); //Khai báo mình sử dụng Toolbar as Actionbar
        setSupportActionBar(toolbar); //set Toolbar thành Actionbar (có thể đặt tên, đặt option menu vào toolbar)
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view); //đéo hiểu cho lắm NOTE
        navigationView.setNavigationItemSelectedListener(this); //đéo hiểu cho lắm

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //ActionBarDrawerToggle dùng để tạo nút "burger icon"
        //ActionBarDrawerToggle cần phải khởi tạo 2 biến string trong "res->values->strings.xm để sử sử dụng 2 giá trị này như 1 kiểu input
        //ActionBarDrawerToggle(context, drawer layout variable, toolbar variable,string 1 variable, string 2 variable)
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //đéo hiểu cho lắm
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { //bắt sự kiện trên navigation View)
        switch (item.getItemId()) {
            case R.id.nav_ENEN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EnEnFragment()).commit();
                break;
            case R.id.nav_ENtoVN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ENtoVNFragment()).commit();
                break;
            case R.id.nav_VNtoEN:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new VNtoENFragment()).commit();
                break;
            case R.id.nav_FavWord:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavWordFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //create menu inflater
        MenuInflater menuInflater = getMenuInflater();
        //Inflater menu
        menuInflater.inflate(R.menu.menu_search, menu);
        //Tạo menu item
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public void onBackPressed() //hàm tạo ra khi nhấn Back: màng hình chỉ tắt Navagation Drawer,View chứ ko tắt Activity
    {
        if (drawer.isDrawerOpen(GravityCompat.START)) //START = bên trái màng hình
        {
            drawer.closeDrawer(GravityCompat.START);
        } else //else đóng app như bình thường
        {
            super.onBackPressed();
        }
    }

}

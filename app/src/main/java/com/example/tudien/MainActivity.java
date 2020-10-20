package com.example.tudien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout drawer;                                                                    //tạo 1 biến thành viên cho Drawer Layout
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);                                                //Khai báo mình sử dụng Toolbar as Actionbar
        setSupportActionBar(toolbar);                                                                //set Toolbar thành Actionbar (có thể đặt tên, đặt option menu vào toolbar)

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);         //xem lại
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState(); //xem lại

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
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed()                                                                      //hàm tạo ra khi nhấn Back: màng hình chỉ tắt Navagation Drawer,View chứ ko tắt Activity
    {
        FragmentManager manager = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START))                                                //START = bên trái màng hình
        {
            drawer.closeDrawer(GravityCompat.START);
        } else //else đóng app như bình thường
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        return true;
    }
}
//https://viblo.asia/p/translation-cac-khai-niem-co-ban-ve-fragment-trong-android-phan-2-GrLZDAQwlk0
//https://viblo.asia/p/fragment-va-co-che-backstack-va-su-dung-fragment-hieu-qua-nhat-p1-1Je5EMmG5nL
//developer.android.com/training/search/setup#:~:text=To%20add%20a%20SearchView%20widget,the%20title%20of%20the%20item
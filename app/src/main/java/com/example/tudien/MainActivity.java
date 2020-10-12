package com.example.tudien;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //set Toolbar thành Actionbar (có thể đặt tên, đặt option menu vào toolbar)
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
        R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() //hàm tạo ra khi nhấn Back: màng hình chỉ tắt Navagation View,Drawer chứ ko tắt Activity
    {
        if(drawer.isDrawerOpen(GravityCompat.START)) //START = bên trái màng hình
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else //Đóng app như bình thường
            {
            super.onBackPressed();
            }
    }
}
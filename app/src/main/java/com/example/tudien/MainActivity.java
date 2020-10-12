package com.example.tudien;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer; //tạo 1 biến thành viên cho Drawer Layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar); //Khai báo mình sử dụng Toolbar as Actionbar
        setSupportActionBar(toolbar); //set Toolbar thành Actionbar (có thể đặt tên, đặt option menu vào toolbar)
        drawer = findViewById(R.id.drawer_layout);
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

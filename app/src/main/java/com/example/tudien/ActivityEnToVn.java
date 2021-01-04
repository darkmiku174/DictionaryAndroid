package com.example.tudien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityEnToVn extends AppCompatActivity {

    TextView textViewTu, textViewPhienAm, textViewNghia;
    String word;
    ImageButton btnSpeak, btnFav;
    DBHelper myDbHelper;
    Cursor c = null;

    public String description;
    public String pronounce;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_to_vn);
        Bundle bundle = getIntent().getExtras();
        word = bundle.getString("word");


        myDbHelper = new DBHelper(this, 1);
        myDbHelper.openDB();


        c = myDbHelper.getMeaningEV(word);

        if (c.moveToFirst()) {
            description = c.getString(c.getColumnIndex("description"));
            pronounce = c.getString(c.getColumnIndex("pronounce"));
        }
        textViewTu = findViewById(R.id.txt_tuvung);
        textViewPhienAm = findViewById(R.id.txt_phienam);
        textViewNghia = findViewById(R.id.txt_description);

        textViewTu.setText(word);
        textViewPhienAm.setText(pronounce);
        textViewNghia.setText(description);

        btnSpeak = findViewById(R.id.btn_speak);
        btnFav=findViewById(R.id.btn_favorite);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts = new TextToSpeech(ActivityEnToVn.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(Locale.getDefault());
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("error", "This Language is not supported");
                            } else {
                                tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        } else
                            Log.e("error", "Initialization Failed!");
                    }
                });
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFav.setImageResource(R.drawable.ic_star_check);
                Toast.makeText(ActivityEnToVn.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

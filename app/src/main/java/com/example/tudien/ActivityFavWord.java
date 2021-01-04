package com.example.tudien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityFavWord extends AppCompatActivity {

    ListView listView;
    String fileName;
    ArrayList<Bookmark> arrayList;
    BookmarkApdater bookmarkApdater;
    Button btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_words);
        fileName = "BOOKMARK.txt";
        listView = findViewById(R.id.lv_favword);
        arrayList = new ArrayList<Bookmark>();
        bookmarkApdater = new BookmarkApdater(this, arrayList);
        writeToBookmark();
        listView.setAdapter(bookmarkApdater);
        bookmarkApdater.clear();
        readBookmark();
        bookmarkApdater.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityFavWord.this, "123", Toast.LENGTH_SHORT).show();
            }
        });
        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBookmark();
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void readBookmark() {
        StringBuffer buf = new StringBuffer();
        try {
            FileInputStream fis = openFileInput(fileName);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr);
                String str;
                while (((str = reader.readLine()) != null))
                    buf.append(str + "\n");
                reader.close();
                isr.close();
                fis.close();
                String tmp = buf.toString();
                String[] inp = tmp.split("[/\n]");
                if (inp.length >= 2) {
                    for (int i = 0; i < inp.length; i += 2) {
                        Bookmark bookmark = new Bookmark(inp[i], inp[i + 1]);
                        arrayList.add(bookmark);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ActivityFavWord.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteBookmark() {
        File dir = getFilesDir();
        File file = new File(dir, fileName);
        boolean deleted = file.delete();
        if (deleted) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeToBookmark() {
        File file = new File(getFilesDir(), fileName);
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write("");
            writer.close();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ActivityFavWord.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ActivityFavWord.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
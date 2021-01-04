package com.example.tudien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityIrrVerb extends AppCompatActivity {
    GridView gridView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irr_verbs);
        gridView = findViewById(R.id.gv_irrverb);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(ActivityIrrVerb.this,
                android.R.layout.simple_list_item_1,
                arrayList);

        gridView.setAdapter(arrayAdapter);
        arrayAdapter.clear();
        //readFromAsset();
        copyFile("IRR_VERB.txt");
        readFromInternal();
        arrayAdapter.notifyDataSetChanged();

       /* gridView.setBackgroundColor(Color.WHITE);
        gridView.setHorizontalSpacing(1);
        gridView.setVerticalSpacing(1);*/
    }

    private void readFromInternal() {
        StringBuffer buf = new StringBuffer();
        try {
            FileInputStream fis = openFileInput("IRR_VERB.txt");
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
                String[] inp = tmp.split("[-\n]");
                List<String> result = new ArrayList<>(Arrays.asList(inp));
                arrayList.addAll(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile(String filename) {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            String newFileName = "/data/data/" + this.getPackageName() + "/files/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }
}

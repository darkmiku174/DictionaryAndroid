package com.example.tudien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEnEn extends AppCompatActivity {

    TextView textViewTu, textViewNghia, textViewSynonyms, textViewAntonyms;
    String word;
    DBHelper myDbHelper;
    Cursor c = null;

    public String definition;
    public String synonyms;
    public String antonyms;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_en);
        Bundle bundle = getIntent().getExtras();
        word = bundle.getString("word");


        myDbHelper = new DBHelper(this, 0);
        myDbHelper.openDB();


        c = myDbHelper.getMeaningEE(word);

        if (c.moveToFirst()) {
            definition = c.getString(c.getColumnIndex("en_definition"));
            synonyms = c.getString(c.getColumnIndex("synonyms"));
            antonyms = c.getString(c.getColumnIndex("antonyms"));
        }


        textViewTu = findViewById(R.id.txt_tuvung);
        textViewNghia = findViewById(R.id.txt_description);
        textViewAntonyms = findViewById(R.id.txt_antonyms);
        textViewSynonyms = findViewById(R.id.txt_synonyms);

        textViewSynonyms.setText(synonyms);
        textViewAntonyms.setText(antonyms);
        textViewTu.setText(word);
        textViewNghia.setText(definition);
//        ImageButton btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
//
//        btnSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tts = new TextToSpeech(WordMeaningActivity.this, new TextToSpeech.OnInitListener() {
//                    @Override
//                    public void onInit(int status) {
//                        // TODO Auto-generated method stub
//                        if (status == TextToSpeech.SUCCESS) {
//                            int result = tts.setLanguage(Locale.getDefault());
//                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                                Log.e("error", "This Language is not supported");
//                            } else {
//                                tts.speak(enWord, TextToSpeech.QUEUE_FLUSH, null);
//                            }
//                        } else
//                            Log.e("error", "Initialization Failed!");
//                    }
//                });
//            }
//        });
    }
}
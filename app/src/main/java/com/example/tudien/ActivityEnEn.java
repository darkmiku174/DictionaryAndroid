package com.example.tudien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class ActivityEnEn extends AppCompatActivity {

    TextView textViewTu, textViewNghia, textViewSynonyms, textViewAntonyms;
    ImageButton btnSpeak, btnFav;
    String word;
    DBHelper myDbHelper;
    String fileName;
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

        fileName = "BOOKMARK.txt";
        myDbHelper = new DBHelper(this, 0);
        myDbHelper.openDB();


        c = myDbHelper.getMeaningEE(word);
        myDbHelper.inserHistoryEE(word);

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

        btnSpeak = findViewById(R.id.btn_speak);
        btnFav=findViewById(R.id.btn_favorite);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts = new TextToSpeech(ActivityEnEn.this, new TextToSpeech.OnInitListener() {
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
                writeToBookmark();
            }
        });
    }

    private void writeToBookmark() {
        File file = new File(getFilesDir(), fileName);
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(word + "/" + definition + "\n");
            writer.close();
            osw.close();
            fos.close();

            Toast.makeText(ActivityEnEn.this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ActivityEnEn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ActivityEnEn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
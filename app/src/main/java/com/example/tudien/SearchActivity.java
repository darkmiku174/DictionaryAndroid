package com.example.tudien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    androidx.appcompat.widget.SearchView search;
    static DBHelper databaseHelper;
    SimpleCursorAdapter simpleCursorAdapter;
    ImageButton btnSpeak;
    final int REQ_CODE_SPEECH_INPUT = 100;
    int db;
    ArrayList<History> historyList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter historyAdapter;
    RelativeLayout emptyHistory;
    Cursor cursorHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSpeak = findViewById(R.id.btn_speak);


        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search = findViewById(R.id.search_view);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setIconified(false);
            }
        });

        Bundle bundle = getIntent().getExtras();
        db = bundle.getInt("db");
        databaseHelper = new DBHelper(this, db);
        databaseHelper.openDB();
        String name;
        switch (db) {
            case 0:
                name = "en_word";
                break;
            case 1:
                name = "word";
                break;
            default:
                name = "word";
                break;
        }
        final String[] from = new String[]{name};
        final int[] to = new int[]{R.id.suggestion_text};


        simpleCursorAdapter = new SimpleCursorAdapter(SearchActivity.this,
                R.layout.suggestion_row, null, from, to, 0
        ) {
            @Override
            public void changeCursor(Cursor cursor) {
                super.changeCursor(cursor);
            }
        };


        search.setSuggestionsAdapter(simpleCursorAdapter);

        search.setOnSuggestionListener(new androidx.appcompat.widget.SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                CursorAdapter ca = search.getSuggestionsAdapter();
                Cursor cursor = ca.getCursor();
                cursor.moveToPosition(position);
                String clicked_word;
                switch (db) {
                    case 0:
                        clicked_word = cursor.getString(cursor.getColumnIndex("en_word"));
                        break;
                    case 1:
                        clicked_word = cursor.getString(cursor.getColumnIndex("word"));
                        break;
                    default:
                        clicked_word = cursor.getString(cursor.getColumnIndex("word"));
                        break;
                }
                search.setQuery(clicked_word, false);
                search.clearFocus();
                search.setFocusable(false);
                Intent intent;
                switch (db) {
                    case 0:
                        intent = new Intent(SearchActivity.this, ActivityEnEn.class);
                        break;
                    case 1:
                        intent = new Intent(SearchActivity.this, ActivityEnToVn.class);
                        break;
                    default:
                        intent = new Intent(SearchActivity.this, ActivityEnToVn.class);
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putString("word", clicked_word);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                // Your code here
                return true;
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String text = search.getQuery().toString();
                Cursor c;
                switch (db) {
                    case 0:
                        c = databaseHelper.getMeaningEE(text);
                        break;
                    case 1:
                        c = databaseHelper.getMeaningEV(text);
                        break;
                    default:
                        c = databaseHelper.getMeaningEV(text);
                        break;
                }

                if (c.getCount() == 0) {
                    search.setQuery("", false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this, R.style.MyDialogTheme);
                    builder.setTitle("Word Not Found");
                    builder.setMessage("Please search again");

                    String positiveText = getString(android.R.string.ok);
                    builder.setPositiveButton(positiveText,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // positive button logic
                                }
                            });

                    String negativeText = getString(android.R.string.cancel);
                    builder.setNegativeButton(negativeText,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    search.clearFocus();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    // display dialog
                    dialog.show();
                } else {
                    search.clearFocus();
                    search.setFocusable(false);

                    Intent intent;
                    switch (db) {
                        case 0:
                            intent = new Intent(SearchActivity.this, ActivityEnEn.class);
                            break;
                        case 1:
                            intent = new Intent(SearchActivity.this, ActivityEnToVn.class);
                            break;
                        default:
                            intent = new Intent(SearchActivity.this, ActivityEnToVn.class);
                            break;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("word", text);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                return false;
            }


            @Override
            public boolean onQueryTextChange(final String s) {
                search.setIconifiedByDefault(false);
                Cursor cursorSuggestion;
                switch (db) {
                    case 0:
                        cursorSuggestion = databaseHelper.getSuggestionsEE(s);
                        break;
                    case 1:
                        cursorSuggestion = databaseHelper.getSuggestionsEV(s);
                        break;
                    default:
                        cursorSuggestion = databaseHelper.getSuggestionsEV(s);
                        break;
                }
                simpleCursorAdapter.changeCursor(cursorSuggestion);
                return false;
            }
        });

        emptyHistory = findViewById(R.id.empty_history);
        recyclerView = findViewById(R.id.recycler_view_history);
        layoutManager = new LinearLayoutManager(SearchActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        switch (db){
            case 0:
                fetch_historyEE();
                break;
            case 1:
                break;
        }

    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search.setQuery(result.get(0), false);
                }
                break;
            }

        }
    }

    private void fetch_historyEE() {
        historyList = new ArrayList<>();
        historyAdapter = new RecyclerViewAdapterHistory(this, historyList);
        recyclerView.setAdapter(historyAdapter);

        History h;
        databaseHelper.openDB();
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
        cursorHistory = databaseHelper.getHistory();
        if (cursorHistory.moveToFirst()) {
            do {
                h = new History(cursorHistory.getString(cursorHistory.getColumnIndex("word")), cursorHistory.getString(cursorHistory.getColumnIndex("en_definition")));
                historyList.add(h);
            }
            while (cursorHistory.moveToNext());
        }

        historyAdapter.notifyDataSetChanged();

        if (historyAdapter.getItemCount() == 0) {
            emptyHistory.setVisibility(View.VISIBLE);
        } else {
            emptyHistory.setVisibility(View.GONE);
        }
        Toast.makeText(this, "oke", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fetch_historyEE();
    }
}

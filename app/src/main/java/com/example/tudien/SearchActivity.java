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
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    androidx.appcompat.widget.SearchView search;
    static DBHelper databaseHelper;
    SimpleCursorAdapter simpleCursorAdapter;
    int db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
                name="en_word";
                break;
            case 1:
                name="word";
                break;
            default:
                name="word";
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
    }
}

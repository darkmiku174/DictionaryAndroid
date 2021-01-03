package com.example.tudien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    androidx.appcompat.widget.SearchView search;
    static DBHelper databaseHelper;
    SimpleCursorAdapter simpleCursorAdapter;

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


        databaseHelper = new DBHelper(this,0);
        databaseHelper.openDB();

        final String[] from = new String[]{"word"};
        final int[] to = new int[]{R.id.suggestion_text};


        simpleCursorAdapter = new SimpleCursorAdapter(SearchActivity.this,
                R.layout.suggestion_row, null, from, to, 0
        ){
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
                String clicked_word =  cursor.getString(cursor.getColumnIndex("word"));
                search.setQuery(clicked_word,false);

                search.clearFocus();
                search.setFocusable(false);

                Intent intent = new Intent(SearchActivity.this, En2VnActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("word",clicked_word);
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
            public boolean onQueryTextSubmit(String query)
            {
                String text =  search.getQuery().toString();

                Cursor c = databaseHelper.getMeaning(text);


                if(c.getCount()==0)
                {
                    search.setQuery("",false);

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
                }
                else
                {
                    search.clearFocus();
                    search.setFocusable(false);

                    Intent intent = new Intent(SearchActivity.this, En2VnActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("word",text);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                return false;
            }


            @Override
            public boolean onQueryTextChange(final String s) {
                search.setIconifiedByDefault(false);
                Cursor cursorSuggestion=databaseHelper.getSuggestions(s);
                simpleCursorAdapter.changeCursor(cursorSuggestion);
                return false;
            }
        });
    }
}

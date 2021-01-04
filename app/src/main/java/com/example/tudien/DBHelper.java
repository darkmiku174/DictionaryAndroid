package com.example.tudien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper {
    Context context;
    String DB_NAME;

    public DBHelper(Context context, Integer check) {
        this.context = context;
        if (check == 0)
            DB_NAME = "EE.db";
        else if (check == 1)
            DB_NAME = "EV.db";
        else
            DB_NAME = "";
        copyDatabase();
    }

    private void copyDatabase() {
        File dbFile = context.getDatabasePath(DB_NAME);
        if (!dbFile.exists()) {
            try {
                InputStream is = context.getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0) {
                    os.write(buffer);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }

    public SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    public void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public Cursor getSuggestionsEV(String text) {
        Cursor c = openDB().rawQuery("SELECT rowid _id, word FROM av WHERE word LIKE '" + text + "%' LIMIT 40", null);
        return c;
    }

    public Cursor getMeaningEV(String text) {
        Cursor c = openDB().rawQuery("SELECT description, pronounce FROM av WHERE word=='" + text + "'", null);
        return c;
    }

    public Cursor getSuggestionsEE(String text) {
        Cursor c = openDB().rawQuery("SELECT _id, en_word FROM words WHERE en_word LIKE '" + text + "%' LIMIT 40", null);
        return c;
    }

    public Cursor getMeaningEE(String text) {
        Cursor c = openDB().rawQuery("SELECT en_definition, example, synonyms, antonyms FROM words WHERE en_word==UPPER('" + text + "')", null);
        return c;
    }
}

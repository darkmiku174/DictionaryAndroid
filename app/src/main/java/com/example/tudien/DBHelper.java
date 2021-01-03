package com.example.tudien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
            DB_NAME = "EV.db";
        else if (check == 1)
            DB_NAME = "EE.db";
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

    public void createTable() {
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists tblStudent(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "word TEXT," +
                "html TEXT," +
                "description TEXT," +
                "pronounce TEXT) ";
        db.execSQL(sql);
        closeDB(db);
    }

    public ArrayList<DictEV> getDictEV() {
        ArrayList<DictEV> arrayList = new ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query("av",
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String word = cursor.getString(1);
            String html = cursor.getString(2);
            String description = cursor.getString(3);
            String pronounce = cursor.getString(4);
            arrayList.add(new DictEV(id, word, html, description, pronounce));
        }
        closeDB(db);
        return arrayList;
    }

    public ArrayList<DictEE> getDictEE() {
        ArrayList<DictEE> arrayList = new ArrayList<>();
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query("entries",
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String word = cursor.getString(0);
            String type = cursor.getString(1);
            String definition = cursor.getString(2);
            arrayList.add(new DictEE(word, type, definition));
        }
        closeDB(db);
        return arrayList;
    }

    public Cursor getSuggestions(String text)
    {
        Cursor c= openDB().rawQuery("SELECT rowid _id, word FROM av WHERE word LIKE '"+text+"%' LIMIT 40",null);
        return c;
    }

    public Cursor getMeaning(String text)
    {
        Cursor c= openDB().rawQuery("SELECT description, pronounce FROM av WHERE word==UPPER('"+text+"')",null);
        return c;
    }
}

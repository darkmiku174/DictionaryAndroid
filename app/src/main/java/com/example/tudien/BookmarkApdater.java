package com.example.tudien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookmarkApdater extends ArrayAdapter<Bookmark> {
    Context context;
    ArrayList<Bookmark> arrayList;


    public BookmarkApdater(@NonNull Context context, @NonNull ArrayList<Bookmark> objects) {
        super(context, 0, objects);
        this.context = context;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.bookmark_item_layout, parent, false);

        Bookmark furniture = arrayList.get(position);

        TextView textViewWord = convertView.findViewById(R.id.en_word);
        TextView textViewDescription = convertView.findViewById(R.id.en_def);

        textViewWord.setText(furniture.get_en_word());
        textViewDescription.setText(furniture.get_def());
        return convertView;
    }
}

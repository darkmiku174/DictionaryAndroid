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

public class DictEVAdapter extends ArrayAdapter<DictEV> {
    Context context;
    TextView textViewTuVung;
    ArrayList<DictEV> arrayList;


    public DictEVAdapter(@NonNull Context context, @NonNull ArrayList<DictEV> objects) {
        super(context, 0, objects);
        this.context = context;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.suggestion_row, parent, false);

        DictEV dictEV = arrayList.get(position);

        textViewTuVung = convertView.findViewById(R.id.txt_tuvung);

        textViewTuVung.setText(dictEV.getWord());
        return convertView;
    }
}

package com.example.tudien;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ENtoVNFragment extends Fragment {

    DBHelper dbHelper;
    ArrayList<DictEV> arrayList;
    TextView txtViewTu, txtViewLoai, txtViewPhienAm, txtViewNghia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbHelper = new DBHelper(getContext(), 0);
        return inflater.inflate(R.layout.fragment_entovn, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        /*inflate menu*/
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            //bắt sự kiện click ở đây
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            getActivity().startActivity(intent);
        }
        if (id == R.id.action_record) {
            //bắt sự kiện click ở đây
            Toast.makeText(getActivity(), "work", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.action_favorite) {
            //bắt sự kiện click ở đây
            Toast.makeText(getActivity(), "bro", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper.createTable();
        txtViewTu = view.findViewById(R.id.txt_tuvung);
        txtViewLoai = view.findViewById(R.id.txt_loaitu);
        txtViewPhienAm = view.findViewById(R.id.txt_phienam);
        txtViewNghia = view.findViewById(R.id.txt_description);


        txtViewLoai.setText("123");


    }
}
//Inflater là 1 component giúp chuyển layout file (xml) thành View(Java  Code) trong Android
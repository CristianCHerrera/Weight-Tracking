package com.example.project2final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class ViewData extends AppCompatActivity {

    ArrayList<GetAndSet> arrayList;
    WeightDBHelper wdbHelper;
    DataAdapter dataAdapter;
    RecyclerView dataRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        arrayList = new ArrayList<>();
        wdbHelper = new WeightDBHelper(ViewData.this);

        arrayList = wdbHelper.readData();

        // on below line passing our array lost to our adapter class.
        dataAdapter = new DataAdapter(arrayList, ViewData.this);
        dataRV = findViewById(R.id.idRVData);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewData.this, RecyclerView.VERTICAL, false);
        dataRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        dataRV.setAdapter(dataAdapter);
    }


}
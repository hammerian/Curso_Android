package com.example.taskeando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private Spinner spnrType;

    private RecyclerView rclTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        spnrType = (Spinner) findViewById(R.id.spnr);
        rclTask = (RecyclerView) findViewById(R.id.rccl1);

        spnrType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void changeType (ArrayList<String> typeList) {
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrType.setAdapter(musicAdapter);
    }
}
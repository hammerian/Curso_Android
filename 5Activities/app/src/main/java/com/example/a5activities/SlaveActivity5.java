package com.example.a5activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class SlaveActivity5 extends AppCompatActivity {

    private ArrayList<String> musicList1, musicList2;

    private ToggleButton musicMode;
    private Spinner spnr1;
    private TextView txtView1, txtView2;
    private Boolean activated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave5);

        activated = false;
        musicMode = (ToggleButton) findViewById(R.id.tgglBtn1);
        spnr1 = (Spinner) findViewById(R.id.spnr1);
        txtView1 = (TextView) findViewById(R.id.txtView1);
        txtView2 = (TextView) findViewById(R.id.txtView2);
        musicList1 = new ArrayList<String>();
        musicList2 = new ArrayList<String>();

        // Añadir elementos a la primera lista
        musicList1.add("Mozart");
        musicList1.add("Bach");
        musicList1.add("Beethoven");
        musicList1.add("Schubert");

        // añadir elementos a la segunda lista
        musicList2.add("Vangelis");
        musicList2.add("John Williams");
        musicList2.add("Morricone");
        musicList2.add("James Horner");

        changeMusic(musicList1);
        int iCurrentSelection = spnr1.getSelectedItemPosition();

        musicMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicMode.isChecked()) {
                    changeMusic(musicList2);
                } else {
                    changeMusic(musicList1);
                }
            }
        });

        spnr1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (iCurrentSelection != position){
                    if (musicMode.isChecked()) {
                        txtView2.setText(musicList2.get(position));
                    } else {
                        txtView2.setText(musicList1.get(position));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void changeMusic (ArrayList<String> musicList) {
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, musicList);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr1.setAdapter(musicAdapter);
    }

}
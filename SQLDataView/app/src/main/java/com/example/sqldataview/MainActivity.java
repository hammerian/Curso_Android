package com.example.sqldataview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Ciudad> ciudades;

    private DBAcess bdacces;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView) findViewById(R.id.R1);
        txt2 = (TextView) findViewById(R.id.R2);
        txt3 = (TextView) findViewById(R.id.R3);

        bdacces = new DBAcess(getBaseContext());

        ciudades = bdacces.getAllCities();
        Ciudad mycity = ciudades.get(0);
        txt1.setText(mycity.getPoblation());
        txt2.setText(mycity.getCityName());
        txt3.setText(mycity.getSurface());

    }

}


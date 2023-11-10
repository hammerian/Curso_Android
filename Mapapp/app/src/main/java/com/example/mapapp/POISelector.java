package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class POISelector extends AppCompatActivity  {

    private TextView txtViewPOI;
    private TextView txtCantPOI;
    private EditText edtTxt;
    private EditText edtLong;
    private EditText edtLatt;
    private Button btnEdit;
    private Button btnSave;

    private Button btnGo;
    private Button btnSelect;

    private ArrayList<PoiUnit> listPoist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poiselector);

        txtViewPOI = (TextView) findViewById(R.id.txtViewPOI);
        txtCantPOI = (TextView) findViewById(R.id.txtCantPOI);
        edtTxt = (EditText) findViewById(R.id.edtTxt);
        edtLong = (EditText) findViewById(R.id.edtLong);
        edtLatt = (EditText) findViewById(R.id.edtLatt);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnGo = (Button) findViewById(R.id.btnGo);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        activateButtons(false);

        listPoist = new ArrayList<PoiUnit>();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(true);
                edtTxt.setText("");
                edtLatt.setText("");
                edtLong.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(false);
                String tmpDesc = edtTxt.getText().toString().trim();
                String tmpLat = edtLatt.getText().toString().trim();
                String tmpLon = edtLong.getText().toString().trim();

                PoiUnit tmpPoi = new PoiUnit(tmpDesc, tmpLat.replaceAll(",","."), tmpLon.replaceAll(",","."));

                listPoist.add(tmpPoi);

                txtCantPOI.setText(""+ listPoist.size()+ "POIs");

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiamos a la pantalla de mapas
                if (listPoist.size() > 0) {
                    Intent itn = new Intent(POISelector.this, MainActivity.class);
                    itn.putExtra("mapData", listPoist);
                    startActivity(itn);
                } else {
                    Toast.makeText(POISelector.this, "Añade un POI como mínimo", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(POISelector.this, POIList.class);
                startActivity(itn);
            }
        });
    }

    private void activateButtons (boolean act) {
        // Activar/Deactivar Campos de texto y botónes
        edtTxt.setEnabled(act);
        edtLong.setEnabled(act);
        edtLatt.setEnabled(act);
        btnEdit.setEnabled(!act);
        btnSave.setEnabled(act);
        btnGo.setEnabled(!act);
    }
}
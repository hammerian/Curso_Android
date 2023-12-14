package com.example.mapboxeando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Selector extends AppCompatActivity {

    private TextView txtListPOI;
    private TextView txtDescPOI;
    private EditText descTxt;
    private Button btnMap;
    private Button btnAddPoi;
    private Spinner spnListMap;
    private String myPlace;

    private HashMap<String, PoiUnit> poiList;
    private ArrayList<String> listPoist;

    private ArrayList<PoiUnit> newList;
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        txtListPOI = (TextView) findViewById(R.id.txtCntPOI);
        descTxt = (EditText) findViewById(R.id.edtDesc);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnAddPoi = (Button) findViewById(R.id.btnAddPoi);
        spnListMap = (Spinner) findViewById(R.id.spnrPOI);

        myContext = this.getBaseContext();
        poiList = new HashMap<>();
        newList = new ArrayList<PoiUnit>();
        listPoist = new ArrayList<String>();
        listPoist.add("--Seleccione Una Ciudad--");
        listPoist.add("Madrid");
        listPoist.add("Berlin");
        listPoist.add("Dublin");
        listPoist.add("Maracaibo");
        listPoist.add("Roma");

        PoiUnit poi1 = new PoiUnit("Madrid","-3.703367","40.416712");

        poiList.put(listPoist.get(1),poi1);

        PoiUnit poi2 = new PoiUnit("Berlin","13.377779","52.516235");
        poiList.put(listPoist.get(2),poi2);
        PoiUnit poi3 = new PoiUnit("Dublin","-6.250368","53.348068");
        poiList.put(listPoist.get(3),poi3);
        PoiUnit poi4 = new PoiUnit("Maracaibo","-71.619051","10.639665");
        poiList.put(listPoist.get(4),poi4);
        PoiUnit poi5 = new PoiUnit("Roma","12.476655","41.899339");
        poiList.put(listPoist.get(5),poi5);

        changePOIs(spnListMap);

        btnAddPoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spnListMap.getSelectedItemPosition() != 0) {
                    if (!descTxt.getText().toString().isEmpty()) {
                        PoiUnit newPoint = poiList.get(myPlace);
                        newPoint.setDescription(descTxt.getText().toString());
                        newList.add(newPoint);
                        txtListPOI.setText(""+ newList.size()+ "POIs");
                        descTxt.setText("");

                     // getLocationFromAddress(myContext,newPoint.getDescription().toString());
                        LatLng latLan = new LatLng(Double.parseDouble(newPoint.getLati()), Double.parseDouble(newPoint.getLong()));
                        getAddressFromLocation(myContext, latLan);
                    } else {
                        Toast.makeText(Selector.this, "Debes escribir un nombre para continuar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Selector.this, "Debes seleccionar una ciudad para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newList.isEmpty()) {
                    Toast.makeText(Selector.this, "Debes agregar POIs para continuar", Toast.LENGTH_SHORT).show();
                } else {
                    Intent itn = new Intent(Selector.this, MainActivity.class);
                    itn.putExtra("mapData", newList);
                    startActivity(itn);
                }
            }
        });

        spnListMap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    myPlace = listPoist.get(position);
                } else {
                    Toast.makeText(Selector.this, "Debes seleccionar una ciudad para continuar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void changePOIs(Spinner spnR) {
        ArrayAdapter<String> categAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPoist);
        categAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnR.setAdapter(categAdapter);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng latLan= null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            latLan = new LatLng(location.getLatitude(), location.getLongitude());


        } catch (IOException ex) {
            Toast.makeText(context,ex.toString() , Toast.LENGTH_SHORT).show();
            // ex.printStackTrace();
        }

        return latLan;
    }

    public String getAddressFromLocation (Context context, LatLng latLan) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        String newAdress = null;

        try {
            // May throw an IOException
            address = coder.getFromLocation(latLan.getLatitude(), latLan.getLongitude(), 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            newAdress = location.toString();
            latLan = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {
            Toast.makeText(context,ex.toString() , Toast.LENGTH_SHORT).show();
         // ex.printStackTrace();
        }

        return newAdress;
    }


}
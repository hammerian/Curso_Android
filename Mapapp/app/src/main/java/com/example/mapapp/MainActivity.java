package com.example.mapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap minimap;

    private ArrayList<PoiUnit> listPoist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        listPoist = (ArrayList<PoiUnit>) extras.getSerializable("mapData");
        SupportMapFragment mapFr = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frMap);
        mapFr.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        minimap = googleMap;

        for (PoiUnit myPoi: listPoist) {
            String poiDesc = myPoi.getDescription();
            Double poiLati = Double.parseDouble(myPoi.getLati());
            Double poiLong = Double.parseDouble(myPoi.getLong());

            addPoi(poiDesc,poiLati,poiLong);
        }

     /* LatLng casa = new LatLng(40.3409, -3.7853);
        LatLng ferrol = new LatLng(43.4924, -8.2047);
        LatLng australia = new LatLng(-12.4593, 130.8435);
        LatLng disney = new LatLng(28.3631, -81.5768);
        LatLng fiesta = new LatLng(70.6629, 23.6809);

        minimap.addMarker(new MarkerOptions().position(fiesta).title("Fiesta"));
        minimap.moveCamera(CameraUpdateFactory.newLatLng(fiesta));
        minimap.addMarker(new MarkerOptions().position(disney).title("Escursión"));
        minimap.moveCamera(CameraUpdateFactory.newLatLng(disney));
        minimap.addMarker(new MarkerOptions().position(australia).title("Vacaciones"));
        minimap.moveCamera(CameraUpdateFactory.newLatLng(australia));
        minimap.addMarker(new MarkerOptions().position(ferrol).title("Puerta"));
        minimap.moveCamera(CameraUpdateFactory.newLatLng(ferrol));
        minimap.addMarker(new MarkerOptions().position(casa).title("Mi Casa"));
        minimap.moveCamera(CameraUpdateFactory.newLatLng(casa)); */
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void addPoi (String desc, Double latitude, Double longitude){
        LatLng newPoi = new LatLng(latitude, longitude);
        minimap.addMarker(new MarkerOptions().position(newPoi).title(desc));
        minimap.moveCamera(CameraUpdateFactory.newLatLng(newPoi));
    }
}
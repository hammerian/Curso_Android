package com.example.mapboxeando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback {

    private MapView mapView;
    private MapboxMap mbMap;

    private ArrayList<PoiUnit> listPoist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        listPoist = (ArrayList<PoiUnit>) extras.getSerializable("mapData");

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        mbMap = mapboxMap;

        for (PoiUnit myPoi: listPoist) {
            String poiDesc = myPoi.getDescription();
            Double poiLati = Double.parseDouble(myPoi.getLati());
            Double poiLong = Double.parseDouble(myPoi.getLong());

            addPoi(poiDesc,poiLati,poiLong);
        }
    }

    private void addPoi (String desc, Double latitude, Double longitude){
        LatLng newPoi = new LatLng(latitude, longitude);
        getLocationFromAddress(this.getBaseContext(),desc);
        mbMap.addMarker(new MarkerOptions().position(newPoi).title(desc));
        mbMap.moveCamera(CameraUpdateFactory.newLatLng(newPoi));
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
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println(""+location.getLatitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return latLan;
    }

}
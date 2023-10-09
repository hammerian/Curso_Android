package com.example.addimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    private ImageView imgView;
    private Button btn1;
    private Spinner spn1;

    private Bitmap imagen;
    private static final int CAMERA_REQUEST_CODE = 100;
    private ArrayList<String> musicList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        imgView = (ImageView) findViewById(R.id.imgView);
        btn1 = (Button) findViewById(R.id.btn1);
        spn1 = (Spinner) findViewById(R.id.spnr1);

        musicList1 = new ArrayList<String>();
        // Añadir elementos a la primera lista
        musicList1.add("Mozart");
        musicList1.add("Bach");
        musicList1.add("Beethoven");
        musicList1.add("Schubert");

        int iCurrentSelection = spn1.getSelectedItemPosition();
        chargeMusic(musicList1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                } else {
                    openCamera();
                }
            }
        });

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (iCurrentSelection != i) {
                    txtTitle.setText(musicList1.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtTitle.setText("Selecciona una opción");
            }
        });
    }

    private void chargeMusic (ArrayList<String> musicList) {
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, musicList);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(musicAdapter);
    }

    private void openCamera () {
        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamara, 1);
    }

    private boolean hasStoragePermission(Context context) {
        int read = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        return read == PackageManager.PERMISSION_GRANTED;
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Camera permission granted.", Toast.LENGTH_LONG).show();
                // Do stuff here for Action Image Capture.
            } else {
                Toast.makeText(this, "Camera permission denied.", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try{
                Bundle extra = data.getExtras();
                imagen = (Bitmap) extra.get("data");
                imgView.setImageBitmap(imagen);
            } catch (NullPointerException npe) {
                System.out.println(npe);
            }
        }
    }
}
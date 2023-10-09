package com.example.sqldataview;

import static android.system.Os.close;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Ciudad> ciudades;

    private DBAcess bdacces;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    public static String DB_FILEPATH = "/data/data/{package_name}/databases/database.db";

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

    /**
     * Copies the database file at the specified location over the current
     * internal application database.
     * */
    public boolean importDatabase(String dbPath) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        bdacces.close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            bdacces.getWritableDatabase().close();
            return true;
        }
        return false;
    }

}


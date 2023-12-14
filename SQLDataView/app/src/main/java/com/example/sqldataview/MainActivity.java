package com.example.sqldataview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Ciudad> ciudades;

    private DBAcess bdacces;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    private SQLiteDatabase dbnew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView) findViewById(R.id.R1);
        txt2 = (TextView) findViewById(R.id.R2);
        txt3 = (TextView) findViewById(R.id.R3);

     // bdacces = new DBAcess(getBaseContext());

     // ciudades = bdacces.getAllCities();
     // Ciudad city = ciudades.get(0);
     // txt1.setText(city.getPoblation());
     // txt2.setText(city.getCityName());
     // txt3.setText(city.getSurface());

        OpenHelper openHelper = new OpenHelper(this);
        this.dbnew = openHelper.getWritableDatabase();

    }


    private static class OpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "Comic.sqlite";
        private static final int DATABASE_VERSION = 1;

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE RSS (TITLE TEXT, LINK TEXT, DESCR TEXT, PUBDATE DATE, GUID TEXT, READ TEXT, TYPE TEXT)");
            db.execSQL("CREATE TABLE PAGE (LINK TEXT, CONTENT TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}


package com.example.sqldataview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBAcess extends SQLiteOpenHelper {

    //Database name
    private static final String DB_NAME = "chinook";

    //Table name
    private static final String DB_TABLE_NAME = "albums";

    //Database version must be >= 1
    private static final int DB_VERSION = 2;

    //Columns
    private static final String POBLATION_COLUMN = "AlbumId";
    private static final String CITY_COLUMN = "Title";
    private static final String SURFACE_COLUMN = "ArtistId";

    //Context
    private Context mContext;

    public DBAcess(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mContext = context;

    }

    //Todo 2. Sobrecargamos onCreate, encargado de crear las tablas asociadas a la base de datos.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USER_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "("
                + CITY_COLUMN + " TEXT,"
                + POBLATION_COLUMN + " INTEGER,"
                + SURFACE_COLUMN + " INTEGER)";

        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log("onUpadre");
        Log("oldversion -> "+oldVersion);
        switch(oldVersion){
            case 1:
                sqLiteDatabase.execSQL("ALTER TABLE " + DB_TABLE_NAME  +" ADD COLUMN " + POBLATION_COLUMN +" INTEGER");
                Log.i("DB", "BBDD Actualizada a la versión 2");
            case 2:
                sqLiteDatabase.execSQL("ALTER TABLE " + DB_TABLE_NAME  +" ADD COLUMN " + SURFACE_COLUMN +" INTEGER");
                Log.i("DB", "BBDD Actualizada a la versión 3");
        }
    }


    public void getVersionDB(){
        Log(Integer.toString(this.getReadableDatabase().getVersion()));
    }

    //Metodo insertar
    public long insert(String city, int poblation, int superficie){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1;

        // Contenedor clave,valor -> columna, valor de entrada registro
        ContentValues values = new ContentValues();

        values.put(CITY_COLUMN, city);
        values.put(POBLATION_COLUMN, poblation);
        values.put(SURFACE_COLUMN, superficie);

        result = db.insert(DB_TABLE_NAME,null,values);

        //Se cierra la conexión de la base de datos
        db.close();

        return result;

    }
    //Método eliminar
    public void delete(String city){
        //Permiso de escritura en la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        //Eliminamos
        db.delete(DB_TABLE_NAME, "cityName=?", new String[]{city});
        //Se cierra la conexión de la base de datos
        db.close();
    }
    //Metodo actualizar
    public void update(String city, int poblation, int superficie){
        //Permiso de escritura en la base de datos
        SQLiteDatabase db = this.getWritableDatabase();

        //ContentValues para actualizar los datos
        ContentValues values = new ContentValues();
        values.put(POBLATION_COLUMN, poblation);
        values.put(SURFACE_COLUMN, superficie);
        //Actualizamos
        //Nombre tabla, valores nuevos
        db.update(DB_TABLE_NAME, values, "cityName=?",new String[]{city});
        //Se cierra la conexión de la base de datos
        db.close();
    }
    //Para cargar todas las ciudades de la base de datos
    public ArrayList<Ciudad> getAllCities(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Ciudad> resultCities = new ArrayList<>();

        String[] cols = new String[]{ POBLATION_COLUMN, CITY_COLUMN, SURFACE_COLUMN};

        //String selection = "city=? AND poblation>?"; // -> el caracter interrogación será sustituido por los valores del array 'args' en orden de aparición
        //String[] args = new String[]{"jerez", "100"};

        //Un cursor es un tipo de dato que se mueve entre los registros devueltos por una consulta de una base de datos.
        Cursor c = db.query(DB_TABLE_NAME,cols,null,null,null,null,null);

        //Todo 6.1. Movemos el iterador al primer elemento (si existe devuelve true sino false)
        if(c.moveToFirst()){
            do{
                //
                String nombreCiudad = c.getString(1);
                int poblacion = c.getInt(0);
                int superficie = c.getInt(2);
                Ciudad ciudad = new Ciudad(poblacion, nombreCiudad, superficie);
                resultCities.add(ciudad);
            }while(c.moveToNext());
        }

        return resultCities;
    }
    public void Log(String msg){
        Log.d("DB", msg);
    }

}

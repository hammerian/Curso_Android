package com.example.vistadelista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button newObject;
    private ImageView ivFotoContacto;
    private Uri selectedImage;
    private ArrayList <MyListData> newListData;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newObject = (Button) findViewById(R.id.button);
        newListData = new ArrayList<MyListData>();

        newListData.add(new MyListData( "Primero","Juan", "Saltar", R.drawable.img1));
        newListData.add(new MyListData( "Segundo","Luis", "Agacharse", R.drawable.img2));
        newListData.add(new MyListData( "Tercer", "Carlos", "Correr", R.drawable.img3));
        newListData.add(new MyListData( "Cuarto", "Domingo", "Sentadillas", R.drawable.img4));
        newListData.add(new MyListData( "Quinto", "Ramon", "Bracear", R.drawable.img5));
        newListData.add(new MyListData( "Sexto", "Vicente", "Rodar", R.drawable.img6));

        newListData.add(new MyListData( "Primero","Juan", "Saltar", R.drawable.img1));
        newListData.add(new MyListData( "Segundo","Luis", "Agacharse", R.drawable.img2));
        newListData.add(new MyListData( "Tercer","Carlos", "Correr", R.drawable.img3));
        newListData.add(new MyListData( "Cuarto","Domingo", "Sentadillas", R.drawable.img4));
        newListData.add(new MyListData( "Quinto","Ramon", "Bracear", R.drawable.img5));
        newListData.add(new MyListData( "Sexto","Vicente", "Rodar", R.drawable.img6));

        RecyclerView rcView = (RecyclerView) findViewById(R.id.rclview);

        rcView.setLayoutManager(new LinearLayoutManager(this));

        MyListAdapter adapter = new MyListAdapter(newListData);
        rcView.setAdapter(adapter);
        // rcView.setHasFixedSize(true);

        newObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonShowPopupWindowClick(view);
            }
        });

    }

    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.new_contact, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);


        EditText edtTxt11 = (EditText) popupView.findViewById(R.id.edtTxt1);
        EditText edtTxt12 = (EditText) popupView.findViewById(R.id.edtTxt2);
        EditText edtTxt13 = (EditText) popupView.findViewById(R.id.edtTxt3);
        ImageButton imgBtn = (ImageButton) popupView.findViewById(R.id.imgButton1);
        Button btn2 = popupView.findViewById(R.id.button2);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                final String[] Options = {"Cámara", "Galería", "Cancelar"};
                // Configura el titulo.
                alertDialogBuilder.setTitle("Agenda");
                // Configura el mensaje.
                // alertDialogBuilder.setMessage("Selecciona una opción de imagen");
                alertDialogBuilder.setCancelable(true);

                alertDialogBuilder.setItems(Options,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            //first option clicked, do this...
                            abrirCamara();

                        }else if(which == 1){
                            //second option clicked, do this...
                            if(hasStoragePermission(MainActivity.this)){
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                            }else{
                                ActivityCompat.requestPermissions(((AppCompatActivity) MainActivity.this), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                            }
                        }else if(which == 2){
                            dialog.cancel();
                        }else{
                            //theres an error in what was selected
                            Toast.makeText(getApplicationContext(), "Hmmm I messed up. I detected that you clicked on : " + which + "?", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialogBuilder.show();
             /* alertDialogBuilder.setPositiveButton("Cámara",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Si la respuesta es afirmativa aquí agrega tu función a realizar.

                            }
                        })
                       .setPositiveButton("Galería",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id) {
                                //Si la respuesta es afirmativa aquí agrega tu función a realizar.
                                if(hasStoragePermission(MainActivity.this)){
                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                                }else{
                                    ActivityCompat.requestPermissions(((AppCompatActivity) MainActivity.this), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                                }
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        }).create().show(); */

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp1 = edtTxt11.getText().toString().trim();
                String tmp2 = edtTxt12.getText().toString().trim();
                String tmp3 = edtTxt13.getText().toString().trim();

                if (tmp1 == null || tmp1.trim().equals("") || tmp2 == null || tmp1.trim().equals("") || tmp3 == null || tmp3.trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Campos obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    MyListData tmpUser = new MyListData(tmp1, tmp2, tmp3, R.drawable.img1);
                    newListData.add(tmpUser);
                    popupWindow.dismiss();
                    Toast.makeText(MainActivity.this, "Registro agregado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            selectedImage = data.getData();
            ivFotoContacto.setImageURI(selectedImage);


        }
    }

    private boolean hasStoragePermission(Context context) {
        int read = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        return read == PackageManager.PERMISSION_GRANTED;
    }

    private void abrirCamara() {
        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamara, 1);
    }

}
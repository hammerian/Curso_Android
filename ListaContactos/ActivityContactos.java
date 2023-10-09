package com.example.biometricthings.PruebasLoeches.ListaContactos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.biometricthings.PruebasLoeches.MyListAdapter;
import com.example.biometricthings.PruebasLoeches.MyListData;
import com.example.biometricthings.R;
import com.example.biometricthings.model.Multa;
import com.example.biometricthings.model.User;
import com.example.biometricthings.remote.APIService;
import com.example.biometricthings.remote.RetroClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityContactos extends AppCompatActivity {

    private ImageView ivFotoContacto;
    private Button btnCrearC;
    private EditText etNombreC, etTelfC, etCorreoC;
    private Button btnAniadir;
    public static final int PICK_IMAGE = 1;
    private String nombre, telf, correo;
    private Uri selectedImage;
    private ArrayList<Contact> arrContact = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contactos);
        btnAniadir = (Button) findViewById(R.id.btnAniadir);

        Contact c1 = new Contact("Daniel Sabbagh", "691399402", R.drawable.yo, "danisom1b@gmail.com", 0);
        Contact c2 = new Contact("Pepe Sanchez", "666666666", R.drawable.pepe, "pepe@gmail.com", 0);
        Contact c3 = new Contact("Sofia Perez", "655555555", R.drawable.sofia, "sofia@gmail.com", 0);
        Contact c4 = new Contact("Claudia Martinez", "633112244", R.drawable.claudia, "claudia@gmail.com", 0);
        Contact c5 = new Contact("Eustaquio Habichuela", "696696969", R.drawable.eustaquio, "eusta@gmail.com", 0);

        arrContact.add(c2);
        arrContact.add(c1);
        arrContact.add(c3);
        arrContact.add(c4);
        arrContact.add(c5);
        /*Contact[] contactList = new Contact[]{
                new Contact("Daniel Sabbagh", "691399402", R.drawable.yo, "danisom1b@gmail.com"),
                new Contact("Pepe Sanchez", "666666666", R.drawable.pepe, "pepe@gmail.com"),
                new Contact("Sofia Perez", "655555555", R.drawable.sofia, "sofia@gmail.com"),
                new Contact("Claudia Martinez", "633112244", R.drawable.claudia, "claudia@gmail.com"),
                new Contact("Eustaquio Habichuela", "696696969", R.drawable.eustaquio, "eusta@gmail.com")

        };*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvListaC);
        ContactAdapter adapter = new ContactAdapter(arrContact);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createNewDialogAceptar(view);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.ventana_popup, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                ivFotoContacto = popupView.findViewById(R.id.ivFotoContacto);
                btnCrearC = popupView.findViewById(R.id.btnCrearC);
                etNombreC = popupView.findViewById(R.id.etNombreC);
                etTelfC = popupView.findViewById(R.id.etTelfC);
                etCorreoC = popupView.findViewById(R.id.etCorreoC);

                ivFotoContacto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);


                    }
                });
                btnCrearC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        nombre = etNombreC.getText().toString().trim();
                        telf = etTelfC.getText().toString().trim();
                        correo = etCorreoC.getText().toString().trim();

                        Contact c = new Contact(nombre, telf, selectedImage, correo,1);
                        arrContact.add(c);
                        popupWindow.dismiss();




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


}
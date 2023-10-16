package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private FirebaseUser fbUser;

    private EditText edtTxt2;
    private EditText edtTxt3;
    private EditText edtTxt4;
    private EditText edtTxt6;
    private Button btn2;
    private Button btn3;

    private MyUser regUser;

    private String dataName, dataSurname, dataEmail, dataPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtTxt2 = findViewById(R.id.edtTxt2);
        edtTxt3 = findViewById(R.id.edtTxt3);
        edtTxt4 = findViewById(R.id.edtTxt4);
        edtTxt6 = findViewById(R.id.edtTxt6);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        // Deshabilita los campos de texto y botones
        activateButtons(false);

        // Método de recuperar datos de pantalla anterior (Deshabilitado)
        // Bundle extras = getIntent().getExtras();
        // regUser = (MyUser) extras.getSerializable("prfUser");

        // Activación de Firebase
        mAuth = FirebaseAuth.getInstance();
        // activa el usuario de Firebase
        fbUser = mAuth.getCurrentUser();
        // recupera de Firebase Database los datos del usuario de Firebase
        dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(fbUser.getUid());
        reloadValues();


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(true);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(false);
                String uuid = fbUser.getUid();
                dbRef.child(uuid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        MyUser modUser = snapshot.getValue(MyUser.class);
                     // modUser.setName();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Si se cancela la operación
                    }
                });

            }
        });

    }

    private void reloadValues() {
        // evento de completado de la operación de recuperar los datos del usuario
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Recupera los datos del usuario en un POJO creado por nosotros
                regUser = snapshot.getValue(MyUser.class);

                edtTxt2.setText(regUser.getName().toString());
                edtTxt3.setText(regUser.getSurname().toString());
                edtTxt4.setText(regUser.getEmail().toString());
                edtTxt6.setText(regUser.getPhone().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Si se cancela la operación
            }
        });
    }

    private void activateButtons (boolean act) {
        // Activar/Deactivar Campos de texto y botónes
        edtTxt2.setEnabled(act);
        edtTxt3.setEnabled(act);
        edtTxt4.setEnabled(act);
        edtTxt6.setEnabled(act);
        btn2.setEnabled(!act);
        btn3.setEnabled(act);
    }
}
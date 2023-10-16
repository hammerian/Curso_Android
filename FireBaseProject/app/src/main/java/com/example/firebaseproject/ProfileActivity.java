package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private Button btnModf;
    private Button btnSave;

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
        btnModf = findViewById(R.id.btnModf);
        btnSave = findViewById(R.id.btnSave);

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
        dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
        reloadValues();


        btnModf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(false);

                dataName = edtTxt2.getText().toString().toString();
                dataSurname = edtTxt3.getText().toString().toString();
                dataEmail = edtTxt4.getText().toString().toString();
                dataPhone = edtTxt6.getText().toString().toString();
                MyUser newUser = new MyUser(dataName,dataSurname,dataEmail,dataPhone);
                dbRef.child(fbUser.getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "El Usuario se ha actualizado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Ha habido un error al actualizar el Usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    private void reloadValues() {
        // evento de completado de la operación de recuperar los datos del usuario
        dbRef.child(fbUser.getUid()).addValueEventListener(new ValueEventListener() {
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
        edtTxt4.setEnabled(false);
        edtTxt6.setEnabled(act);
        btnModf.setEnabled(!act);
        btnSave.setEnabled(act);
    }
}
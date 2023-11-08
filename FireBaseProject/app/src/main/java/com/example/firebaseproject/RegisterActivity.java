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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    // Variables de Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private FirebaseUser fbUser;

    // Variables de Objetos del Activity
    private EditText edtTxt2;
    private EditText edtTxt3;
    private EditText edtTxt4;
    private EditText edtTxt5;
    private EditText edtTxt6;
    private Button btn2;

    // Variables de datos
    private MyUser regUser;
    private String dataName, dataSurname, dataEmail, dataPass, dataPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Definición de Campos de texto y Botones
        edtTxt2 = findViewById(R.id.edtTxt2);
        edtTxt3 = findViewById(R.id.edtTxt3);
        edtTxt4 = findViewById(R.id.edtTxt4);
        edtTxt5 = findViewById(R.id.edtTxt5);
        edtTxt6 = findViewById(R.id.edtTxt6);
        btn2 = findViewById(R.id.btn2);

        // Activación de Firebase
        mAuth = FirebaseAuth.getInstance();
        // Recuperación de datos para Firebase Database
        dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // guardamos los campos de texto para Login
                dataName = edtTxt2.getText().toString().trim();
                dataSurname = edtTxt3.getText().toString().trim();
                dataEmail = edtTxt4.getText().toString().trim();
                dataPass = edtTxt5.getText().toString().trim();
                dataPhone = edtTxt6.getText().toString().trim();

                // Evento de Registro en Firebase de Google
                mAuth.createUserWithEmailAndPassword(dataEmail,dataPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // activa el usuario de Firebase
                            fbUser = mAuth.getCurrentUser();
                            // consigue el id del usuario en Firebase
                            String userUuid = fbUser.getUid();
                            // Crea un usuario desde nuestro POJO
                            regUser = new MyUser(dataName, dataSurname, dataEmail, dataPhone, null);

                            // Evento de guardado del usuario recién creado en Firebase Database
                            dbRef.child(userUuid).setValue(regUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        // Regresa a la pantalla anterior de Login
                                        Intent itn = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(itn);

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "El usuario no se ha guardado", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "El registro no se ha completado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
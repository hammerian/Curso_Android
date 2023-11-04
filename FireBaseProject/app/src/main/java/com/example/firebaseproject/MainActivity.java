package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    // Variables de Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser fbUser;
    private DatabaseReference dbRef;

    // Variables de Objetos del Activity
    private EditText edtTxt1;
    private EditText edtTxt2;
    private TextView txtView2;
    private TextView txtView3;
    private Button btn1;
    // private Button btn2;
    private ImageButton imageButton2;

    // Variables de datos
    private String data1, data2;
    private MyUser logUser;
    private DataWriter dataWr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definición de Campos de texto y Botones
        edtTxt1 = findViewById(R.id.edtTxt1);
        edtTxt2 = findViewById(R.id.edtTxt2);
        txtView2 = findViewById(R.id.txtV2);
        txtView3 = findViewById(R.id.txtV3);
        btn1 = findViewById(R.id.btn1);
     // btn2 = findViewById(R.id.btn2);
        imageButton2 = findViewById(R.id.imageButton2);
        dataWr = new DataWriter(this);
        // Activación de Firebase
        mAuth = FirebaseAuth.getInstance();

        if (dataWr.sharedPreferenceExist("userId")) {
            // App already executed
            String myId = dataWr.getuserId();
            dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(myId);
            Intent itn = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(itn);
        }

        // Acción de botón para ir a Registro
        txtView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(itn);
            }
        });

        // Acción de botón para ir a recuperar contraseña
        txtView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(MainActivity.this, MainActivity2.class);
                // Si tenemos un email escrito, nos lo manda a la siguiente pantalla
                if (!edtTxt1.toString().isEmpty()) {
                    // Guarda el email del Campo de texto de email
                    itn.putExtra("userData", edtTxt1.getText().toString().trim());
                }
                startActivity(itn);
            }
        });

        // Botón de acción para realizar el registro
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // guardamos los campos de texto para Login
                data1 = edtTxt1.getText().toString().trim();
                data2 = edtTxt2.getText().toString().trim();

                if (data1.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campos obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    // Evento de Login en Firebase de Google
                    mAuth.signInWithEmailAndPassword(data1, data2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // activa el usuario de Firebase
                                fbUser = mAuth.getCurrentUser();

                                if (fbUser != null) {
                                    // recupera de Firebase Database los datos del usuario de Firebase
                                    dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(fbUser.getUid());

                                    // evento de completado de la operación de recuperar los datos del usuario
                                    dbRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                          if (dbRef!=null) {
                                                // Recupera los datos del usuario en un POJO creado por nosotros
                                                logUser = snapshot.getValue(MyUser.class);
                                                String name = logUser.getName();
                                                dataWr.setuserId(fbUser.getUid().toString());
                                                Toast.makeText(MainActivity.this, "El Login se ha completado " + name, Toast.LENGTH_SHORT).show();
                                                //
                                                Intent itn = new Intent(MainActivity.this, ProfileActivity.class);
                                                // itn.putExtra("prfUser", logUser); // Envío de datos a otra pantalla (Deshabilitado)
                                                startActivity(itn);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            // Si se cancela la operación
                                        }
                                    });
                                }

                            } else {
                                // En caso de que nos de error el Login
                                Toast.makeText(MainActivity.this, "El Login no se ha completado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        // Acción de botón para cambiar el campo de contraseña de visible a oculto
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // En caso de que tengamos oculto el campo, nos muestra el campo y cambia el icono
                if (edtTxt2.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                 // imageButton2.setText("O");
                    // Cambiar icono
                    int id = getResources().getIdentifier("com.example.firebaseproject:drawable/" + "baseline_key_24", null, null);
                    imageButton2.setImageResource(id);
                    // Mostrar campo
                    edtTxt2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {  // En caso de que tengamos visible el campo, nos oculta el campo y cambia el icono
                 // imageButton2.setText("Ø");
                    // Cambiar icono
                    int id = getResources().getIdentifier("com.example.firebaseproject:drawable/" + "baseline_key_off_24", null, null);
                    imageButton2.setImageResource(id);
                    // Ocultar campo
                    edtTxt2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); // TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                }
            }
        });

    }
}
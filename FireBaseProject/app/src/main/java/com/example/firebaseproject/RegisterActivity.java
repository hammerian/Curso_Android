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

    private FirebaseAuth mAuth;
    private EditText edtTxt3;
    private EditText edtTxt4;
    private EditText edtTxt5;

    private MyUser regUser;

    private DatabaseReference dbRef;

    private FirebaseUser fbUser;

    private Button btn2;

    private String data1, data2, data3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtTxt3 = findViewById(R.id.edtTxt3);
        edtTxt4 = findViewById(R.id.edtTxt4);
        edtTxt5 = findViewById(R.id.edtTxt5);
        btn2 = findViewById(R.id.btn2);

        mAuth = FirebaseAuth.getInstance();

        dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data1 = edtTxt3.getText().toString().trim();
                data2 = edtTxt4.getText().toString().trim();
                data3 = edtTxt5.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(data2,data3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            fbUser = mAuth.getCurrentUser();
                            String userUuid = fbUser.getUid();

                            regUser = new MyUser(data1, data2);

                            dbRef.child(userUuid).setValue(regUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "El registro no se ha completado", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            Intent itn = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(itn);
                        } else {
                            Toast.makeText(RegisterActivity.this, "El registro no se ha completado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    String frgtMail;
    EditText edtPass;
    Button btnPass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPass = (Button) findViewById(R.id.btnPass);
        edtPass = (EditText) findViewById(R.id.txtViewPass);

        Bundle extras = getIntent().getExtras();
        frgtMail = (String) extras.getSerializable("userData");
        edtPass.setText(frgtMail);

        mAuth = FirebaseAuth.getInstance();


        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(frgtMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                        } else {
                            Toast.makeText(MainActivity2.this, "La recuperación de contraseña no se ha completado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText edtTxt1;
    private EditText edtTxt2;

    private TextView txtView2;

    private String data1, data2;

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxt1 = findViewById(R.id.edtTxt1);
        edtTxt2 = findViewById(R.id.edtTxt2);
        txtView2 = findViewById(R.id.txtV2);
        btn1 = findViewById(R.id.btn1);

        mAuth = FirebaseAuth.getInstance();

        txtView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(itn);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data1 = edtTxt1.getText().toString().trim();
                data2 = edtTxt2.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(data1,data2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "El Login se ha completado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "El Login no se ha completado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
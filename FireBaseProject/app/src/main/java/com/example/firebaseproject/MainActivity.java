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

    private FirebaseAuth mAuth;
    private FirebaseUser fbUser;

    private DatabaseReference dbRef;

    private EditText edtTxt1;
    private EditText edtTxt2;

    private TextView txtView2;
    private TextView txtView3;

    private String data1, data2;
    private MyUser logUser;

    private Button btn1;
    // private Button btn2;
    private ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxt1 = findViewById(R.id.edtTxt1);
        edtTxt2 = findViewById(R.id.edtTxt2);
        txtView2 = findViewById(R.id.txtV2);
        txtView3 = findViewById(R.id.txtV3);
        btn1 = findViewById(R.id.btn1);
     // btn2 = findViewById(R.id.btn2);
        imageButton2 = findViewById(R.id.imageButton2);

        mAuth = FirebaseAuth.getInstance();

        txtView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(itn);
            }
        });

        txtView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(MainActivity.this, MainActivity2.class);
                if (!edtTxt1.toString().isEmpty()) {
                    itn.putExtra("userData", edtTxt1.getText().toString().trim());
                }
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

                            fbUser = mAuth.getCurrentUser();

                            dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(fbUser.getUid());

                            dbRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    logUser = snapshot.getValue(MyUser.class);
                                    String name = logUser.getName();

                                    Toast.makeText(MainActivity.this, "El Login se ha completado "+name, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        } else {
                            Toast.makeText(MainActivity.this, "El Login no se ha completado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTxt2.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                 // imageButton2.setText("O");
                    int id = getResources().getIdentifier("com.example.firebaseproject:drawable/" + "baseline_key_24", null, null);
                    imageButton2.setImageResource(id);
                    edtTxt2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                 // imageButton2.setText("Ø");
                    int id = getResources().getIdentifier("com.example.firebaseproject:drawable/" + "baseline_key_off_24", null, null);
                    imageButton2.setImageResource(id);
                    edtTxt2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); // TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                }
            }
        });

    }
}
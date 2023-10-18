package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private FirebaseUser fbUser;

    private Button btnDel;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        btnDel = (Button) findViewById(R.id.btnDelPrfl);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        String userUUid = fbUser.getUid();
        dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios");

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog(userUUid);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Intent itn = new Intent(ConfigActivity.this, MainActivity.class);
                startActivity(itn);
                finish();
            }
        });
    }

    private void openDialog (String userUUid) {
        AlertDialog.Builder alrtDlgBldr = new AlertDialog.Builder(ConfigActivity.this);

        alrtDlgBldr.setTitle("¡PRECAUCIÓN!");
        alrtDlgBldr.setMessage("¿Estás seguro que quieres quedarte sin dispositivo?");
        alrtDlgBldr.setCancelable(false);

        alrtDlgBldr.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                fbUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            dbRef.child((userUUid)).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent itn = new Intent(ConfigActivity.this, MainActivity.class);
                                        itn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(itn);
                                        finishAndRemoveTask();
                                    } else {
                                        Toast.makeText(ConfigActivity.this, "No se ha borrado el usuario de la base de datos.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(ConfigActivity.this, "No se ha borrado el usuario del login.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        alrtDlgBldr.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alrt = alrtDlgBldr.create();
        alrt.show();
    }
}
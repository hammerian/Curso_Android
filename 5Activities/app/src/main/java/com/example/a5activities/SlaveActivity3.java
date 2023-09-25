package com.example.a5activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlaveActivity3 extends AppCompatActivity {

    private Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave3);

        button7 = (Button) findViewById(R.id.button7);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /* AlertDialog.Builder alrtDlgBldr = new AlertDialog.Builder(SlaveActivity3.this);

                alrtDlgBldr.setTitle("¡PRECAUCIÓN!");
                alrtDlgBldr.setMessage("¿Estás seguro que quieres quedarte sin dispositivo?");
                alrtDlgBldr.setCancelable(false);

                alrtDlgBldr.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent itn = new Intent(SlaveActivity3.this, Confirmation.class);
                        startActivity(itn);
                    }
                });

                alrtDlgBldr.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alrt = alrtDlgBldr.create();
                alrt.show(); */

             /* CustomDialogClass cdd=new CustomDialogClass(SlaveActivity3.this);
                cdd.show(); */




            }

        });
    }
}
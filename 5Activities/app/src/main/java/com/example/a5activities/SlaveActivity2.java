package com.example.a5activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SlaveActivity2 extends AppCompatActivity {

    private String userName, userSurname, direction;
    private TextView edtTxtName;
    private TextView edtTxtSurame;
    private Button btnReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave2);

        edtTxtName = (TextView) findViewById(R.id.txtView1);
        edtTxtSurame = (TextView) findViewById(R.id.txtView2);
        btnReservation = (Button) findViewById(R.id.btnReservation);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            userName = extras.getString("name");
            userSurname = extras.getString("surn");
            direction = extras.getString("direction");
            if (extras.getString("mode").toString().equals("4")) {
                btnReservation.setVisibility(View.VISIBLE);;
            }
        }

        edtTxtName.setText(userName);
        edtTxtSurame.setText(userSurname);

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("dinerMeals",userName);
                editor.putString("dinerPrice",userSurname);
                editor.putString("direction", direction);
                editor.apply();

                Intent itn = new Intent(SlaveActivity2.this, PreferencesActivity1.class);
                startActivity(itn);
            }

        });

    }
}
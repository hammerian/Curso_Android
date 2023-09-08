package com.example.a5activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class SlaveActivity2 extends AppCompatActivity {

    private String userName, userSurname;
    private TextView edtTxtName;
    private TextView edtTxtSurame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave2);

        edtTxtName = (TextView) findViewById(R.id.txtView1);
        edtTxtSurame = (TextView) findViewById(R.id.txtView2);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            userName = extras.getString("name");
            userSurname = extras.getString("surn");
        }
        edtTxtName.setText(userName);
        edtTxtSurame.setText(userSurname);

    }
}
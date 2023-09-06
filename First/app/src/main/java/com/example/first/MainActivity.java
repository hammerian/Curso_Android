package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnInit;

    private EditText edTxtInit1, edTxtInit2;

    private TextView txtViewInit;

    private String hintEmail = "myname@myemail.com";
    private String hintPassword = "654321";

    private String readEmail;
    private String readPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInit = (Button) findViewById(R.id.btnInit);
        edTxtInit1 = (EditText) findViewById(R.id.edTxtInit1);
        edTxtInit2 = (EditText) findViewById(R.id.edTxtInit2);
        txtViewInit = (TextView) findViewById(R.id.txtViewInit);

        //View.onclickListener()
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readEmail = edTxtInit1.getText().toString().trim().toLowerCase();
                readPassword = edTxtInit2.getText().toString().trim().toLowerCase();

                if (readEmail.equals(hintEmail) && readPassword.equals(hintPassword)) {

                } else {

                }
            }
        });
    }
}
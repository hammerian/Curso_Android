package com.example.a5activities;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SlaveActivity1 extends AppCompatActivity {

  //public NewUser myUser(){};
    private EditText edtTxtEmail;
    private EditText edtTxtName;
    private EditText edtTxtSurame;
    private EditText edtTxtDNI;
    private EditText edtTxtPhone;
    private EditText edtTxtPassword;
    private EditText edtTxtReassword;
    private Button btnRegister;

    private String userName, userSurname, userEmail, userDni, userPassword, userReassword, tmpPhone;
    private int userPhone;

    private RadioButton rdButton1, rdButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave1);

        edtTxtEmail = (EditText) findViewById(R.id.edtTxtEmail);
        edtTxtName = (EditText) findViewById(R.id.edtTxtName);
        edtTxtSurame = (EditText) findViewById(R.id.edtTxtSurame);
        edtTxtDNI = (EditText) findViewById(R.id.edtTxtDNI);
        edtTxtPhone = (EditText) findViewById(R.id.edtTxtPhone);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
        edtTxtReassword = (EditText) findViewById(R.id.edtTxtReassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edtTxtName.getText().toString().trim();
                userSurname = edtTxtSurame.getText().toString().trim();
                userEmail = edtTxtEmail.getText().toString().trim();
                userDni = edtTxtDNI.getText().toString().trim();
                userPassword = edtTxtPassword.getText().toString().trim();
                userReassword = edtTxtReassword.getText().toString().trim();
                if (edtTxtPhone.getText().length() == 0) {
                    tmpPhone ="0";
                } else {
                    tmpPhone = edtTxtPhone.getText().toString().trim();
                }
                userPhone = new Integer(tmpPhone);
                boolean valid = true;
                if (userEmail.isEmpty()){
                    Toast.makeText(SlaveActivity1.this, "El campo email es obligatorio", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (userDni.isEmpty()){
                    Toast.makeText(SlaveActivity1.this, "El campo DNI es obligatorio", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (userDni.length() < 9) {
                    Toast.makeText(SlaveActivity1.this, "El campo DNI es de 9 caracteres mínimo", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (userPassword.isEmpty()){
                    Toast.makeText(SlaveActivity1.this, "El campo Contraseña es obligatorio", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (userReassword.isEmpty()){
                    Toast.makeText(SlaveActivity1.this, "El campo Repetir contraseña es obligatorio", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (userPassword.length() < 6) {
                    Toast.makeText(SlaveActivity1.this, "El campo Contraseña es de 6 caracteres mínimo", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (userReassword.length() < 6) {
                    Toast.makeText(SlaveActivity1.this, "El campo Repetir Contraseña es de 6 caracteres mínimo", Toast.LENGTH_SHORT).show();
                    valid = false;
                }
                if (!(userPassword.equals(userReassword))) {
                    Toast.makeText(SlaveActivity1.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if (valid) {
                    Intent itn = new Intent(SlaveActivity1.this, SlaveActivity2.class);
                    itn.putExtra("name", userName);
                    itn.putExtra("surn", userSurname);
                    startActivity(itn);
                }

             // myUser = new NewUser();
            }
        });

    }
}
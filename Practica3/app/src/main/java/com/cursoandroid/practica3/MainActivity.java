package com.cursoandroid.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private NewUser myUser = new NewUser();

    private EditText edtTxtEmail;
    private EditText edtTxtPassword;
    private TextView txtRegister;

    private Button btnLogin;

    private String userEmail, userPassword;
    private String usereml = "victor@mota.es", userpwd = "87654321";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxtEmail = (EditText) findViewById(R.id.edtTxtEmail);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
        txtRegister = (TextView) findViewById(R.id.TxtRegister);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            myUser = (NewUser) extras.getSerializable("userData");
            edtTxtEmail.setText(myUser.getUserEmail());
            usereml = myUser.getUserEmail();
            userpwd = myUser.getUserPassword();
        } else {
            usereml = "victor@mota.es";
            userpwd = "87654321";
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = edtTxtEmail.getText().toString().trim();
                userPassword = edtTxtPassword.getText().toString().trim();
                if (validateLogin()) {
                    Intent itn = new Intent(MainActivity.this, UserProfile.class);
                    startActivity(itn);
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(MainActivity.this, RegActivity.class);
                startActivity(itn);
            }
        });

    }

    private Boolean validateLogin (){

     // https://stackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext
        String emailPattern = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
     // String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

        if (userEmail.isEmpty()) {
            Toast.makeText(MainActivity.this, "El campo Email es obligatorio", Toast.LENGTH_SHORT).show();
        } else if (userEmail.matches(emailPattern)) { // TODO: No funciona ninguno de los métodos
     // https://duckduckgo.com/?q=android+validate+email+field&t=ftsa&atb=v238-1&ia=web
     // } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast.makeText(MainActivity.this, "El texto introducido no es un Email válido", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length() < 8) {
            Toast.makeText(MainActivity.this, "El campo Contraseña es obligatorio y mínimo de 8 caracteres", Toast.LENGTH_SHORT).show();
        } else if (!userEmail.equals(usereml) | !userPassword.equals(userpwd)) {
            Toast.makeText(MainActivity.this, "El usuario introducido no es correcto", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }

        return false;
    }
}
package com.cursoandroid.practica3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    private NewUser myUser = new NewUser();

    private EditText edtTxtEmail;
    private EditText edtTxtName;
    private EditText edtTxtSurame;
    private EditText edtTxtDNI;
    private EditText edtTxtPhone;
    private EditText edtTxtPassword;
    private EditText edtTxtRepassword;
    private Button btnRegister;

    private String userEmail, userPassword, userRepassword, userName, userSurname, userDNI, userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        edtTxtEmail = (EditText) findViewById(R.id.edtTxtEmail);
        edtTxtName = (EditText) findViewById(R.id.edtTxtName);
        edtTxtSurame = (EditText) findViewById(R.id.edtTxtSurame);
        edtTxtDNI = (EditText) findViewById(R.id.edtTxtDNI);
        edtTxtPhone = (EditText) findViewById(R.id.edtTxtPhone);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
        edtTxtRepassword = (EditText) findViewById(R.id.edtTxtReassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateRegister()) {
                    Intent itn = new Intent(RegActivity.this, MainActivity.class);
                    itn.putExtra("userData", myUser);
                    startActivity(itn);
                }

            }
        });

    }

    private Boolean validateRegister () {
        userEmail = edtTxtEmail.getText().toString().trim();
        userName = edtTxtName.getText().toString().trim();
        userSurname = edtTxtSurame.getText().toString().trim();
        userPassword = edtTxtPassword.getText().toString().trim();
        userRepassword = edtTxtRepassword.getText().toString().trim();
        userDNI = edtTxtDNI.getText().toString().trim();
        userPhone = edtTxtPhone.getText().toString().trim();

        // https://stackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext
        String emailPattern = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        if (userEmail.isEmpty()) {
            Toast.makeText(RegActivity.this, "El campo Email es obligatorio", Toast.LENGTH_SHORT).show();
        } else if (userEmail.matches(emailPattern)) {
            Toast.makeText(RegActivity.this, "El texto introducido no es un Email válido", Toast.LENGTH_SHORT).show();
        } else if (userName.isEmpty()) {
            Toast.makeText(RegActivity.this, "El campo Nombre es obligatorio", Toast.LENGTH_SHORT).show();
        } else if (userSurname.isEmpty()) {
            Toast.makeText(RegActivity.this, "El campo Apellido es obligatorio", Toast.LENGTH_SHORT).show();
        } else if (userDNI.length() != 9) {
            Toast.makeText(RegActivity.this, "El campo DNI es obligatorio y de 9 cifras", Toast.LENGTH_SHORT).show();
        } else if (!checkDNI(userDNI)) {
            Toast.makeText(RegActivity.this, "El campo DNI no es válido", Toast.LENGTH_SHORT).show();
        } else if (userPhone.length() != 9) {
            Toast.makeText(RegActivity.this, "El campo Teléfono es obligatorio y de 9 cifras", Toast.LENGTH_SHORT).show();
        } else if (!checkPhone(userPhone)) { // El teclado no permite introducir ningún caracter no numérico.
            Toast.makeText(RegActivity.this, "El campo Teléfono no es válido", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length() < 8) {
            Toast.makeText(RegActivity.this, "El campo Contraseña es obligatorio y mínimo de 8 caracteres", Toast.LENGTH_SHORT).show();
        } else if (userRepassword.length() < 8) {
            Toast.makeText(RegActivity.this, "El campo Repetir Contraseña es obligatorio y mínimo de 8 caracteres", Toast.LENGTH_SHORT).show();
        } else if (!(userPassword.equals(userRepassword))) {
            Toast.makeText(RegActivity.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
        } else {
            myUser.setUserEmail(userEmail);
            myUser.setUserName(userName);
            myUser.setUserSurname(userSurname);
            myUser.setDni(userDNI);
            myUser.setTelephone(new Integer(userPhone));
            myUser.setUserPassword(userPassword);
            return true;
        }
        return false;
    }

    private static boolean checkDNI (@NonNull String dniValue) {
        String firstChar = String.valueOf(dniValue.charAt(0)).toUpperCase();
        if (!isNumeric(firstChar)) { // Comprobación NIE
            if (!"X".contentEquals(firstChar) && !"Y".contentEquals(firstChar) && !"Z".contentEquals(firstChar)) {
                return false;
            }
        }
        for (int i = 1; i < dniValue.length()-1; i++) {
            if (!isNumeric(String.valueOf(dniValue.charAt(i)))){
                return false;
            }
        }
        String last = String.valueOf(dniValue.charAt(dniValue.length()-1));
        if (isNumeric(last)) { // Comprobación NIF
            return false;
        }
        return true;
    }

    private static boolean checkPhone (@NonNull String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!isNumeric(String.valueOf(phoneNumber.charAt(i)))){
                return false;
            }
        }
        return true;
    }

    // https://es.stackoverflow.com/questions/325876/comprobar-si-un-car%C3%A1cter-de-un-string-es-un-n%C3%BAmero
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false; //Error no es numerico
        }
        return true; //Es numerico
    }

}
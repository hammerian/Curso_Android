package com.example.a5activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class PreferencesActivity1 extends AppCompatActivity {
    private String txtPrice, txtProduct;
    private TextView edtTxtProduct;
    private TextView edtTxtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences1);

        edtTxtProduct = (TextView) findViewById(R.id.txtView2);
        edtTxtPrice = (TextView) findViewById(R.id.txtView3);

        SharedPreferences prfs = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        String dinerMeals = prfs.getString("dinerMeals", "");
        String dinerPrice = prfs.getString("dinerPrice", "");
        String direction = prfs.getString("direction", "");

        edtTxtProduct.setText(dinerMeals + " " + dinerPrice);
        edtTxtPrice.setText(direction);
    }
;
}
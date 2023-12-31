package com.example.a5activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SlaveActivity4 extends AppCompatActivity {

    private Button btnBillingr, btnReservation;
    private Switch swKnife;
    private EditText txtView3;
    private CheckBox chckBox1, chckBox2;
    private RadioGroup rdPizza, rdBurguer;
    private RadioButton rdButton1, rdButton2, rdButton3, rdButton4, rdButton5, rdButton6;

    //SharedPreferences prefs = this.getSharedPreferences( "com.example.app", Context.MODE_PRIVATE);
    private String dinerPrice, dinerMeals, direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave4);

        btnBillingr = (Button) findViewById(R.id.btnBill);
        btnReservation = (Button) findViewById(R.id.btnReservation);
        txtView3 = (EditText) findViewById(R.id.txtView3);
        swKnife = (Switch) findViewById(R.id.switch1);
        chckBox1 = (CheckBox) findViewById(R.id.chckBox1);
        chckBox2 = (CheckBox) findViewById(R.id.chckBox2);
        rdPizza = (RadioGroup) findViewById(R.id.rdPizza);
        rdBurguer = (RadioGroup) findViewById(R.id.rdBurguer);
        rdButton1 = (RadioButton) findViewById(R.id.rdButton1);
        rdButton2 = (RadioButton) findViewById(R.id.rdButton2);
        rdButton3 = (RadioButton) findViewById(R.id.rdButton3);
        rdButton4 = (RadioButton) findViewById(R.id.rdButton4);
        rdButton5 = (RadioButton) findViewById(R.id.rdButton5);
        rdButton6 = (RadioButton) findViewById(R.id.rdButton6);


        chckBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chckBox1.isChecked()) {
                    chckBox2.setChecked(false);
                    rdButton1.setEnabled(true);
                    rdButton2.setEnabled(true);
                    rdButton3.setEnabled(true);
                    rdButton4.setEnabled(false);
                    rdButton5.setEnabled(false);
                    rdButton6.setEnabled(false);
                    rdButton4.setChecked(false);
                    rdButton5.setChecked(false);
                    rdButton6.setChecked(false);
                } else {
                    rdButton1.setEnabled(false);
                    rdButton2.setEnabled(false);
                    rdButton3.setEnabled(false);
                }
            }
        });

        chckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (chckBox2.isChecked()) {
                    chckBox1.setChecked(false);
                    rdButton4.setEnabled(true);
                    rdButton5.setEnabled(true);
                    rdButton6.setEnabled(true);
                    rdButton1.setEnabled(false);
                    rdButton2.setEnabled(false);
                    rdButton3.setEnabled(false);
                    rdButton1.setChecked(false);
                    rdButton2.setChecked(false);
                    rdButton3.setChecked(false);
                } else {
                    rdButton4.setEnabled(false);
                    rdButton5.setEnabled(false);
                    rdButton6.setEnabled(false);
                }
            }
        });

     /* chckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    chckBox1.setChecked(false);
                    System.out.println("Checked");
                } else {
                    System.out.println("Un-Checked");
                }
            }
        });*/

        btnBillingr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!chckBox1.isChecked()) && (!chckBox2.isChecked())) {
                    Toast.makeText(SlaveActivity4.this, "¡Selecciona un menú!", Toast.LENGTH_SHORT).show();
                } else if (txtView3.getText().toString().isEmpty()) {
                    Toast.makeText(SlaveActivity4.this, "¡Indica una dirección!", Toast.LENGTH_SHORT).show();
                } else {
                    calculateTotal();

                    Intent itn = new Intent(SlaveActivity4.this, SlaveActivity2.class);
                    itn.putExtra("name", dinerMeals);
                    itn.putExtra("surn", dinerPrice);
                    itn.putExtra("direction", direction);
                    itn.putExtra("mode", "4");
                    startActivity(itn);
                }
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!chckBox1.isChecked()) && (!chckBox2.isChecked())) {
                    Toast.makeText(SlaveActivity4.this, "¡Selecciona un menú!", Toast.LENGTH_SHORT).show();
                } else if (txtView3.getText().toString().isEmpty()) {
                    Toast.makeText(SlaveActivity4.this, "¡Indica una dirección!", Toast.LENGTH_SHORT).show();
                } else {
                    calculateTotal();

                    saveData();

                    Intent itn = new Intent(SlaveActivity4.this, PreferencesActivity1.class);
                    startActivity(itn);
                }
            }

        });
    }

    private void saveData() {
        SharedPreferences preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("dinerMeals",dinerMeals);
        editor.putString("dinerPrice",dinerPrice);
        editor.putString("direction", direction);
        editor.apply();
    }

    private void calculateTotal() {
        int price = 0;
        if (swKnife.isChecked()) {
            price = 1;
        }

        if (chckBox1.isChecked()) {
            dinerMeals = getString(R.string.pizza);
            price = price + 20;
            if (rdButton1.isChecked()) {
                dinerMeals = "Pizza Mediterranea";
                price = price + 2;
            } else if (rdButton2.isChecked()) {
                dinerMeals = "Pizza 3 Quesos";
                price = price + 5;
            } else if (rdButton3.isChecked()) {
                dinerMeals = "Pizza Embutidos";
                price = price + 8;
            }
        } else if (chckBox2.isChecked()) {
            dinerMeals = getString(R.string.hamburguesa);
            price = price + 15;
            if (rdButton4.isChecked()) {
                dinerMeals = "Hamburguesa Barbacoa";
                price = price + 2;
            } else if (rdButton5.isChecked()) {
                dinerMeals = "Hamburguesa de Pollo";
                price = price + 4;
            } else if (rdButton6.isChecked()) {
                dinerMeals = "Hamburguesa Vegana";
                price = price + 7;
            }
        }
        dinerPrice = price + "€";
        direction = txtView3.getText().toString().trim();
    }
}
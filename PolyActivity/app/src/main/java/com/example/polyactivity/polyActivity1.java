package com.example.polyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Date;
import java.util.Locale;

public class polyActivity1 extends AppCompatActivity {

    private TextClock digitalClock;

    private TextView txtTitleM;

    private TextView txtTitleH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_poly1);

        digitalClock = findViewById(R.id.textClock);
        txtTitleH = findViewById(R.id.txtTitleH);
        digitalClock.setTimeZone("Europe/Madrid");
        // digitalClock.setTimeZone("America/Caracas");
        digitalClock.setFormat12Hour("EEE, MMM dd. HH mm");
        digitalClock.setFormat24Hour("EEE, MMM dd. HH mm");

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd. HH mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        txtTitleH.setText(currentDateandTime);

    }
}
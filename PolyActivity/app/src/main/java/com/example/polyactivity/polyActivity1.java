package com.example.polyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class polyActivity1 extends AppCompatActivity {

    private TextClock digitalClock;

    private TextView txtTitleM;

    private TextView txtTitleH;
    private java.time.format.DateTimeFormatter DateTimeFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_poly1);

        Locale esLocale = new Locale("es", "ES");//para trabajar en español
        digitalClock = findViewById(R.id.textClock);
        txtTitleH = findViewById(R.id.txtTitleH);
        digitalClock.setTimeZone("Europe/Madrid");
        digitalClock.setTextLocale(esLocale);
        // digitalClock.setTimeZone("America/Caracas");
        digitalClock.setFormat12Hour("EEE, MMM dd. HH mm");
        digitalClock.setFormat24Hour("EEE, MMM dd. HH mm");

        SimpleDateFormat sdf2 = new SimpleDateFormat("EEE, MMM dd. HH mm", esLocale);
        sdf2.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

        String currentDateandTime = sdf2.format(new Date());
        txtTitleH.setText(currentDateandTime);

    }
}
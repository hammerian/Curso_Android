package com.example.pdfreload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView txtPincha;
    private Uri newUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPincha = findViewById(R.id.txtPincha);

        txtPincha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirPDF(R.raw.hojapersonaje);

            }
        });

    }

    private void abrirPDF(int rawPdf) {
        try {
            InputStream intStr = getResources().openRawResource(rawPdf);
            byte[] buffer = new byte[intStr.available()];

            intStr.read(buffer);

            String pdfFileName = "";
            File tempFile = new File(getFilesDir(), pdfFileName);
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            outputStream.write(buffer);
            outputStream.close();

            newUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", tempFile);
            Intent i = new Intent(Intent.ACTION_VIEW);

            try {
                startActivity(i);
            } catch (ActivityNotFoundException e){

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
package com.example.vistadelista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button newObject;
    private ArrayList <MyListData> newListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newObject = (Button) findViewById(R.id.button);
        newListData = new ArrayList<MyListData>();

        newListData.add(new MyListData( "Primero","Juan", "Saltar", R.drawable.img1));
        newListData.add(new MyListData( "Segundo","Luis", "Agacharse", R.drawable.img2));
        newListData.add(new MyListData( "Tercer", "Carlos", "Correr", R.drawable.img3));
        newListData.add(new MyListData( "Cuarto", "Domingo", "Sentadillas", R.drawable.img4));
        newListData.add(new MyListData( "Quinto", "Ramon", "Bracear", R.drawable.img5));
        newListData.add(new MyListData( "Sexto", "Vicente", "Rodar", R.drawable.img6));

        newListData.add(new MyListData( "Primero","Juan", "Saltar", R.drawable.img1));
        newListData.add(new MyListData( "Segundo","Luis", "Agacharse", R.drawable.img2));
        newListData.add(new MyListData( "Tercer","Carlos", "Correr", R.drawable.img3));
        newListData.add(new MyListData( "Cuarto","Domingo", "Sentadillas", R.drawable.img4));
        newListData.add(new MyListData( "Quinto","Ramon", "Bracear", R.drawable.img5));
        newListData.add(new MyListData( "Sexto","Vicente", "Rodar", R.drawable.img6));

        RecyclerView rcView = (RecyclerView) findViewById(R.id.rclview);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        MyListAdapter adapter = new MyListAdapter(newListData);
        rcView.setAdapter(adapter);
     // rcView.setHasFixedSize(true);

    newObject.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onButtonShowPopupWindowClick(view);
        }
    });

    }

    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.new_contact, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);


        EditText edtTxt1 = popupView.findViewById(R.id.edtTxt1);
        EditText edtTxt2 = popupView.findViewById(R.id.edtTxt2);
        EditText edtTxt3 = popupView.findViewById(R.id.edtTxt3);
        Button btn2 = popupView.findViewById(R.id.button2);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp1 = edtTxt1.getText().toString().trim();
                String tmp2 = edtTxt2.getText().toString().trim();
                String tmp3 = edtTxt3.getText().toString().trim();

                MyListData tmpUser = new MyListData( tmp1, tmp2, tmp3, R.drawable.img1);
                newListData.add(tmpUser);
                popupWindow.dismiss();
                Toast.makeText(MainActivity.this, "Registro agregado", Toast.LENGTH_SHORT).show();
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
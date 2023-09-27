package com.example.vistadelista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListData[] newListData = new MyListData[6];

        newListData[0] = new MyListData( "Primero", "Saltar", R.drawable.img1);
        newListData[1] = new MyListData( "Segundo", "Agacharse", R.drawable.img2);
        newListData[2] = new MyListData( "Tercer", "Correr", R.drawable.img3);
        newListData[3] = new MyListData( "Cuarto", "Sentadillas", R.drawable.img4);
        newListData[4] = new MyListData( "Quinto", "Bracear", R.drawable.img5);
        newListData[5] = new MyListData( "Sexto", "Rodar", R.drawable.img6);

        RecyclerView rcView = (RecyclerView) findViewById(R.id.rclview);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        MyListAdapter adapter = new MyListAdapter(newListData);
        rcView.setAdapter(adapter);
     // rcView.setHasFixedSize(true);



    }
}
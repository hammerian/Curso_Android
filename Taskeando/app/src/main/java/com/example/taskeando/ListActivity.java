package com.example.taskeando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public ArrayList <String> categories;

    private Spinner spnrType;
    private RecyclerView rclTask;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        spnrType = (Spinner) findViewById(R.id.spnr);
        rclTask = (RecyclerView) findViewById(R.id.rccl1);

        dbRef = FirebaseDatabase.getInstance().getReference().child("categories");

        URI myUri = URI.create("https://avatars.githubusercontent.com/u/11311828?v=4");
        ArrayList<Taskita> newListData = new ArrayList<Taskita>();
        // Load data from scratch
        newListData.add(new Taskita("Pop-Up", "Crear una app, que muestre un Pop-Up al pulsar un botón.", "Práctica", false,myUri));
        newListData.add(new Taskita("Arandanos y coco", "Ingredientes: Harina, aceite, leches, azucar, ...", "Tarta", false,myUri));
        newListData.add(new Taskita("Chocolate con almendras", "Ingredientes: Harina, aceite, leches, azucar, ...", "Bizcocho", false,myUri));
        newListData.add(new Taskita("Chocolate y fresas", "Ingredientes: Harina, aceite, leches, azucar, ...", "Tarta", false,myUri));

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categories = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String categ = ds.getValue().toString();
                    categories.add(categ);
                }
                changeType(categories);
                //Do what you need to do with your list
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Don't ignore errors!
            }
        };
        dbRef.addListenerForSingleValueEvent(valueEventListener);

        spnrType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void changeType (ArrayList<String> typeList) {
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrType.setAdapter(musicAdapter);
    }
}
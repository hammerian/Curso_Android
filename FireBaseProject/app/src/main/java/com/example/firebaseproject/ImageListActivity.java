package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    public ArrayList<String> dwarfs;

    private RecyclerView rclDwarfs;

    private MyListAdapter listAdapter;

    private DatabaseReference dbRefDawrfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        rclDwarfs = (RecyclerView) findViewById(R.id.rccl1);
        // Instancia el listado de imagenes en Firebase
        dbRefDawrfs = FirebaseDatabase.getInstance().getReference().child("dwarfs");

        // Define el array Adpater
        rclDwarfs.setLayoutManager(new LinearLayoutManager(this));
        listAdapter= new MyListAdapter(dwarfs);
        // Y carga el Array de imagenes
        rclDwarfs.setAdapter(listAdapter);
        rclDwarfs.setHasFixedSize(false);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dwarfs = new ArrayList<>();
                // Recupera los strings de las URLs de las imagenes y las carga en el Array
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String categ = ds.getValue().toString();
                    dwarfs.add(categ);
                }
                updateList(dwarfs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Don't ignore errors!
            }
        };
        dbRefDawrfs.addListenerForSingleValueEvent(valueEventListener);

    }

    private void updateList (ArrayList<String> myNewList) {
        listAdapter = new MyListAdapter(myNewList);
        // Actualiza el Adapter con el array cargado
        rclDwarfs.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}
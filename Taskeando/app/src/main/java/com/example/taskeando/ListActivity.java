package com.example.taskeando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public ArrayList <String> categories;
    public ArrayList <Taskita> taskitas;

    private Spinner spnrType;
    private RecyclerView rclTask;

    private DatabaseReference dbRefCateg;
    private DatabaseReference dbRefTasks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        spnrType = (Spinner) findViewById(R.id.spnr);
        rclTask = (RecyclerView) findViewById(R.id.rccl1);

        dbRefCateg = FirebaseDatabase.getInstance().getReference().child("categories");

        // Initiate Firebase Database connection
        dbRefTasks = FirebaseDatabase.getInstance().getReference().child("tasks");

      // Load data from scratch
        taskitas = new ArrayList<Taskita>();
        Uri myUri = null;
        taskitas.add(new Taskita("Pop-Up", "Crear una app, que muestre un Pop-Up al pulsar un botón.", "Práctica", false, myUri));
        taskitas.add(new Taskita("Lista de contactos", "Ejemplo de App donde tenemos un listado de contactos.", "Ejemplo", false,myUri));
        taskitas.add(new Taskita("Crear un formulario", "Realizar un XML Drawable de un formulario para crear un contacto.", "Ejercicio", false,myUri));
        taskitas.add(new Taskita("Guardar Usuarios de Firebase", "Creación de un usuario de Firebase y guardado de sus datos.", "Teoría", false,myUri));

        // Recycler view initiation
        rclTask.setLayoutManager(new LinearLayoutManager(this));
        TaskAdapter rcpAdapter = new TaskAdapter(taskitas);
        rclTask.setAdapter(rcpAdapter);
        rclTask.setHasFixedSize(false);
        //rcpAdapter.notifyDataSetChanged();
       // rclTask.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

     /* // Save array in Firebase
            dbRefTasks.setValue(newListData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(ListActivity.this, "El listado no se ha guardado", Toast.LENGTH_SHORT).show();
                }
            }
        }); */

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
        dbRefCateg.addListenerForSingleValueEvent(valueEventListener);

     /* ValueEventListener taskitaListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Taskita> newListData = new ArrayList<Taskita>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Taskita tsk = (Taskita) ds.getValue(Taskita.class);
                    newListData.add(tsk);
                }
                taskitas = newListData;
                // rcpAdapter.setTaskData(newListData);
                // rcpAdapter = new TaskAdapter(newListData);
                // rclTask.setAdapter(rcpAdapter);
                updateList(taskitas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Don't ignore errors!
            }
        };
        dbRefTasks.addListenerForSingleValueEvent(taskitaListener); */

        spnrType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /*private void updateList (ArrayList<Taskita> myNewList) {
        rcpAdapter = new TaskAdapter(myNewList);
        rclTask.setAdapter(rcpAdapter);
        rcpAdapter.notifyDataSetChanged();
    }*/

    private void changeType (ArrayList<String> typeList) {
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrType.setAdapter(musicAdapter);
    }
}
package com.example.rvbuscadorfiltrado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etBuscador;
    RecyclerView rvLista;
    AdaptadorUsuarios adaptador;
    List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        etBuscador = findViewById(R.id.etBuscador);

        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filtrar(editable.toString());
            }
        });

        rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new GridLayoutManager(this, 1));

        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Ana", "123456", "ana@mail.es"));
        listaUsuarios.add(new Usuario("Pepe", "123456", "pepe@mail.es"));
        listaUsuarios.add(new Usuario("Antonio", "123456", "antonio@mail.es"));
        listaUsuarios.add(new Usuario("Pepito", "123456", "pepito@mail.es"));
        listaUsuarios.add(new Usuario("Dani", "123456", "pepazo@mail.es"));

        adaptador = new AdaptadorUsuarios(MainActivity.this, listaUsuarios);
        rvLista.setAdapter(adaptador);


    }

    public void filtrar(String texto) {
        ArrayList<Usuario> filtrarLista = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(usuario);
            }
        }


        adaptador.filtrar(filtrarLista);

    }

}
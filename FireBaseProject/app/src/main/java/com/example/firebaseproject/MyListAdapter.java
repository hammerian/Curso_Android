package com.example.firebaseproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private ArrayList<String> listData;
    private static Context adapHolder;

    public MyListAdapter(ArrayList<String> listData) {
        // Carga el array de datos en el Adapter
        this.listData = listData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Instancia la imagen en la celda
            this.imgView = (ImageView) itemView.findViewById(R.id.imgView2);

        }
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lytInflater = LayoutInflater.from(parent.getContext());
        // Cargamos la celda creada previamente
        View lstItem = lytInflater.inflate(R.layout.image_cell, parent, false);
        adapHolder = parent.getContext();
        ViewHolder vwHolder = new ViewHolder(lstItem);

        return vwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        final String mListData = listData.get(position);
        // Utiliza la librería Glide para representar la imagen en la celda
        Glide.with(adapHolder).load(mListData).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        // Control de carga asincrona
        if (this.listData == null) {
            return 0;
        }
        // Retorna la cantidad de elementos del array
        return this.listData.size();
    }
}

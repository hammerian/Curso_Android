package com.example.vistadelista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private ArrayList<MyListData> listData;

    public MyListAdapter(ArrayList<MyListData> lstData) {
        this.listData = lstData;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgView;
        public TextView txtView1;
        public TextView txtView2;
        public TextView txtView3;
        public Button btn1;

        public LinearLayout rltId1;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imgView = (ImageView) itemView.findViewById(R.id.imgView2);

            this.txtView1 = (TextView) itemView.findViewById(R.id.txtView3);
            this.txtView2 = (TextView) itemView.findViewById(R.id.txtView4);
            this.txtView3 = (TextView) itemView.findViewById(R.id.txtView5);

            this.btn1 = (Button) itemView.findViewById(R.id.btn1);

            rltId1 = (LinearLayout) itemView.findViewById(R.id.rltId1);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lytInflater = LayoutInflater.from(parent.getContext());

        View lstItem = lytInflater.inflate(R.layout.activity_mylist, parent, false);

        ViewHolder vwHolder = new ViewHolder(lstItem);

        return vwHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MyListData mListData = listData.get(position);

        holder.txtView1.setText(listData.get(position).getValue1());
        holder.txtView2.setText(listData.get(position).getValue2());
        holder.txtView3.setText(listData.get(position).getValue3());
        holder.imgView.setImageResource(listData.get(position).getImageValue());

        holder.rltId1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Pulsado "+ mListData.getValue1(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.listData.size();
    }


}

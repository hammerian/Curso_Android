package com.example.taskeando;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Taskita> taskitaData;
  //  private ArrayList<Taskita> filterData;

    private Context adapHolder;



    /*public TaskAdapter(ArrayList<Taskita> lstData) {
        this.taskitaData = lstData;
        this.filterData = lstData;
    }*/

    public TaskAdapter(ArrayList<Taskita> taskitaData) {
        this.taskitaData = taskitaData;
    }

    @Override
    public int getItemCount() {
        return taskitaData.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public TextView txtView1;
        public TextView txtView2;
        public TextView txtView3;
        public CheckBox chckB1;

        public LinearLayout rltId1;

        public ViewHolder(View itemView) {
            super(itemView);
            // Initiate Recipe Cell elements
            this.imgView = (ImageView) itemView.findViewById(R.id.imgView2);
            this.txtView1 = (TextView) itemView.findViewById(R.id.txtName);
            this.txtView2 = (TextView) itemView.findViewById(R.id.txtDesc);
            this.txtView3 = (TextView) itemView.findViewById(R.id.txtType);
            this.chckB1 = (CheckBox) itemView.findViewById(R.id.chckB1);
            rltId1 = (LinearLayout) itemView.findViewById(R.id.rltId1);

        }
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Link Recipe Cell with the Recycler view
        LayoutInflater lytInflater = LayoutInflater.from(parent.getContext());
       // adapHolder = parent.getContext();
        View lstItem = lytInflater.inflate(R.layout.task_cell, parent, false);
        ViewHolder vwHolder = new ViewHolder(lstItem);
        return vwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        int myPos = position;
        // Populate cell with Recipe Data
        final Taskita mListData = taskitaData.get(position);
        holder.txtView1.setText(mListData.getTaskName());
        holder.txtView2.setText(mListData.getTaskDescription());
        holder.txtView3.setText(mListData.getTaskType());
        holder.chckB1.setChecked(mListData.isTaskEnd());
        Uri imageV = mListData.getTaskImg();

        holder.chckB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListData.setTaskEnd(!mListData.isTaskEnd());
                taskitaData.set(myPos,mListData);
                // TODO: Guardar datos en Firebase ¿?
            }
        });
    }

    // Function for return the working array in the Adapter
    public ArrayList<Taskita> getRecipeData() {
        return this.taskitaData;
    }

    // Function for replace the working array in adapter
    public void setTaskData(ArrayList<Taskita> myTasks) {
        this.taskitaData = myTasks;
    }
}

package com.example.biometricthings.PruebasLoeches.ListaContactos;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biometricthings.PruebasLoeches.MyListAdapter;
import com.example.biometricthings.PruebasLoeches.MyListData;
import com.example.biometricthings.R;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private ArrayList<Contact> listdata;

    public ContactAdapter(ArrayList<Contact> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.contact_card, parent, false);
        ContactAdapter.ViewHolder viewHolder = new ContactAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*
        * public ImageView ivPersona;
        public TextView tvNombre, tvNum, tvCorreo;
        public Button btnBorrar, btnLlamar;
        public LinearLayout llTotal;
        *
        * */
        final Contact c = listdata.get(position);
        
        holder.tvNombre.setText(listdata.get(position).getNombre());
        holder.tvNum.setText(listdata.get(position).getNumTelf());
        holder.tvCorreo.setText(listdata.get(position).getCorreo());

        if(listdata.get(position).getFlag() == 1){
            holder.ivPersona.setImageURI(listdata.get(position).getFoto());
        }else if(listdata.get(position).getFlag() == 0){
            holder.ivPersona.setImageResource(listdata.get(position).getFotoV());
        }


        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telf = listdata.get(position).getNumTelf();
                //TODO ACCEDER A LA APP DE LLAMADAS DEL DISPOSITIVO MOVIL
            }
        });
        //holder.imageView.setImageResource(listdata.get(position).getImgId());
        holder.llTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),c.getNombre(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPersona;
        public TextView tvNombre, tvNum, tvCorreo;
        public Button btnBorrar, btnLlamar;
        public LinearLayout llTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivPersona = (ImageView) itemView.findViewById(R.id.ivPersona);

            this.tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            this.tvNum = (TextView) itemView.findViewById(R.id.tvNum);
            this.tvCorreo = (TextView) itemView.findViewById(R.id.tvCorreo);

            this.btnBorrar = (Button) itemView.findViewById(R.id.btnBorrar);
            this.btnLlamar = (Button) itemView.findViewById(R.id.btnLlamar);

            this.llTotal = (LinearLayout) itemView.findViewById(R.id.llTotal);

        }
    }
}
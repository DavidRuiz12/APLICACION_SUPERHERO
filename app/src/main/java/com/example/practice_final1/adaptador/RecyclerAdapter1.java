package com.example.practice_final1.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.practice_final1.R;
import com.example.practice_final1.personajes.abomb;
import com.example.practice_final1.controlador.PersonajesM;
import com.example.practice_final1.personajes.spiderman;
import com.example.practice_final1.personajes.sttepenwolf;
import com.example.practice_final1.personajes.thor;
import com.example.practice_final1.personajes.wolverine;
import com.example.practice_final1.personajes.wonderwoman;

import java.util.List;


public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.RecyclerHolder> implements View.OnClickListener {
    List<PersonajesM> ListCharacter;
    private Context context;


    public RecyclerAdapter1(List<PersonajesM> ListCharacter,Context context) {
        this.ListCharacter = ListCharacter;
        this.context = context;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick (int posicion);

    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_list,parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        return recyclerHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        PersonajesM PMarvel = ListCharacter.get(position);
        holder.txtViewDesc.setText(PMarvel.getDescripcion());
        holder.txtViewTitle.setText(PMarvel.getNombre());
        holder.imgPelicula.setImageResource(PMarvel.getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion;
                listener.onItemClick(position);
                posicion = position;
                if (posicion == 0){
                    Intent intent = new Intent(context, spiderman.class);
                    context.startActivity(intent);
                }if (posicion == 1){
                    Intent intent = new Intent(context, abomb.class);
                    context.startActivity(intent);
                }if (posicion == 2){
                    Intent intent = new Intent(context, wonderwoman.class);
                    context.startActivity(intent);
                }if (posicion == 3){
                    Intent intent = new Intent(context, thor.class);
                    context.startActivity(intent);
                }if (posicion == 4){
                    Intent intent = new Intent(context, wolverine.class);
                    context.startActivity(intent);
                }if (posicion == 5){
                    Intent intent = new Intent(context, sttepenwolf.class);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return ListCharacter.size();
    }

    @Override
    public void onClick(View view) {

    }

    public static class RecyclerHolder extends ViewHolder{
        TextView  txtViewTitle;
        TextView  txtViewDesc;
        ImageView imgPelicula;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imgPelicula  = (ImageView) itemView.findViewById(R.id.img_item);
            txtViewTitle = (TextView)  itemView.findViewById(R.id.txt_item_tittle);
            txtViewDesc  = (TextView)  itemView.findViewById(R.id.txt_item_desc);


        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



}


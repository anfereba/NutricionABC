package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.EditarPlanesNutricionales;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;

import java.util.ArrayList;

public class ListaAlimentosAdapter extends RecyclerView.Adapter<ListaAlimentosAdapter.AlimentosViewHolder> {
    ArrayList<Alimentos>listaAlimentos;
    public ListaAlimentosAdapter(ArrayList<Alimentos>listaAlimentos){
        this.listaAlimentos=listaAlimentos;
    }
    @NonNull
    @Override
    public AlimentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_alimento,null,false);
        return new AlimentosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentosViewHolder holder, int position) {
        holder.viewNombre.setText(listaAlimentos.get(position).getNombreAlimento());
    }

    @Override
    public int getItemCount() {
        return listaAlimentos.size();
    }

    public class AlimentosViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        public AlimentosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre= itemView.findViewById(R.id.NombreAlimentoView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, VerAlimentos.class); //si algo lo toca se podra modificar
                    intent.putExtra("IdAlimentos",listaAlimentos.get(getAdapterPosition()).getIdAlimento());
                    context.startActivity(intent);
                }
            });
        }
    }
}

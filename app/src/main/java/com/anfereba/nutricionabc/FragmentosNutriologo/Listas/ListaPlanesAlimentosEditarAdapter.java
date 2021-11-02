package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.EditarPlanesAlimentos;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.PlanesAlimentos;

import java.util.ArrayList;

public class ListaPlanesAlimentosEditarAdapter extends RecyclerView.Adapter<ListaPlanesAlimentosEditarAdapter.ListaPlanesAlimentosEditarAdapterViewHolder>{
    ArrayList<PlanesAlimentos> listaPlanesAlimentos;
    int a=1;
    public ListaPlanesAlimentosEditarAdapter(ArrayList<PlanesAlimentos>listaPlanesAlimentos){
        this.listaPlanesAlimentos=listaPlanesAlimentos;
    }
    @NonNull
    @Override
    public ListaPlanesAlimentosEditarAdapter.ListaPlanesAlimentosEditarAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_planes_alimentos2,null,false);
        return new ListaPlanesAlimentosEditarAdapter.ListaPlanesAlimentosEditarAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPlanesAlimentosEditarAdapter.ListaPlanesAlimentosEditarAdapterViewHolder holder, int position) {
        holder.viewNombre.setText("Dia "+listaPlanesAlimentos.get(position).getDia()+"  "+listaPlanesAlimentos.get(position).getNombrePlanesAlimentos());
        a=a+1;
    }

    @Override
    public int getItemCount() {
        return listaPlanesAlimentos.size();
    }

    public class ListaPlanesAlimentosEditarAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        public ListaPlanesAlimentosEditarAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre= itemView.findViewById(R.id.NombrePlanesAlimentos2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, EditarPlanesAlimentos.class); //si algo me toca modificarlo
                    intent.putExtra("IdPlanAlimento",listaPlanesAlimentos.get(getAdapterPosition()).getIdPlanAlimento());
                    intent.putExtra("IdPlanNutricional",listaPlanesAlimentos.get(getAdapterPosition()).getIdPlanNutricional());
                    context.startActivity(intent);
                }
            });
        }
    }
}

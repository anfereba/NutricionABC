package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.EditarPlanesNutricionales;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerPlanesNutricionales;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;

import java.util.ArrayList;

public class ListaPlanesNutricionalesAdapter extends RecyclerView.Adapter<ListaPlanesNutricionalesAdapter.PlanesNutricionalesViewHolder>{
    ArrayList<PlanesNutricionales>listaPlanes;
    public ListaPlanesNutricionalesAdapter(ArrayList<PlanesNutricionales>listaPlanes){
        this.listaPlanes = listaPlanes;
    }
    @NonNull
    @Override
    public PlanesNutricionalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_plan_nutricional,null,false);
        return new PlanesNutricionalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanesNutricionalesViewHolder holder, int position) {

        holder.NombrePlan.setText(listaPlanes.get(position).getNombrePlan());
    }

    @Override
    public int getItemCount() {
        return listaPlanes.size();
    }

    public class PlanesNutricionalesViewHolder extends RecyclerView.ViewHolder {
        TextView NombrePlan;
        public PlanesNutricionalesViewHolder(@NonNull View itemView) {
            super(itemView);
            NombrePlan = itemView.findViewById(R.id.NombrePlanNutricional);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, VerPlanesNutricionales.class); //si algo me toca modificarlo
                    intent.putExtra("IdPlanesNutricionales",listaPlanes.get(getAdapterPosition()).getIdPlanNutricional());
                    Log.i("Id plan",listaPlanes.get(getAdapterPosition()).getIdPlanNutricional().toString());
                     context.startActivity(intent);
                }
            });
        }
    }
}

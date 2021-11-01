package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.PlanDirario;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerPlanesNutricionales;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.Entidades.HistorialPlanes;

import java.util.ArrayList;

public class ListaItemsHistorialPlanAdapter extends RecyclerView.Adapter<ListaItemsHistorialPlanAdapter.ListaItemsHistorialPlanViewHolder>{
    ArrayList<HistorialPlanes> ListaHistorial;
    public ListaItemsHistorialPlanAdapter(ArrayList<HistorialPlanes>ListaHistorial){
        this.ListaHistorial=ListaHistorial;
    }
    @NonNull
    @Override
    public ListaItemsHistorialPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_historial_planes_nutricionales2,null,false);
        return new ListaItemsHistorialPlanAdapter.ListaItemsHistorialPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaItemsHistorialPlanViewHolder holder, int position) {

        holder.NombreHistrorialPlan.setText(ListaHistorial.get(position).getNombrePlanNutricional());
        holder.CumplimientoHistorialPlan.setText(ListaHistorial.get(position).getCumplimientoDelPlan().toString()+"% Cumplido");
        Boolean b= Boolean.parseBoolean(ListaHistorial.get(position).getVistoBueno());
        if (b==true){
            holder.VistoBuenoHistorialPlan.setText("Aprobado");
            holder.VistoBuenoHistorialPlan.setTextColor(Color.GREEN);
        }else {
            holder.VistoBuenoHistorialPlan.setText("Sin Calificacion");
            holder.VistoBuenoHistorialPlan.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return ListaHistorial.size();
    }

    public class ListaItemsHistorialPlanViewHolder extends RecyclerView.ViewHolder {
        TextView NombreHistrorialPlan,CumplimientoHistorialPlan,VistoBuenoHistorialPlan;
        public ListaItemsHistorialPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreHistrorialPlan = (TextView) itemView.findViewById(R.id.NombreHistrorialPlan);
            CumplimientoHistorialPlan = (TextView) itemView.findViewById(R.id.CumplimientoHistorialPlan);
            VistoBuenoHistorialPlan = (TextView) itemView.findViewById(R.id.VistoBuenoHistorialPlan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, PlanDirario.class); //si algo me toca modificarlo
                    intent.putExtra("IdHijo",ListaHistorial.get(getAdapterPosition()).getIdHijo());
                    intent.putExtra("IdPlanNutricional",ListaHistorial.get(getAdapterPosition()).getIdPlanNutricional());
                    context.startActivity(intent);
                }
            });
        }
    }
}

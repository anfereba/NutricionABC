package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.Hijos;

import java.util.ArrayList;

public class ListaHijosSinPlanAdapter extends RecyclerView.Adapter<ListaHijosSinPlanAdapter.ListaHijosSinPlanViewHolder>{
    ArrayList<Hijos> listaHijosSinPlan;
    public ListaHijosSinPlanAdapter(ArrayList<Hijos>listaHijosSinPlan){
        this.listaHijosSinPlan=listaHijosSinPlan;
    }
    @NonNull
    @Override
    public ListaHijosSinPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_hijo_sin_plan,null,false);
        return new ListaHijosSinPlanAdapter.ListaHijosSinPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaHijosSinPlanViewHolder holder, int position) {
        holder.NombreHijoSinPlan.setText(listaHijosSinPlan.get(position).getNombreHijos());
        holder.EdadHijoSinPlan.setText("Estatura: "+listaHijosSinPlan.get(position).getEstaturaHijos()+" m ");
        holder.PesoHijoSinPlan.setText("Edad: "+listaHijosSinPlan.get(position).getEdadHijos().toString()+" Años ");
        holder.EstaturaHijoSinPlan.setText("Peso: "+listaHijosSinPlan.get(position).getPesoHijos().toString()+" Kilos ");

    }

    @Override
    public int getItemCount() {
        return listaHijosSinPlan.size();
    }

    public class ListaHijosSinPlanViewHolder extends RecyclerView.ViewHolder {
        TextView NombreHijoSinPlan, EdadHijoSinPlan, PesoHijoSinPlan,EstaturaHijoSinPlan;
        public ListaHijosSinPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreHijoSinPlan = itemView.findViewById(R.id.NombreHijoSinPlan);
            EdadHijoSinPlan = itemView.findViewById(R.id.EdadHijoSinPlan);
            PesoHijoSinPlan = itemView.findViewById(R.id.PesoHijoSinPlan);
            EstaturaHijoSinPlan=itemView.findViewById(R.id.EstaturaHijoSinPlan);
        }
    }
}

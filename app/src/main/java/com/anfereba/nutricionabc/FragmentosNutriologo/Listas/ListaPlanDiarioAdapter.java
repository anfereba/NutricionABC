package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;

import java.util.ArrayList;

public class ListaPlanDiarioAdapter extends RecyclerView.Adapter<ListaPlanDiarioAdapter.ListaPlanDiarioViewHolder>{
    ArrayList<PlanesDiarios> listaPlanesDiarios;
    public ListaPlanDiarioAdapter(ArrayList<PlanesDiarios>listaPlanesDiarios){
        this.listaPlanesDiarios=listaPlanesDiarios;
    }
    @NonNull
    @Override
    public ListaPlanDiarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_plan_diario,null,false);
        return new ListaPlanDiarioAdapter.ListaPlanDiarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPlanDiarioViewHolder holder, int position) {


        holder.textViewPlanDiario.setText(listaPlanesDiarios.get(position).getNombreAlimento());
    }

    @Override
    public int getItemCount() {
        return listaPlanesDiarios.size();
    }

    public class ListaPlanDiarioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlanDiario;
        CheckBox checkBoxPlanDiario;
        public ListaPlanDiarioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlanDiario = (TextView) itemView.findViewById(R.id.textViewPlanDiario);
            checkBoxPlanDiario = (CheckBox) itemView.findViewById(R.id.checkBoxPlanDiario);
        }
    }
}

package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanDiario;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;

import java.util.ArrayList;

public class ListaPlanDiarioAdapter extends RecyclerView.Adapter<ListaPlanDiarioAdapter.ListaPlanDiarioViewHolder>{
   int a =0;
   int b=0;
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
        a= a+1;
        if(listaPlanesDiarios.get(position).getCumplimiento()!=1){
            holder.checkBoxPlanDiario.setChecked(true);
            b=b+1;
        }
        holder.textViewPlanDiario.setText("Plan nutricional Dia "+a+" "+listaPlanesDiarios.get(position).getNombreAlimento());
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
            checkBoxPlanDiario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    DbPlanDiario dbPlanDiario = new DbPlanDiario(context);
                    if(checkBoxPlanDiario.isChecked()==true){
                        dbPlanDiario.EditarCumplimientoDelPlanDiario(listaPlanesDiarios.get(getAdapterPosition()).getIdCumplimientoPlanDiario(),2);
                        b=b+1;
                        Long c=Long.valueOf(a);
                        Long d=Long.valueOf(b);
                        long e = d/c;
                        String f= String.valueOf(e);
                        System.out.println(f);
                    }else{
                        dbPlanDiario.EditarCumplimientoDelPlanDiario(listaPlanesDiarios.get(getAdapterPosition()).getIdCumplimientoPlanDiario(),1);
                        b=b-1;
                        String c= String.valueOf(b/a);
                        System.out.println(c);
                    }

                }
            });
        }
    }
}

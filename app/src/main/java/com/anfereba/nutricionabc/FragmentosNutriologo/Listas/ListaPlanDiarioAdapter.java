package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerPlanesNutricionales;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanDiario;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.lang.reflect.Type;
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
        holder.textViewPlanDiario.setText("Dia "+listaPlanesDiarios.get(position).getDia()+" "+listaPlanesDiarios.get(position).getNombreAlimento());
        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(holder.itemView.getContext());
        String a =dbPlanNutricional.verVistoBueno(listaPlanesDiarios.get(position).getIdHijo(),listaPlanesDiarios.get(position).getPlanNutricional());
        Boolean b= Boolean.parseBoolean(a);
        if (b==true){
            holder.checkBoxPlanDiario.setEnabled(false);
        }
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
            textViewPlanDiario.setTextColor(Color.WHITE);
            textViewPlanDiario.setTextSize(20);
            checkBoxPlanDiario = (CheckBox) itemView.findViewById(R.id.checkBoxPlanDiario);
            checkBoxPlanDiario.setTextSize(20);
            checkBoxPlanDiario.setTextColor(Color.WHITE);
            SharedPreferences shared = itemView.getContext().getSharedPreferences("Sesiones",itemView.getContext().MODE_PRIVATE);
            Integer IdPerfilSistema = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
            DbUsuario dbUsuario = new DbUsuario(itemView.getContext());
            String U = dbUsuario.consultarDato("idPerfilSistema","idUsuario",IdPerfilSistema.toString());
            int u = Integer.parseInt (U);
            if(u==2){
                checkBoxPlanDiario.setEnabled(false);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, VerAlimentos.class); //si algo me toca modificarlo
                    intent.putExtra("IdAlimentos",listaPlanesDiarios.get(getAdapterPosition()).getIdAlimento());
                    intent.putExtra("IdPlanAlimento",listaPlanesDiarios.get(getAdapterPosition()).getIdPlanAlimento());
                    context.startActivity(intent);
                }
            });
            checkBoxPlanDiario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    DbPlanDiario dbPlanDiario = new DbPlanDiario(context);
                    DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(context);

                    if(checkBoxPlanDiario.isChecked()==true){
                        dbPlanDiario.EditarCumplimientoDelPlanDiario(listaPlanesDiarios.get(getAdapterPosition()).getIdCumplimientoPlanDiario(),2);
                        b=b+1;
                        long c = (long) ((b*1.00)/(a*1.00)*100);
                        String f= String.valueOf(c);
                        Toast.makeText(context," Has completado el "+f+"%",Toast.LENGTH_SHORT).show();
                        int d = (int) c;
                        dbPlanNutricional.EditarPorcentajePlanes(listaPlanesDiarios.get(getAdapterPosition()).getPlanNutricional(),listaPlanesDiarios.get(getAdapterPosition()).getIdHijo(),d);
                        if(a==b){
                            Toast.makeText(context," Has completado el Plan",Toast.LENGTH_SHORT).show();
                            Toast.makeText(context," Se Notificara al nutriologo",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        dbPlanDiario.EditarCumplimientoDelPlanDiario(listaPlanesDiarios.get(getAdapterPosition()).getIdCumplimientoPlanDiario(),1);
                        b=b-1;
                        long c = (long) ((b*1.00)/(a*1.00)*100);
                        String f= String.valueOf(c);
                        Toast.makeText(context," Has completado el "+f+"%",Toast.LENGTH_SHORT).show();
                        int d = (int) c;
                        dbPlanNutricional.EditarPorcentajePlanes(listaPlanesDiarios.get(getAdapterPosition()).getPlanNutricional(),listaPlanesDiarios.get(getAdapterPosition()).getIdHijo(),d);
                    }

                }
            });

        }

    }

}

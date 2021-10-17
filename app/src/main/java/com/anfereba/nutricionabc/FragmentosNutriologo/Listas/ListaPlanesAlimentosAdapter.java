package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.EditarAlimentos;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerPlanesNutricionales;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesAlimentos;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListaPlanesAlimentosAdapter extends RecyclerView.Adapter<ListaPlanesAlimentosAdapter.ListaPlanesAlimentosViewHolder>{
    ArrayList<PlanesAlimentos> listaPlanesAlimentos;
    int a=1;
    public ListaPlanesAlimentosAdapter(ArrayList<PlanesAlimentos>listaPlanesAlimentos){
        this.listaPlanesAlimentos=listaPlanesAlimentos;
    }
    @NonNull
    @Override
    public ListaPlanesAlimentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_planes_alimentos,null,false);
       return new ListaPlanesAlimentosAdapter.ListaPlanesAlimentosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPlanesAlimentosViewHolder holder, int position) {

        holder.viewNombre.setText("Dia "+a+"  "+listaPlanesAlimentos.get(position).getNombrePlanesAlimentos());
        a=a+1;
    }

    @Override
    public int getItemCount() {

        return listaPlanesAlimentos.size();
    }

    public class ListaPlanesAlimentosViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        public ListaPlanesAlimentosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre= itemView.findViewById(R.id.NombrePlanesAlimentos);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, VerAlimentos.class); //si algo me toca modificarlo
                    intent.putExtra("IdAlimentos",listaPlanesAlimentos.get(getAdapterPosition()).getIdAlimento());
                    intent.putExtra("IdPlanAlimento",listaPlanesAlimentos.get(getAdapterPosition()).getIdPlanAlimento());

                    context.startActivity(intent);
                    //((Activity)context).finish();
                }
            });


        }
    }
}

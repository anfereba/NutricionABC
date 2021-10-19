package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosCliente.VerHijo;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaAlimentosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesAlimentosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesAlimentos;

import java.util.ArrayList;

public class ListaHijosAdapter extends RecyclerView.Adapter<ListaHijosAdapter.ListaHijosViewHolder>{
    ArrayList<Hijos> listaHijos;
    public ListaHijosAdapter(ArrayList<Hijos>listaHijos){
        this.listaHijos=listaHijos;
    }
    @NonNull
    @Override
    public ListaHijosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_hijo,null,false);
        return new ListaHijosAdapter.ListaHijosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaHijosViewHolder holder, int position) {
        holder.NombreHijos.setText(listaHijos.get(position).getNombreHijos());
        holder.EstaturaHijos.setText(listaHijos.get(position).getEstaturaHijos()+" m");
        holder.EdadHijos.setText(listaHijos.get(position).getEdadHijos().toString()+" Años ");
        holder.PesoHijos.setText(listaHijos.get(position).getPesoHijos().toString()+" Kilos ");
    }

    @Override
    public int getItemCount() {
        return listaHijos.size();
    }

    public class ListaHijosViewHolder extends RecyclerView.ViewHolder {
        TextView NombreHijos;
        TextView EstaturaHijos;
        TextView EdadHijos;
        TextView PesoHijos;
        public ListaHijosViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreHijos= itemView.findViewById(R.id.NombreHijo);
            EstaturaHijos = itemView.findViewById(R.id.EstaturaHijo);
            EdadHijos = itemView.findViewById(R.id.EdadHijo);
            PesoHijos = itemView.findViewById(R.id.PesoHijo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, VerHijo.class); //si algo me toca modificarlo
                    intent.putExtra("IdHijo",listaHijos.get(getAdapterPosition()).getIdHijos());
                    context.startActivity(intent);
                    //((Activity)context).finish();
                }
            });
        }
    }
}

package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosCliente.VerHijo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Hijos;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaHijos.get(position).getFotoHijos(), 0,
                listaHijos.get(position).getFotoHijos().length);


        holder.IMGHijo.setImageBitmap(bitmap);

        holder.NombreHijos.setText(listaHijos.get(position).getNombreHijos());
        holder.EstaturaHijos.setText("Estatura: "+listaHijos.get(position).getEstaturaHijos()+" m");
        holder.EdadHijos.setText("Edad: "+listaHijos.get(position).getEdadHijos().toString()+" Años ");
        holder.PesoHijos.setText("Peso: "+listaHijos.get(position).getPesoHijos().toString()+" Kilos ");
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
        CircleImageView IMGHijo;
        public ListaHijosViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreHijos= itemView.findViewById(R.id.NombreHijo);
            EstaturaHijos = itemView.findViewById(R.id.EstaturaHijo);
            EdadHijos = itemView.findViewById(R.id.EdadHijo);
            PesoHijos = itemView.findViewById(R.id.PesoHijo);
            IMGHijo = itemView.findViewById(R.id.IMGHijo);
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

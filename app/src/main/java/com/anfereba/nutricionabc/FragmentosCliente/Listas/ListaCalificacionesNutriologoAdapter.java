package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.VistasDb.VistaCalificacionUsuario;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaCalificacionesNutriologoAdapter extends RecyclerView.Adapter<ListaCalificacionesNutriologoAdapter.CalificacionesViewHolder>{
    public ArrayList<VistaCalificacionUsuario> listaCalificaciones;
    private Context context;

    public ListaCalificacionesNutriologoAdapter(ArrayList<VistaCalificacionUsuario> listaCalificaciones, Context context){
        this.listaCalificaciones = listaCalificaciones;
        this.context = context;
    }

    @NonNull
    @Override
    public CalificacionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_calificaciones,null,false);
        return new ListaCalificacionesNutriologoAdapter.CalificacionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalificacionesViewHolder holder, int position) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaCalificaciones.get(position).getFotoPadre(), 0,
                listaCalificaciones.get(position).getFotoPadre().length);

        holder.IMG_Comentario.setImageBitmap(bitmap);

        holder.TXTNombreYApellidoComentario.setText(listaCalificaciones.get(position).getNombrePadre() + " " +
                listaCalificaciones.get(position).getApellidosPadre() + " " +listaCalificaciones.get(position).getPuntuacion().toString() );

        holder.TXT_Comentario.setText(listaCalificaciones.get(position).getComentario());


    }

    @Override
    public int getItemCount() {
        return listaCalificaciones.size();
    }

    public class CalificacionesViewHolder extends RecyclerView.ViewHolder{

        CircleImageView IMG_Comentario;
        TextView TXTNombreYApellidoComentario, TXT_Comentario;

        public CalificacionesViewHolder(@NonNull View itemView) {
            super(itemView);

            IMG_Comentario = itemView.findViewById(R.id.IMG_Comentario);
            TXTNombreYApellidoComentario = itemView.findViewById(R.id.TXTNombreYApellidoComentario);
            TXT_Comentario = itemView.findViewById(R.id.TXT_Comentario);


        }
    }
}

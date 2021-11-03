package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosCliente.VerHijo;
import com.anfereba.nutricionabc.FragmentosNutriologo.PlanDirario;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Hijos;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaHijosAdapter extends RecyclerView.Adapter<ListaHijosAdapter.ListaHijosViewHolder>{
    ArrayList<Hijos> listaHijos;
    String NombreActividad;
    private Context context;

    public ListaHijosAdapter(ArrayList<Hijos>listaHijos,String NombreActividad, Context context){
        this.listaHijos=listaHijos;
        this.NombreActividad = NombreActividad;
        this.context = context;
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

        if (position%2 == 0){
            holder.linearlayoutcompat.setBackgroundResource(R.drawable.fondo_gradiente_cardview_azul);
            holder.NombreHijos.setTextColor(Color.WHITE);
            holder.EstaturaHijos.setTextColor(Color.WHITE);
            holder.EdadHijos.setTextColor(Color.WHITE);
            holder.PesoHijos.setTextColor(Color.WHITE);

            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.flecha_derecha_ico);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);

        }else{
            holder.linearlayoutcompat.setBackgroundResource(R.drawable.fondo_gradiente_cardview_rojo);
            holder.NombreHijos.setTextColor(Color.WHITE);
            holder.EstaturaHijos.setTextColor(Color.WHITE);
            holder.EdadHijos.setTextColor(Color.WHITE);
            holder.PesoHijos.setTextColor(Color.WHITE);

            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.flecha_derecha_ico);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);
        }

        if(listaHijos.get(position).getIdPlanNutricional3()!=0){
            holder.AsignacionDePlan.setText("Se le ha asignado un plan");
            holder.AsignacionDePlan.setTextColor(Color.BLACK);
            holder.AsignacionDePlan.setBackgroundColor(Color.GREEN);
            }else{
            holder.AsignacionDePlan.setText("No se le ha asignado un plan");
            holder.AsignacionDePlan.setTextColor(Color.WHITE);
            holder.AsignacionDePlan.setBackgroundColor(Color.RED);
        }



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
        TextView AsignacionDePlan;
        TextView NombreHijos;
        TextView EstaturaHijos;
        TextView EdadHijos;
        TextView PesoHijos;
        CircleImageView IMGHijo;
        LinearLayoutCompat linearlayoutcompat;
        public ListaHijosViewHolder(@NonNull View itemView) {
            super(itemView);
            AsignacionDePlan= itemView. findViewById(R.id.AsignacionDePlan);
            linearlayoutcompat = itemView.findViewById(R.id.linearlayoutcompat);
            AsignacionDePlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    if(listaHijos.get(getAdapterPosition()).getIdPlanNutricional3()!=0){

                        //Toast.makeText(context,"Te han asignado un plan",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, PlanDirario.class); //si algo me toca modificarlo
                        intent.putExtra("IdHijo",listaHijos.get(getAdapterPosition()).getIdHijos());
                        intent.putExtra("IdPlanNutricional",listaHijos.get(getAdapterPosition()).getIdPlanNutricional3());
                        context.startActivity(intent);
                    }else{

                        Toast.makeText(context,"No te han asignado un plan",Toast.LENGTH_LONG).show();
                    }
                }
            });
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
                    intent.putExtra("nombreActividad",NombreActividad);

                    context.startActivity(intent);
                    //((Activity)context).finish();
                }
            });
        }
    }
}

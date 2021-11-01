package com.anfereba.nutricionabc.FragmentosNutriologo.Listas;

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

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.AsignacionPlanNutricional;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.Hijos;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaHijosSinPlan.get(position).getFotoHijos(), 0,
                listaHijosSinPlan.get(position).getFotoHijos().length);

        holder.IMGHijoSinPlan.setImageBitmap(bitmap);

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
        CircleImageView IMGHijoSinPlan;
        public ListaHijosSinPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreHijoSinPlan = itemView.findViewById(R.id.NombreHijoSinPlan);
            EdadHijoSinPlan = itemView.findViewById(R.id.EdadHijoSinPlan);
            PesoHijoSinPlan = itemView.findViewById(R.id.PesoHijoSinPlan);
            EstaturaHijoSinPlan=itemView.findViewById(R.id.EstaturaHijoSinPlan);
            IMGHijoSinPlan = itemView.findViewById(R.id.IMGHijoSinPlan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context =  view.getContext();
                    Intent intent = new Intent(context, AsignacionPlanNutricional.class); //si algo lo toca se podra modificar
                    intent.putExtra("IdHijo",listaHijosSinPlan.get(getAdapterPosition()).getIdHijos());
                    context.startActivity(intent);
                }
            });
        }
    }
}

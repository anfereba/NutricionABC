package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.HistorialPlanes;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaHistorialPlanAdapter extends RecyclerView.Adapter<ListaHistorialPlanAdapter.ListaHistorialPlanViewHolder>{
    int a=0;
    ArrayList<HistorialPlanes> ListaHistorial;
    public ListaHistorialPlanAdapter(ArrayList<HistorialPlanes>ListaHistorial){
        this.ListaHistorial=ListaHistorial;
    }
    @NonNull
    @Override
    public ListaHistorialPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_historial_planes_nutricionales,null,false);
        return new ListaHistorialPlanAdapter.ListaHistorialPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaHistorialPlanViewHolder holder, int position) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(ListaHistorial.get(position).getFotoHijos(), 0,
                ListaHistorial.get(position).getFotoHijos().length);
        holder.ImagenHistorialHijo.setImageBitmap(bitmap);
        holder.NombreHistrorialHijo.setText(ListaHistorial.get(position).getNombreHijo());
        a = ListaHistorial.get(position).getIdHijo();
        System.out.println(a);

        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(holder.itemView.getContext());
        holder.CaracteristicasHistorialPlan.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        ArrayList<HistorialPlanes> listaArrayHistorialPlan=new ArrayList<>();
        System.out.println(a+"a");
        ListaItemsHistorialPlanAdapter adapter2 =new ListaItemsHistorialPlanAdapter(dbPlanNutricional.mostrarItemsHistorialPlan(a));
        holder.CaracteristicasHistorialPlan.setAdapter(adapter2);
        /*holder.NombreHistrorialPlan.setText(ListaHistorial.get(position).getNombrePlanNutricional());
        holder.CumplimientoHistorialPlan.setText(ListaHistorial.get(position).getCumplimientoDelPlan().toString());
        holder.VistoBuenoHistorialPlan.setText(ListaHistorial.get(position).getVistoBueno().toString());*/
    }

    @Override
    public int getItemCount() {
        return ListaHistorial.size();
    }

    public class ListaHistorialPlanViewHolder extends RecyclerView.ViewHolder {
        TextView NombreHistrorialHijo,NombreHistrorialPlan,CumplimientoHistorialPlan,VistoBuenoHistorialPlan;
        CircleImageView ImagenHistorialHijo;
        RecyclerView CaracteristicasHistorialPlan;
        public ListaHistorialPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreHistrorialHijo = (TextView) itemView.findViewById(R.id.NombreHistrorialHijo);
            /*NombreHistrorialPlan = (TextView) itemView.findViewById(R.id.NombreHistrorialPlan);
            CumplimientoHistorialPlan = (TextView) itemView.findViewById(R.id.CumplimientoHistorialPlan);
            VistoBuenoHistorialPlan = (TextView) itemView.findViewById(R.id.VistoBuenoHistorialPlan);*/
            ImagenHistorialHijo = (CircleImageView) itemView.findViewById(R.id.ImagenHistorialHijo);
            SharedPreferences shared = itemView.getContext().getSharedPreferences("Sesiones",itemView.getContext().MODE_PRIVATE);
            Integer IdPerfilSistema = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
            CaracteristicasHistorialPlan = (RecyclerView) itemView.findViewById(R.id.CaracteristicasHistorialPlan);

        }

    }
}

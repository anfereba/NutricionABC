package com.anfereba.nutricionabc.FragmentosCliente.Listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosAdministrador.Adaptadores.ListaUsuariosAdapter;
import com.anfereba.nutricionabc.FragmentosAdministrador.EditarUsuarioAdministrador;
import com.anfereba.nutricionabc.FragmentosCliente.VerDetallesNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaNutriologosAdapter extends RecyclerView.Adapter<ListaNutriologosAdapter.NutriologoViewHolder> implements Filterable {
    public ArrayList<Usuario> listaNutriologos;
    private List<Usuario> listaNutriologosFiltrada;
    private Context context;

    public ListaNutriologosAdapter(ArrayList<Usuario> listaNutriologos, Context context){

        this.listaNutriologos = listaNutriologos;
        this.listaNutriologosFiltrada = new ArrayList<>(listaNutriologos);
        this.context = context;

    }

    @NonNull
    @Override
    public NutriologoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_nutriologos,null,false);
        return new ListaNutriologosAdapter.NutriologoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutriologoViewHolder holder, int position) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaNutriologos.get(position).getFotoPerfil(), 0,
                listaNutriologos.get(position).getFotoPerfil().length);

        holder.IMGCardViewNutriologo.setImageBitmap(bitmap);

        holder.nameTextViewNutriologo.setText(listaNutriologos.get(position).getNombres()+ " " +
                listaNutriologos.get(position).getApellidos());

        holder.cityTextViewNutriologo.setText(listaNutriologos.get(position).getCiudad()+ " " +
                listaNutriologos.get(position).getTelefono());



    }

    @Override
    public int getItemCount() {
        return listaNutriologos.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Usuario> listaUsuariosFiltrada = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                listaUsuariosFiltrada.addAll(listaNutriologos);
            }else{
                //Se itera la lista y se guardan las coincidencias

                for (Usuario item: listaNutriologos){
                    if ((item.getNombres().toString().toLowerCase() + " "+item.getApellidos().toString().toLowerCase())
                            .contains(charSequence.toString().toLowerCase())){
                        listaUsuariosFiltrada.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaUsuariosFiltrada;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listaNutriologos.clear();
            listaNutriologos.addAll((Collection<? extends Usuario>) filterResults.values);
            notifyDataSetChanged();


        }
    };



    public class NutriologoViewHolder extends RecyclerView.ViewHolder {

        //Se identifican los elementos

        CircleImageView IMGCardViewNutriologo;
        TextView nameTextViewNutriologo, cityTextViewNutriologo;
        ImageView Detalles_Ico_Nutriologo;

        NutriologoViewHolder(View itemView) {
            super(itemView);

            IMGCardViewNutriologo = itemView.findViewById(R.id.IMGCardViewNutriologo);
            nameTextViewNutriologo = itemView.findViewById(R.id.nameTextViewNutriologo);
            cityTextViewNutriologo = itemView.findViewById(R.id.cityTextViewNutriologo);
            Detalles_Ico_Nutriologo = itemView.findViewById(R.id.Detalles_Ico_Nutriologo);
            Detalles_Ico_Nutriologo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerDetallesNutriologo.class);
                    intent.putExtra(Utilidades.CAMPO_ID_USUARIO,listaNutriologos.get(getAdapterPosition()).getIdUsuario());
                    context.startActivity(intent);
                }
            });

        }
    }

}

package com.anfereba.nutricionabc.FragmentosAdministrador.Adaptadores;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosAdministrador.EditarUsuarioAdministrador;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuarioViewHolder> implements Filterable {


    private Context context;


    //Para almacenamiento de datos y busqueda de coincidencias

    public ArrayList<Usuario> listaUsuarios; //mData
    private List<Usuario> listaUsuariosFiltrada; //mDataAll


    public ListaUsuariosAdapter(ArrayList<Usuario> listaUsuarios, Context context){

        this.listaUsuarios = listaUsuarios;
        this.listaUsuariosFiltrada = new ArrayList<>(listaUsuarios);
        this.context = context;

    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Enlaza el adaptador con el list clientes (Asigna el diseño de cada elemento de la lista)

        View view = LayoutInflater.from(context).inflate(R.layout.list_clientes,null,false);

        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //Establece comunicacion y adaptador y viewHodler


        Bitmap bitmap = BitmapFactory.decodeByteArray(listaUsuarios.get(position).getFotoPerfil(), 0,
                listaUsuarios.get(position).getFotoPerfil().length);


        holder.IMGCardView.setImageBitmap(bitmap);

        holder.nameTextView.setText(listaUsuarios.get(position).getNombres()+ " " +
                                    listaUsuarios.get(position).getApellidos());

        holder.cityTextView.setText(listaUsuarios.get(position).getCiudad()+ " " +
                                    listaUsuarios.get(position).getTelefono());

    }

    @Override
    public int getItemCount() {
        //Retorna tamaño de la lista

        return listaUsuarios.size();
    }



    public void setItems(ArrayList<Usuario> items){
        listaUsuarios = items;
    }



    @Override
    public Filter getFilter() {
        return filter;
    }


    //Para filtrar los Usuarios del RecyclerView

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Usuario> listaUsuariosFiltrada = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                listaUsuariosFiltrada.addAll(listaUsuarios);
            }else{
                //Se itera la lista y se guardan las coincidencias

                for (Usuario item: listaUsuarios){
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
            listaUsuarios.clear();
            listaUsuarios.addAll((Collection<? extends Usuario>) filterResults.values);
            notifyDataSetChanged();


        }
    };

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        //Se identifican los elementos

        CircleImageView IMGCardView;
        TextView nameTextView, cityTextView;
        ImageView Detalles_Ico;

        UsuarioViewHolder(View itemView) {
            super(itemView);
            IMGCardView = itemView.findViewById(R.id.IMGCardView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            Detalles_Ico = itemView.findViewById(R.id.Detalles_Ico);
            Detalles_Ico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, EditarUsuarioAdministrador.class);
                    intent.putExtra(Utilidades.CAMPO_ID_USUARIO,listaUsuarios.get(getAdapterPosition()).getIdUsuario());
                    context.startActivity(intent);

                }
            });
        }
    }
}

package com.anfereba.nutricionabc.FragmentosAdministrador.Listas;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Validator.ValidationListener, Filterable {

    @NotEmpty
    @Email
    EditText TXTActualizarCorreoUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarNombreUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarApellidoUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarDireccionUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarCiudadUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarTelefonoUsuario;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarFechaNacimientoUsuario;

    //Para validacion de Campos

    private boolean DatosValidados;
    private Validator validator;

    //Calendario

    DatePickerDialog picker;

    //Para almacenamiento de datos y busqueda de coincidencias

    private List<Usuario> mData;
    private ArrayList<Usuario> mDataAll;

    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Usuario> itemList, Context context){

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.mDataAll = new ArrayList<>(mData);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Enlaza el adaptador con el list clientes

        View view = mInflater.inflate(R.layout.list_clientes, null);
        validator = new Validator(this);
        validator.setValidationListener(this);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //Establece comunicacion y adaptador y viewHodler

        holder.bindData(mData.get(position));
        holder.Detalles_Ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(position);



            }
        });

    }



    private void showDialog(int position) {

        //Muestra una ventana emergente con los datos del usuario seleccionado

        Button BtnActualizarClienteBD;
        Button BtnEliminarClienteBD;

        int Id_Usuario;

        final Dialog dialog = new Dialog(context);

        //Personalizacion del Dialog

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialog);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        //Inicializamos los elementos del Dialog

        TXTActualizarNombreUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarNombreUsuario);
        TXTActualizarApellidoUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarApellidoUsuario);
        TXTActualizarFechaNacimientoUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarFechaNacimientoUsuario);
        TXTActualizarCorreoUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarCorreoUsuario);
        TXTActualizarDireccionUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarDireccionUsuario);
        TXTActualizarCiudadUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarCiudadUsuario);
        TXTActualizarTelefonoUsuario = (EditText) dialog.findViewById(R.id.TXTActualizarTelefonoUsuario);

        BtnActualizarClienteBD = (Button) dialog.findViewById(R.id.BtnActualizarClienteBD);
        BtnEliminarClienteBD = (Button) dialog.findViewById(R.id.BtnEliminarrClienteBD);

        //Se recogen los valores almacenados en el ArrayList y se asignan en los campos del dialog

        TXTActualizarNombreUsuario.setText(mData.get(position).getNombres());
        TXTActualizarApellidoUsuario.setText(mData.get(position).getApellidos());
        TXTActualizarFechaNacimientoUsuario.setText(mData.get(position).getFechaNacimiento());
        TXTActualizarCorreoUsuario.setText(mData.get(position).getCorreo());
        TXTActualizarDireccionUsuario.setText(mData.get(position).getDireccion());
        TXTActualizarCiudadUsuario.setText(mData.get(position).getCiudad());
        TXTActualizarTelefonoUsuario.setText(mData.get(position).getTelefono());

        //Variable Auxiliar para actualizar el email

        String AuxCorreoUsuario = mData.get(position).getCorreo();

        //Variable donde se almacena el id del usuario para ejecutar la sentencia UPDATE
        Id_Usuario = mData.get(position).getIdUsuario();

        //Llamado al calendario tras hacer click en el campo de fecha
        TXTActualizarFechaNacimientoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarFechaDeNacimiento();
            }
        });

        //Se actualizan los datos del cliente

        BtnActualizarClienteBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();

                //Si todos los campos estan validados

                if (DatosValidados){
                    DbUsuario db = new DbUsuario(context);

                    //Si el usuario no desea cambiar su email actual

                    if (AuxCorreoUsuario.equals(TXTActualizarCorreoUsuario.getText().toString())){


                        // Se omite el metodo para la validacion de Correo

                        db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                                TXTActualizarApellidoUsuario.getText().toString(),
                                TXTActualizarFechaNacimientoUsuario.getText().toString(),
                                TXTActualizarCorreoUsuario.getText().toString(),
                                TXTActualizarDireccionUsuario.getText().toString(),
                                TXTActualizarCiudadUsuario.getText().toString(),
                                TXTActualizarTelefonoUsuario.getText().toString(),
                                Id_Usuario);

                        mData.get(position).setNombres(TXTActualizarNombreUsuario.getText().toString());
                        mData.get(position).setApellidos(TXTActualizarApellidoUsuario.getText().toString());
                        mData.get(position).setCiudad(TXTActualizarCiudadUsuario.getText().toString());
                        mData.get(position).setCorreo(TXTActualizarCorreoUsuario.getText().toString());

                        dialog.cancel();
                        notifyItemChanged(position);


                    }else{

                        //Se valida si el correo nuevo no se encuentra registrado en la BD

                        if (!db.Comprobar_Correo(TXTActualizarCorreoUsuario.getText().toString())){

                            //Se ejecuta el UPDATE

                            db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                                    TXTActualizarApellidoUsuario.getText().toString(),
                                    TXTActualizarFechaNacimientoUsuario.getText().toString(),
                                    TXTActualizarCorreoUsuario.getText().toString(),
                                    TXTActualizarDireccionUsuario.getText().toString(),
                                    TXTActualizarCiudadUsuario.getText().toString(),
                                    TXTActualizarTelefonoUsuario.getText().toString(),
                                    Id_Usuario);

                            //Se actualiza los detalles mostrados en el RecyclerView

                            mData.get(position).setNombres(TXTActualizarNombreUsuario.getText().toString());
                            mData.get(position).setApellidos(TXTActualizarApellidoUsuario.getText().toString());
                            mData.get(position).setCiudad(TXTActualizarCiudadUsuario.getText().toString());
                            mData.get(position).setCorreo(TXTActualizarCorreoUsuario.getText().toString());

                            dialog.cancel();
                            notifyItemChanged(position);
                            notifyDataSetChanged();

                        }else{
                            Toast.makeText(context, "Este correo ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });

        //Boton para eliminar el cliente

        BtnEliminarClienteBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuario db = new DbUsuario(context);
                db.eliminarUsuario(Integer.toString(Id_Usuario));
                dialog.cancel();
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Retorna tamaño de la lista
        return mData.size();
    }

    //Para asignar los items al ArrayList

    public void setItems(List<Usuario> items){ mData = items;}


    //Si la validacion fue exitosa
    @Override
    public void onValidationSucceeded() {
        DatosValidados = true;

    }

    //Si la validacion fue fallida
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        DatosValidados = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(context);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }

        }

    }

    //Metodo para mostrar calendario para actualizar la fecha de nacimiento

    public void ActualizarFechaDeNacimiento() {

        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);

        //Date Picker Dialog
        picker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                TXTActualizarFechaNacimientoUsuario.setText(year + "-"+month+"-"+day);
            }
        },año,mes,dia);
        picker.show();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    //Para filtrar los Usuarios del RecyclerView

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Usuario> listaUsuariosFiltrada = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                listaUsuariosFiltrada.addAll(mDataAll);
            }else{
                //Se itera la lista y se guardan las coincidencias

                for (Usuario item: mDataAll){
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
            mData.clear();
            mData.addAll((Collection<? extends Usuario>) filterResults.values);
            notifyDataSetChanged();


        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IMGCardView;
        TextView nameTextView, cityTextView;
        ImageView Detalles_Ico;

        ViewHolder(View itemView) {
            super(itemView);
            IMGCardView = itemView.findViewById(R.id.IMGCardView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            Detalles_Ico = itemView.findViewById(R.id.Detalles_Ico);
        }

        void bindData(final Usuario item) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(item.getFotoPerfil(), 0, item.getFotoPerfil().length);

            IMGCardView.setImageBitmap(bitmap);
            nameTextView.setText(item.getNombres() + " " + item.getApellidos());
            cityTextView.setText(item.getCiudad() + " - " + item.getCorreo());
            // statusTextView.setText(item.getCorreo());
        }

    }
}

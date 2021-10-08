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

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
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

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuarioViewHolder> implements Validator.ValidationListener,Filterable {

    CircleImageView ActualizarFotoCliente;

    int CODIGO_DE_SOLICITUD_IMAGEN = 1;

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

    private Context context;

    //Calendario

    DatePickerDialog picker;

    //Para almacenamiento de datos y busqueda de coincidencias

    private ArrayList<Usuario> listaUsuarios; //mData
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

        validator = new Validator(this);
        validator.setValidationListener(this);

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


        holder.Detalles_Ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogActualizar(position, context);
            }
        });
    }





    private void mostrarDialogActualizar(int position, Context context) {

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
        ActualizarFotoCliente = (CircleImageView) dialog.findViewById(R.id.ActualizarFotoUsuario);


        BtnActualizarClienteBD = (Button) dialog.findViewById(R.id.BtnActualizarClienteBD);
        BtnEliminarClienteBD = (Button) dialog.findViewById(R.id.BtnEliminarrClienteBD);

        //Se recogen los valores almacenados en el ArrayList y se asignan en los campos del dialog

        TXTActualizarNombreUsuario.setText(listaUsuarios.get(position).getNombres());
        TXTActualizarApellidoUsuario.setText(listaUsuarios.get(position).getApellidos());
        TXTActualizarFechaNacimientoUsuario.setText(listaUsuarios.get(position).getFechaNacimiento());
        TXTActualizarCorreoUsuario.setText(listaUsuarios.get(position).getCorreo());
        TXTActualizarDireccionUsuario.setText(listaUsuarios.get(position).getDireccion());
        TXTActualizarCiudadUsuario.setText(listaUsuarios.get(position).getCiudad());
        TXTActualizarTelefonoUsuario.setText(listaUsuarios.get(position).getTelefono());


        //Asignamos imagen al dialog

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaUsuarios.get(position).getFotoPerfil(), 0,
                listaUsuarios.get(position).getFotoPerfil().length);
        ActualizarFotoCliente.setImageBitmap(bitmap);

        //Variable Auxiliar para actualizar el email

        String AuxCorreoUsuario = listaUsuarios.get(position).getCorreo();

        //Variable donde se almacena el id del usuario para ejecutar la sentencia UPDATE
        Id_Usuario = listaUsuarios.get(position).getIdUsuario();

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

                        listaUsuarios.get(position).setNombres(TXTActualizarNombreUsuario.getText().toString());
                        listaUsuarios.get(position).setApellidos(TXTActualizarApellidoUsuario.getText().toString());
                        listaUsuarios.get(position).setCiudad(TXTActualizarCiudadUsuario.getText().toString());
                        listaUsuarios.get(position).setCorreo(TXTActualizarCorreoUsuario.getText().toString());

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

                            listaUsuarios.get(position).setNombres(TXTActualizarNombreUsuario.getText().toString());
                            listaUsuarios.get(position).setApellidos(TXTActualizarApellidoUsuario.getText().toString());
                            listaUsuarios.get(position).setCiudad(TXTActualizarCiudadUsuario.getText().toString());
                            listaUsuarios.get(position).setCorreo(TXTActualizarCorreoUsuario.getText().toString());

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

        return listaUsuarios.size();
    }



    public void setItems(ArrayList<Usuario> items){
        listaUsuarios = items;
    }


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

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {

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
        }
    }
}

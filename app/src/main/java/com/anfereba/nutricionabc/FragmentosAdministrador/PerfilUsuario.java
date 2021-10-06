package com.anfereba.nutricionabc.FragmentosAdministrador;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilUsuario extends Fragment implements Validator.ValidationListener{

    ArrayList<Usuario> listaArrayUsuarios;
    DbUsuario db;
    int IdUsuario;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    TextView PerfilNombresyApellidos, PerfilFechaNacimiento, PerfilNumeroTelefono, PerfilCorreo, PerfilDireccion, PerfilCiudad;
    CircleImageView PerfilImagen;
    ImageView OpcionActualizarDatosPerfil, OpcionActualizarPasswordPerfil;


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_usuario, container, false);

        IdUsuario = ObtenerIdUsuarioActual();
        db = new DbUsuario(getActivity());
        listaArrayUsuarios = new ArrayList<>(db.ObtenerDatosUsuario(IdUsuario));

        //Inicializacion de elementos

        PerfilNombresyApellidos = view.findViewById(R.id.PerfilNombresyApellidos);
        PerfilFechaNacimiento = view.findViewById(R.id.PerfilFechaNacimiento);
        PerfilNumeroTelefono = view.findViewById(R.id.PerfilNumeroTelefono);
        PerfilCorreo = view.findViewById(R.id.PerfilCorreo);
        PerfilDireccion = view.findViewById(R.id.PerfilDireccion);
        PerfilImagen = view.findViewById(R.id.PerfilImagen);
        PerfilCiudad = view.findViewById(R.id.PerfilCiudad);

        OpcionActualizarDatosPerfil = view.findViewById(R.id.OpcionActualizarDatosPerfil);
        OpcionActualizarPasswordPerfil = view.findViewById(R.id.OpcionActualizarPasswordPerfil);

        validator = new Validator(this);
        validator.setValidationListener((Validator.ValidationListener) this);

        SetearDatosPerfilAVista();

        OpcionActualizarDatosPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogActualizar();
            }
        });

        return view;
    }

    private int ObtenerIdUsuarioActual() {

        preferences = getActivity().getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();
        int IdUsuario = preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        return IdUsuario;
    }

    private void SetearDatosPerfilAVista(){

        PerfilNombresyApellidos.setText(listaArrayUsuarios.get(0).getNombres() + " " + listaArrayUsuarios.get(0).getApellidos());
        PerfilFechaNacimiento.setText(listaArrayUsuarios.get(0).getFechaNacimiento());
        PerfilCorreo.setText(listaArrayUsuarios.get(0).getCorreo());
        PerfilDireccion.setText(listaArrayUsuarios.get(0).getDireccion());
        PerfilNumeroTelefono.setText(listaArrayUsuarios.get(0).getTelefono());
        PerfilCiudad.setText(listaArrayUsuarios.get(0).getCiudad());

        //Setear Imagen

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaArrayUsuarios.get(0).getFotoPerfil(),0,
                listaArrayUsuarios.get(0).getFotoPerfil().length);

        PerfilImagen.setImageBitmap(bitmap);
    }

    private void mostrarDialogActualizar() {

        //Muestra una ventana emergente con los datos del usuario seleccionado

        Button BtnActualizarClienteBD;
        Button BtnEliminarClienteBD;

        int Id_Usuario;

        final Dialog dialog = new Dialog(getActivity());

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
        BtnEliminarClienteBD.setVisibility(View.GONE);

        //Se recogen los valores almacenados en el ArrayList y se asignan en los campos del dialog

        TXTActualizarNombreUsuario.setText(listaArrayUsuarios.get(0).getNombres());
        TXTActualizarApellidoUsuario.setText(listaArrayUsuarios.get(0).getApellidos());
        TXTActualizarFechaNacimientoUsuario.setText(listaArrayUsuarios.get(0).getFechaNacimiento());
        TXTActualizarCorreoUsuario.setText(listaArrayUsuarios.get(0).getCorreo());
        TXTActualizarDireccionUsuario.setText(listaArrayUsuarios.get(0).getDireccion());
        TXTActualizarCiudadUsuario.setText(listaArrayUsuarios.get(0).getCiudad());
        TXTActualizarTelefonoUsuario.setText(listaArrayUsuarios.get(0).getTelefono());

        //Variable Auxiliar para actualizar el email

        String AuxCorreoUsuario = listaArrayUsuarios.get(0).getCorreo();

        //Variable donde se almacena el id del usuario para ejecutar la sentencia UPDATE
        Id_Usuario = listaArrayUsuarios.get(0).getIdUsuario();

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
                    DbUsuario db = new DbUsuario(getActivity());

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

                        dialog.cancel();

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


                            dialog.cancel();

                        }else{
                            Toast.makeText(getActivity(), "Este correo ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });

    }

    //Metodo para mostrar calendario para actualizar la fecha de nacimiento

    public void ActualizarFechaDeNacimiento() {

        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);

        //Date Picker Dialog
        picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                TXTActualizarFechaNacimientoUsuario.setText(year + "-"+month+"-"+day);
            }
        },año,mes,dia);
        picker.show();
    }

    @Override
    public void onValidationSucceeded() {
        DatosValidados = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        DatosValidados = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

        }
    }
}
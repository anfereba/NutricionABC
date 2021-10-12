package com.anfereba.nutricionabc;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
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

import com.anfereba.nutricionabc.ActualizarPerfil;
import com.anfereba.nutricionabc.CambiarCredenciales;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilUsuario extends Fragment{

    ArrayList<Usuario> listaArrayUsuarios;
    DbUsuario db;
    int IdUsuario;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    TextView PerfilNombresyApellidos, PerfilFechaNacimiento, PerfilNumeroTelefono, PerfilCorreo, PerfilDireccion, PerfilCiudad;
    CircleImageView PerfilImagen, ActualizarFotoUsuario;
    ImageView OpcionActualizarDatosPerfil, OpcionActualizarPasswordPerfil;




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

        SetearDatosPerfilAVista();

        OpcionActualizarDatosPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ActualizarPerfil.class);
                startActivity(intent);
            }
        });

        OpcionActualizarPasswordPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CambiarCredenciales.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private int ObtenerIdUsuarioActual() {
        int IdUsuario = 0;
        preferences = getActivity().getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Log.i("Usuario Anterior: ",String.valueOf(preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0)));
        IdUsuario = preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        Log.i("Usuario Despues: ",String.valueOf(IdUsuario));
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


}
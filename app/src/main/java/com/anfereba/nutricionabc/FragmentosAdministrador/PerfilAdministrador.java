package com.anfereba.nutricionabc.FragmentosAdministrador;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilAdministrador extends Fragment {

    ArrayList<Usuario> listaArrayUsuarios;
    DbUsuario db;
    int IdUsuario;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    TextView PerfilNombresyApellidos, PerfilFechaNacimiento, PerfilNumeroTelefono, PerfilCorreo, PerfilDireccion;
    CircleImageView PerfilImagen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_administrador, container, false);

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

        SetearDatosPerfilAVista();

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

        //Setear Imagen

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaArrayUsuarios.get(0).getFotoPerfil(),0,
                listaArrayUsuarios.get(0).getFotoPerfil().length);

        PerfilImagen.setImageBitmap(bitmap);
    }

}
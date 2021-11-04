package com.anfereba.nutricionabc.FragmentosCliente;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

public class InformacionNutriologoCliente extends Fragment {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacion_nutriologo_cliente, container, false);

        preferences = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);
        editor = preferences.edit();
        SharedPreferences shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);
        int IdUsuario = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        DbUsuario dbUsuario = new DbUsuario(getActivity());

        Button btnVerNutriologos = (Button) view.findViewById(R.id.BtnVerNutriologos);

        btnVerNutriologos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PerfilUsuario = dbUsuario.Comprobar_Perfil(IdUsuario);
                if (PerfilUsuario.equals("Cliente")){
                    startActivity(new Intent(getActivity(),VerNutriologos.class));
                } else{
                    Intent intent = new Intent(getActivity(), VerDetallesNutriologo.class);
                    intent.putExtra(Utilidades.CAMPO_ID_USUARIO,IdUsuario);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
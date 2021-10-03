package com.anfereba.nutricionabc.FragmentosAdministrador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anfereba.nutricionabc.FragmentosAdministrador.Listas.ListAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;


public class OpcionUnoAdministrador extends Fragment {

    Button AccederCRUDUsuarios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_opcion_uno_administrador, container, false);

        AccederCRUDUsuarios=view.findViewById(R.id.AccederCRUDUsuarios);
        AccederCRUDUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GestionUsuarios.class));
            }
        });

        return view;
    }
}
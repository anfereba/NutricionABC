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


public class OpcionUnoAdministrador extends Fragment implements View.OnClickListener{

    Button AccederCRUDUsuarios, OperacionInsertarUsuarios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_opcion_uno_administrador, container, false);

        AccederCRUDUsuarios=view.findViewById(R.id.AccederCRUDUsuarios);
        OperacionInsertarUsuarios=view.findViewById(R.id.OperacionInsertarUsuarios);

        AccederCRUDUsuarios.setOnClickListener(this);
        OperacionInsertarUsuarios.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AccederCRUDUsuarios:
                startActivity(new Intent(getActivity(), GestionUsuarios.class));
                break;
            case R.id.OperacionInsertarUsuarios:
                startActivity(new Intent(getActivity(), InsertarUsuarioAdministrador.class));
                break;
                
        }

    }
}
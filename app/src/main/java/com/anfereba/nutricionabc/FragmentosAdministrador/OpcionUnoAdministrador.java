package com.anfereba.nutricionabc.FragmentosAdministrador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anfereba.nutricionabc.FragmentosAdministrador.Listas.ListAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;


public class OpcionUnoAdministrador extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_opcion_uno_administrador, container, false);

        DbUsuario db = new DbUsuario(getActivity());
        ListAdapter listAdapter = new ListAdapter(db.ConsultarListaClientes(), getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);

        return view;
    }
}
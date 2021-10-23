package com.anfereba.nutricionabc.FragmentosNutriologo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaAlimentosAdapter;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.R;

import java.util.ArrayList;

public class OpcionTresNutriologo extends Fragment {

    ListaAlimentosAdapter adapter;
    View view;
    Button AgregarAlimento;
    RecyclerView listaAlimentos;
    DbAlimento dbAlimento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_opcion_tres_nutriologo, container, false);

        AgregarAlimento = (Button) view.findViewById(R.id.AgregarAlimento);
        listaAlimentos = (RecyclerView) view.findViewById(R.id.listaAlimentos);
        listaAlimentos.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        dbAlimento = new DbAlimento(getActivity());

        adapter =new ListaAlimentosAdapter(dbAlimento.mostrarAlimentos());
        listaAlimentos.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        AgregarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegistrarAlimento.class));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter =new ListaAlimentosAdapter(dbAlimento.mostrarAlimentos());
        listaAlimentos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
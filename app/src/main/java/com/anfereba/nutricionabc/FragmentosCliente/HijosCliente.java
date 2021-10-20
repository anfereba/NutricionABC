package com.anfereba.nutricionabc.FragmentosCliente;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;


public class HijosCliente extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_hijos_cliente, container, false);
        Button AgregarHijo = (Button) v.findViewById(R.id.AgregarHijo);
        RecyclerView listaHijos = (RecyclerView) v.findViewById(R.id.listaHijos);
        listaHijos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        DbHijo dbHijo = new DbHijo(getContext());
        ArrayList<Hijos> listaArrayHijos = new ArrayList<>();
        SharedPreferences shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);//Llamar id del cliente.
        Integer iDUsuario = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );//Llamar id del cliente.

        ListaHijosAdapter adapter =new ListaHijosAdapter(dbHijo.mostrarHijos(iDUsuario));

        listaHijos.setAdapter(adapter);


        AgregarHijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegistrarHijo.class));
                //getActivity().finish();
            }
        });
        return v;
    }
}
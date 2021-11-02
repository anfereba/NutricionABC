package com.anfereba.nutricionabc.FragmentosCliente;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHistorialPlanAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaHijosSinPlanAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.HistorialPlanes;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;


public class OpcionTresCliente extends Fragment {
    View view;
    DbPlanNutricional dbPlanNutricional;
    RecyclerView listaHistorialPlan;
    ListaHistorialPlanAdapter adapter;
    SharedPreferences shared;
    Integer iDUsuario;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_opcion_tres_cliente, container, false);
        shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);//Llamar id del nutriologo.
        iDUsuario = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );//Llamar id del nutriologo.
        dbPlanNutricional = new DbPlanNutricional(getContext());
        listaHistorialPlan = (RecyclerView)view.findViewById(R.id.RecyclerViewHistorialPlan);
        listaHistorialPlan.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<HistorialPlanes> listaArrayHistorialPlan=new ArrayList<>();
        adapter =new ListaHistorialPlanAdapter(dbPlanNutricional.mostrarHistorialPlan(iDUsuario));
        listaHistorialPlan.setAdapter(adapter);
        return view;
    }
}
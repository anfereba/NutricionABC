package com.anfereba.nutricionabc.FragmentosNutriologo;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaAlimentosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaHijosSinPlanAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;


public class OpcionUnoNutriologo extends Fragment {
    ListaHijosSinPlanAdapter adapter;
    ListaHijosAdapter adapter2;
    View view;
    RecyclerView listaHijosSinPlan,listaHijos;
    DbHijo dbHijo;
    SharedPreferences shared;
    Integer iDNutriologo;
    String NombreActividad = this.getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_opcion_uno_nutriologo, container, false);
        dbHijo = new DbHijo(getContext());
        listaHijosSinPlan = (RecyclerView)view.findViewById(R.id.ListaHijosSinPlan);
        listaHijosSinPlan.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        ArrayList<Hijos>listaArrayHijosSinPlan=new ArrayList<>();
        Integer iDUsuario=0;
        ListaHijosSinPlanAdapter adapter =new ListaHijosSinPlanAdapter(dbHijo.mostrarTodosLosHijosQueNoTienenPlan(iDUsuario));
        listaHijosSinPlan.setAdapter(adapter);


        shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);//Llamar id del nutriologo.
        iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );//Llamar id del nutriologo.
        listaHijos = (RecyclerView) view.findViewById(R.id.ListaHijosNutriologo);
        listaHijos.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ArrayList<Hijos> listaArrayHijos = new ArrayList<>();
        adapter2 =new ListaHijosAdapter(dbHijo.mostrarTodosLosHijosDelNutriologo(iDNutriologo),NombreActividad,getContext());
        listaHijos.setAdapter(adapter2);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        Integer iDUsuario=0;
        adapter =new ListaHijosSinPlanAdapter(dbHijo.mostrarTodosLosHijosQueNoTienenPlan(iDUsuario));
        listaHijosSinPlan.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter2 =new ListaHijosAdapter(dbHijo.mostrarTodosLosHijosDelNutriologo(iDNutriologo),NombreActividad,getContext());
        listaHijos.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
    }
}
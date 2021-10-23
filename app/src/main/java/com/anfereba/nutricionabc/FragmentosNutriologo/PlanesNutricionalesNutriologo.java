package com.anfereba.nutricionabc.FragmentosNutriologo;

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

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesNutricionalesAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;


public class PlanesNutricionalesNutriologo extends Fragment {

    View v;
    Button button;
    RecyclerView listaPlanes;
    DbPlanNutricional dbPlanNutricional;
    ArrayList<PlanesNutricionales> listaArrayPlanes;
    SharedPreferences shared;
    Integer iDNutriologo;
    ListaPlanesNutricionalesAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_planes_nutricionales_nutriologo, container, false);
        button= v.findViewById(R.id.AgregarPlan);
        listaPlanes = v.findViewById(R.id.listaPlanes);
        listaPlanes.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        dbPlanNutricional = new DbPlanNutricional(getContext());
        listaArrayPlanes = new ArrayList<>();
        shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);//Llamar id del nutriologo.
        iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );//Llamar id del nutriologo.

        adapter =new ListaPlanesNutricionalesAdapter(dbPlanNutricional.mostrarPlan(iDNutriologo));
        listaPlanes.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegistrarPlanNutricional.class));
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter =new ListaPlanesNutricionalesAdapter(dbPlanNutricional.mostrarPlan(iDNutriologo));
        listaPlanes.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
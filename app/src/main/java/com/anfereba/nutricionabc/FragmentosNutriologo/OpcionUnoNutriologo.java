package com.anfereba.nutricionabc.FragmentosNutriologo;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaHijosSinPlanAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionUnoNutriologo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionUnoNutriologo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionUnoNutriologo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionUnoNutriologo.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionUnoNutriologo newInstance(String param1, String param2) {
        OpcionUnoNutriologo fragment = new OpcionUnoNutriologo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_opcion_uno_nutriologo, container, false);
        DbHijo dbHijo = new DbHijo(getContext());
        RecyclerView listaHijosSinPlan = (RecyclerView)v.findViewById(R.id.ListaHijosSinPlan);
        listaHijosSinPlan.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Hijos>listaArrayHijosSinPlan=new ArrayList<>();
        Integer iDUsuario=0;
        ListaHijosSinPlanAdapter adapter =new ListaHijosSinPlanAdapter(dbHijo.mostrarTodosLosHijosQueNoTienenPlan(iDUsuario));
        listaHijosSinPlan.setAdapter(adapter);


        SharedPreferences shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);//Llamar id del nutriologo.
        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );//Llamar id del nutriologo.
        RecyclerView listaHijos = (RecyclerView) v.findViewById(R.id.ListaHijosNutriologo);
        listaHijos.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ArrayList<Hijos> listaArrayHijos = new ArrayList<>();
        ListaHijosAdapter adapter2 =new ListaHijosAdapter(dbHijo.mostrarTodosLosHijosDelNutriologo(iDNutriologo));
        listaHijos.setAdapter(adapter2);

        return v;
    }
}
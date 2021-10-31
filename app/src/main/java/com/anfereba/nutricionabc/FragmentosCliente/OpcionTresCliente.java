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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionTresCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionTresCliente extends Fragment {
    View view;
    DbPlanNutricional dbPlanNutricional;
    RecyclerView listaHistorialPlan;
    ListaHistorialPlanAdapter adapter;
    SharedPreferences shared;
    Integer iDUsuario;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionTresCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionTresCliente.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionTresCliente newInstance(String param1, String param2) {
        OpcionTresCliente fragment = new OpcionTresCliente();
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
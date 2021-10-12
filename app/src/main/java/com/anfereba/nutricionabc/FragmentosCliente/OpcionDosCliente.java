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

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesNutricionalesAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.RegistrarPlanNutricional;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionDosCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionDosCliente extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionDosCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionDosCliente.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionDosCliente newInstance(String param1, String param2) {
        OpcionDosCliente fragment = new OpcionDosCliente();
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
        View v=inflater.inflate(R.layout.fragment_opcion_dos_cliente, container, false);
        Button AgregarHijo = (Button) v.findViewById(R.id.AgregarHijo);
        RecyclerView listaHijos = (RecyclerView) v.findViewById(R.id.listaHijos);
        listaHijos.setLayoutManager(new LinearLayoutManager(getContext()));

        DbHijo dbHijo = new DbHijo(getContext());
        ArrayList<Hijos> listaArrayHijos = new ArrayList<>();
        SharedPreferences shared = getActivity().getSharedPreferences("Sesiones", MODE_PRIVATE);//Llamar id del cliente.
        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );//Llamar id del cliente.

        ListaHijosAdapter adapter =new ListaHijosAdapter(dbHijo.mostrarHijos(iDNutriologo));

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
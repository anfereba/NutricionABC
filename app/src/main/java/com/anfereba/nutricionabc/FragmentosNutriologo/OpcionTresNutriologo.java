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
import com.anfereba.nutricionabc.RegistrarAlimento;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.utilidades.DbAlimento;
import com.anfereba.nutricionabc.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionTresNutriologo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionTresNutriologo extends Fragment {
    Button button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionTresNutriologo() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionTresNutriologo.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionTresNutriologo newInstance(String param1, String param2) {
        OpcionTresNutriologo fragment = new OpcionTresNutriologo();
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
        View v=inflater.inflate(R.layout.fragment_opcion_tres_nutriologo, container, false);
        Button button= (Button) v.findViewById(R.id.AgregarAlimento);
        RecyclerView listaAlimentos = (RecyclerView) v.findViewById(R.id.listaAlimentos);
        listaAlimentos.setLayoutManager(new LinearLayoutManager(getActivity()));

        DbAlimento dbAlimento = new DbAlimento(getActivity());
        ArrayList<Alimentos>listaArrayAlimentos = new ArrayList<>();
        ListaAlimentosAdapter adapter =new ListaAlimentosAdapter(dbAlimento.mostrarAlimentos());
        listaAlimentos.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegistrarAlimento.class));
            }
        });
        return v;
    }
}
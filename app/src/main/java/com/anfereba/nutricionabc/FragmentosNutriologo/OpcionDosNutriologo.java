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

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesNutricionalesAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.DbPlanNutricional;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionDosNutriologo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionDosNutriologo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionDosNutriologo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionDosNutriologo.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionDosNutriologo newInstance(String param1, String param2) {
        OpcionDosNutriologo fragment = new OpcionDosNutriologo();
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
        View v= inflater.inflate(R.layout.fragment_opcion_dos_nutriologo, container, false);
        Button button= (Button) v.findViewById(R.id.AgregarPlan);
        RecyclerView listaPlanes = (RecyclerView) v.findViewById(R.id.listaPlanes);
        listaPlanes.setLayoutManager(new LinearLayoutManager(getContext()));

        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(getContext());
        ArrayList<PlanesNutricionales> listaArrayPlanes = new ArrayList<>();
        ListaPlanesNutricionalesAdapter adapter =new ListaPlanesNutricionalesAdapter(dbPlanNutricional.mostrarPlan());
        listaPlanes.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegistrarPlanNutricional.class));
            }
        });
        return v;
    }
}
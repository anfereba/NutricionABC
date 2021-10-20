package com.anfereba.nutricionabc.FragmentosCliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anfereba.nutricionabc.R;

public class InformacionNutriologoCliente extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacion_nutriologo_cliente, container, false);

        Button btnVerNutriologos = (Button) view.findViewById(R.id.BtnVerNutriologos);
        btnVerNutriologos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),VerNutriologos.class));
            }
        });
        return view;
    }
}
package com.anfereba.nutricionabc.FragmentosAdministrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.anfereba.nutricionabc.FragmentosAdministrador.Listas.ListAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;

public class GestionUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);
        mostrarListaUsuarios();


    }
    public void mostrarListaUsuarios(){

        DbUsuario db = new DbUsuario(this);
        ListAdapter listAdapter = new ListAdapter(db.ConsultarListaClientes(), this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    };

}
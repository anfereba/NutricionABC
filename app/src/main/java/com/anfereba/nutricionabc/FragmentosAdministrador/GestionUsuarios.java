package com.anfereba.nutricionabc.FragmentosAdministrador;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.anfereba.nutricionabc.FragmentosAdministrador.Adaptadores.ListaUsuariosAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;

import java.util.ArrayList;


public class GestionUsuarios extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    Dialog dialog;
    RecyclerView listaUsuariosRV;

    ListaUsuariosAdapter adapter;
    ArrayList<Usuario> listaArrayUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);



        dialog = new Dialog(GestionUsuarios.this);

        mostrarListaUsuarios();
        SetearActionBar();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mostrarListaUsuarios();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void mostrarListaUsuarios() {
        listaUsuariosRV = findViewById(R.id.listaUsuariosRV);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        listaUsuariosRV.setLayoutManager(new LinearLayoutManager(this));
        DbUsuario db = new DbUsuario(GestionUsuarios.this);
        listaArrayUsuarios = new ArrayList<>();
        adapter = new ListaUsuariosAdapter(db.ConsultarListaUsuarios(),this);
        listaUsuariosRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void SetearActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Listado de Clientes");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_buscar,menu);
        MenuItem item = menu.findItem(R.id.BuscarUsuario);
        SearchView searchView = (SearchView) item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
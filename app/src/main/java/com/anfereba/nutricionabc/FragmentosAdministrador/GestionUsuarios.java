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


import com.anfereba.nutricionabc.FragmentosAdministrador.Listas.ListAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;



public class GestionUsuarios extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    Dialog dialog;
    ListAdapter listAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
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
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    public void mostrarListaUsuarios(){
        DbUsuario db = new DbUsuario(this);
        listAdapter = new ListAdapter(db.ConsultarListaClientes(), this);
        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

}
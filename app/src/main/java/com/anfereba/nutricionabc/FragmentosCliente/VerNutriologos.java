package com.anfereba.nutricionabc.FragmentosCliente;

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

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaNutriologosAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;

import java.util.ArrayList;

public class VerNutriologos extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;
    Dialog dialog;
    RecyclerView listaNutriologosRV;

    ListaNutriologosAdapter adapter;
    ArrayList<Usuario> listaArrayNutriologos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nutriologos);

        dialog = new Dialog(VerNutriologos.this);

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
        listaNutriologosRV = findViewById(R.id.listaNutriologosRV);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        listaNutriologosRV.setLayoutManager(new LinearLayoutManager(this));
        DbUsuario db = new DbUsuario(VerNutriologos.this);
        listaArrayNutriologos = new ArrayList<>();
        adapter = new ListaNutriologosAdapter(db.ConsultarListaNutriologos(),this);
        listaNutriologosRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void SetearActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Listado de Nutriologos");
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
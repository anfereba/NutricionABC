package com.anfereba.nutricionabc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.anfereba.nutricionabc.FragmentosCliente.OpcionCuatroCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionDosCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionTresCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionUnoCliente;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.google.android.material.navigation.NavigationView;

import okhttp3.internal.Util;

public class MainActivityCliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);

        Toolbar toolbar = findViewById(R.id.toolbarC);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_C);

        NavigationView navigationView = findViewById(R.id.nav_viewC);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new OpcionUnoCliente()).commit();
            navigationView.setCheckedItem(R.id.Opcion_uno_Cliente);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Opcion_uno_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new OpcionUnoCliente()).commit();
                break;
            case R.id.Opcion_dos_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new OpcionDosCliente()).commit();
                break;
            case R.id.Opcion_tres_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new OpcionTresCliente()).commit();
                break;
            case R.id.Opcion_cuatro_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new OpcionCuatroCliente()).commit();
                break;
            case R.id.Salir:
                CerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }

    private void CerrarSesion() {
        String x = Utilidades.CAMPO_APELLIDOS;
    }
}
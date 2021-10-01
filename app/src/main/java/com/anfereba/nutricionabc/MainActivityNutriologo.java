package com.anfereba.nutricionabc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosCliente.OpcionCuatroCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionDosCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionTresCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionUnoCliente;
import com.anfereba.nutricionabc.FragmentosNutriologo.OpcionDosNutriologo;
import com.anfereba.nutricionabc.FragmentosNutriologo.OpcionTresNutriologo;
import com.anfereba.nutricionabc.FragmentosNutriologo.OpcionUnoNutriologo;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.google.android.material.navigation.NavigationView;

public class MainActivityNutriologo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nutriologo);

        preferences = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        Toolbar toolbar = findViewById(R.id.toolbarN);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_N);

        NavigationView navigationView = findViewById(R.id.nav_viewN);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerN,new OpcionUnoNutriologo()).commit();
            navigationView.setCheckedItem(R.id.Opcion_uno_Nutriologo);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.Opcion_uno_Nutriologo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerN,new OpcionUnoNutriologo()).commit();
                break;
            case R.id.Opcion_dos_Nutriologo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerN,new OpcionDosNutriologo()).commit();
                break;
            case R.id.Opcion_tres_Nutriologo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerN,new OpcionTresNutriologo()).commit();
                break;
            case R.id.Salir:
                CerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
        
    }

    private void CerrarSesion() {
        editor.putInt(Utilidades.CAMPO_ID_USUARIO,0);
        editor.apply();
        Toast.makeText(getApplicationContext(), "La sesion fue cerrada", Toast.LENGTH_SHORT).show();
        finish();
    }
}
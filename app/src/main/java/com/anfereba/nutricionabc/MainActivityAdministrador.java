package com.anfereba.nutricionabc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosAdministrador.OpcionDosAdministrador;
import com.anfereba.nutricionabc.FragmentosAdministrador.OpcionTresAdministrador;
import com.anfereba.nutricionabc.FragmentosAdministrador.OpcionUnoAdministrador;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionCuatroCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionDosCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionTresCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionUnoCliente;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.google.android.material.navigation.NavigationView;

public class MainActivityAdministrador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    TextView TXTNombreYApellido, NombrePerfilUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);

        preferences = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        AsignarDatosEncabezado();

        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_A);

        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,new OpcionUnoAdministrador()).commit();
            navigationView.setCheckedItem(R.id.Opcion_uno_Administrador);
        }

    }

    private void AsignarDatosEncabezado() {

        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);

        NavigationView navigationView = findViewById(R.id.nav_viewA);
        View header = navigationView.getHeaderView(0);
        TXTNombreYApellido = (TextView) header.findViewById(R.id.TXTNombreYApellido);
        NombrePerfilUsuario = (TextView) header.findViewById(R.id.NombrePerfilUsuario);


        String Nombre = shared.getString(Utilidades.CAMPO_NOMBRES,"");
        String Apellido = shared.getString(Utilidades.CAMPO_APELLIDOS,"");
        String Perfil = shared.getString(Utilidades.CAMPO_NOMBRE_PERFIL,"");

        NombrePerfilUsuario.setText(Perfil);
        TXTNombreYApellido.setText(Nombre+" "+Apellido);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Opcion_uno_Administrador:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,new OpcionUnoAdministrador()).commit();
                break;
            case R.id.Opcion_dos_Administrador:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,new OpcionDosAdministrador()).commit();
                break;
            case R.id.Opcion_tres_Administrador:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,new OpcionTresAdministrador()).commit();
                break;
            case R.id.Salir:
                CerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CerrarSesion() {
        Intent intent = null;
        editor.putInt(Utilidades.CAMPO_ID_USUARIO,0);
        editor.apply();
        Toast.makeText(getApplicationContext(), "La sesion fue cerrada", Toast.LENGTH_SHORT).show();
        intent = new Intent(getApplicationContext(), Login_App.class);
        startActivity(intent);
    }
}
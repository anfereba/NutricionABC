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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosCliente.HijosCliente;
import com.anfereba.nutricionabc.FragmentosCliente.OpcionTresCliente;
import com.anfereba.nutricionabc.FragmentosCliente.InformacionNutriologoCliente;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityCliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ArrayList<Usuario> listaArrayUsuarios;
    DbUsuario db;
    CircleImageView FotoPerfilEncabezado;

    TextView TXTNombreYApellido, NombrePerfilUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);


        preferences = this.getSharedPreferences("Sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        AsignarDatosEncabezado();

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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new InformacionNutriologoCliente()).commit();
            navigationView.setCheckedItem(R.id.Opcion_uno_Cliente);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Opcion_uno_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new InformacionNutriologoCliente()).commit();
                break;
            case R.id.Opcion_dos_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new HijosCliente()).commit();
                break;
            case R.id.Opcion_tres_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new OpcionTresCliente()).commit();
                break;
            case R.id.Opcion_cuatro_Cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerC,new PerfilUsuario()).commit();
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
        intent=new Intent(MainActivityCliente.this, Login_App.class);
        startActivity(intent);
        finish();
    }

    private void AsignarDatosEncabezado(){

        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);

        NavigationView navigationView = findViewById(R.id.nav_viewC);
        View header = navigationView.getHeaderView(0);
        TXTNombreYApellido = (TextView) header.findViewById(R.id.TXTNombreYApellido);
        NombrePerfilUsuario = (TextView) header.findViewById(R.id.NombrePerfilUsuario);
        FotoPerfilEncabezado = (CircleImageView) header.findViewById(R.id.FotoPerfilEncabezado);

        int IdUsuario = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        String Nombre = shared.getString(Utilidades.CAMPO_NOMBRES,"");
        String Apellido = shared.getString(Utilidades.CAMPO_APELLIDOS,"");
        String Perfil = shared.getString(Utilidades.CAMPO_NOMBRE_PERFIL,"");

        db = new DbUsuario(this);
        listaArrayUsuarios = new ArrayList<>(db.ObtenerDatosUsuario(IdUsuario));

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaArrayUsuarios.get(0).getFotoPerfil(),0,
                listaArrayUsuarios.get(0).getFotoPerfil().length);

        FotoPerfilEncabezado.setImageBitmap(bitmap);

        NombrePerfilUsuario.setText(Perfil);
        TXTNombreYApellido.setText(Nombre+" "+Apellido);
    }
}
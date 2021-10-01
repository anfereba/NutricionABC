package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

public class Login_App extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EditText Correo,Password;
    Button BtnIngresar, BtnRegistroCliente;

    DbUsuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //Inicializa el sharedpreference y el editor para guardar y revisar sesion

        preferences = this.getSharedPreferences("Sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        if (VerificarIdDeSesion() > 0){
            int id_sesion = VerificarIdDeSesion();
            RetomarSesion(id_sesion);

        }else {
            Toast.makeText(getApplicationContext(), "Debe Iniciar Sesion", Toast.LENGTH_LONG).show();
        }

        //Se recogen los elementos

        Correo= (EditText) findViewById(R.id.Correo);
        Password=(EditText) findViewById(R.id.Password);
        BtnIngresar=(Button) findViewById(R.id.BtnIngresar);
        BtnRegistroCliente=(Button) findViewById(R.id.BtnRegistroCliente);

        //Se programa el evento Onclick

        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbUsuario = new DbUsuario(getApplicationContext());

                //Se determina si las credenciales son correctas

                boolean UsuarioEsValido = dbUsuario.Comprobar_Correo_Password
                        (Correo.getText().toString(),Password.getText().toString())!=null;

                if(UsuarioEsValido){
                    IniciarSesion();

                }else{
                    Toast.makeText(getApplicationContext(),"Credenciales No Validas" , Toast.LENGTH_LONG).show();
                }

            }
        });

        //Se dirige al registro del cliente

        BtnRegistroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistroUsuarios.class));
                finish();
            }
        });


    }

    private int VerificarIdDeSesion() {
        return this.preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
    }

    private void GuardarInicioDeSesion(String Id_Usuario, String Nombre_Usuario, String Apellido_Usuario) {
        editor.putInt(Utilidades.CAMPO_ID_USUARIO,Integer.parseInt(Id_Usuario));
        editor.putString(Utilidades.CAMPO_NOMBRES,Nombre_Usuario);
        editor.putString(Utilidades.CAMPO_APELLIDOS,Apellido_Usuario);
        editor.apply();
    }

    private void RetomarSesion(int Usuario){

        dbUsuario = new DbUsuario(getApplicationContext());

        //Se determina el perfil del usuario para iniciar sesion

        String PerfilUsuario = dbUsuario.Comprobar_Perfil(Usuario);

        Intent intent = null;
        switch (PerfilUsuario){
            case "Cliente":
                intent = new Intent(getApplicationContext(), MainActivityCliente.class);
                break;
            case "Nutriologo":
                intent = new Intent(getApplicationContext(), MainActivityNutriologo.class);
                break;
            case "Administrador":
                intent = new Intent(getApplicationContext(), MainActivityAdministrador.class);
                break;
        }
        startActivity(intent);
        finish();

    }
    private void IniciarSesion(){

        //Se determina el perfil del usuario para iniciar sesion

        String PerfilUsuario = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString());

        //Se consulta el Id para guardar su sesion

        String Id_Usuario = dbUsuario.consultarDato(
                Utilidades.CAMPO_ID_USUARIO,
                Utilidades.CAMPO_CORREO,
                Correo.getText().toString());

        String Nombre_Usuario = dbUsuario.consultarDato(
                Utilidades.CAMPO_NOMBRES,
                Utilidades.CAMPO_CORREO,
                Correo.getText().toString());

        String Apellido_Usuario = dbUsuario.consultarDato(
                Utilidades.CAMPO_APELLIDOS,
                Utilidades.CAMPO_CORREO,
                Correo.getText().toString());

        Intent intent = null;
        switch (PerfilUsuario){
            case "Cliente":
                intent = new Intent(getApplicationContext(), MainActivityCliente.class);
                break;
            case "Nutriologo":
                intent = new Intent(getApplicationContext(), MainActivityNutriologo.class);
                break;
            case "Administrador":
                intent = new Intent(getApplicationContext(), MainActivityAdministrador.class);
                break;
        }
        GuardarInicioDeSesion(Id_Usuario,Nombre_Usuario,Apellido_Usuario);
        startActivity(intent);
        finish();
    }
}
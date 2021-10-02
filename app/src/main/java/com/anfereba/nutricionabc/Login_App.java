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

    private Integer Id_Sesion = -1;

    EditText Correo,Password;
    Button BtnIngresar, BtnRegistroCliente;

    DbUsuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //Inicializa el sharedpreference y el editor para guardar y revisar sesion

        dbUsuario = new DbUsuario(getApplicationContext());

        preferences = this.getSharedPreferences("Sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        if (VerificarIdDeSesion() > 0){

            Id_Sesion = VerificarIdDeSesion();
            IniciarSesion(Id_Sesion);

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
                    IniciarSesion(Id_Sesion);

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


    private void IniciarSesion(int Id_Sesion){

        dbUsuario = new DbUsuario(getApplicationContext());

        String PerfilUsuario = "";
        String Id_Usuario = null;
        String Nombre_Usuario = null;
        String Apellido_Usuario = null;


        if (Id_Sesion > 0){
            PerfilUsuario = dbUsuario.Comprobar_Perfil(Id_Sesion);
        }
        else{

            //Se determina el perfil del usuario para iniciar sesion
            PerfilUsuario = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString());

            //Se consulta el Id para guardar su sesion
            Id_Usuario = dbUsuario.consultarDato(
                    Utilidades.CAMPO_ID_USUARIO,
                    Utilidades.CAMPO_CORREO,
                    Correo.getText().toString());
            Nombre_Usuario = dbUsuario.consultarDato(
                    Utilidades.CAMPO_NOMBRES,
                    Utilidades.CAMPO_CORREO,
                    Correo.getText().toString());
            Apellido_Usuario = dbUsuario.consultarDato(
                    Utilidades.CAMPO_APELLIDOS,
                    Utilidades.CAMPO_CORREO,
                    Correo.getText().toString());

            GuardarInicioDeSesion(Id_Usuario,Nombre_Usuario,Apellido_Usuario,PerfilUsuario);
        }

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

    private void GuardarInicioDeSesion(String Id_Usuario, String Nombre_Usuario, String Apellido_Usuario, String Perfil_Usuario) {
        editor.putInt(Utilidades.CAMPO_ID_USUARIO,Integer.parseInt(Id_Usuario));
        editor.putString(Utilidades.CAMPO_NOMBRES,Nombre_Usuario);
        editor.putString(Utilidades.CAMPO_APELLIDOS,Apellido_Usuario);
        editor.putString(Utilidades.CAMPO_NOMBRE_PERFIL,Perfil_Usuario);
        editor.apply();
    }

    private Integer VerificarIdDeSesion() {
        return this.preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
    }
}
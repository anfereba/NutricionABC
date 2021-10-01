package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbUsuario;

public class Login_App extends AppCompatActivity {
    EditText Correo,Password;
    Button BtnIngresar, BtnRegistroCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //Se recogen los elementos

        Correo= (EditText) findViewById(R.id.Correo);
        Password=(EditText) findViewById(R.id.Password);
        BtnIngresar=(Button) findViewById(R.id.BtnIngresar);
        BtnRegistroCliente=(Button) findViewById(R.id.BtnRegistroCliente);

        //Se programa el evento Onclick

        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuario dbUsuario = new DbUsuario(getApplicationContext());

                //Se determina el perfil del usuario

                String PerfilUsuario = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString());

                //Se determina si las credenciales son correctas

                boolean UsuarioEsValido = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString())!=null;

                //Si el usuario es valido, se le dirige a la seccion correspondiente de su perfil

                if(UsuarioEsValido){
                    Toast.makeText(getApplicationContext(),PerfilUsuario, Toast.LENGTH_LONG).show();
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

}
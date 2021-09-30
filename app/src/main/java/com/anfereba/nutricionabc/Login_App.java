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

                String TipoUsuario = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString());

                boolean UsuarioEsValido = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString())!=null;
                if(UsuarioEsValido){
                    Toast.makeText(getApplicationContext(),TipoUsuario , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Credenciales No Validas" , Toast.LENGTH_LONG).show();
                }

            }
        });
        BtnRegistroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistroUsuarios.class));
                finish();
            }
        });
    }

}
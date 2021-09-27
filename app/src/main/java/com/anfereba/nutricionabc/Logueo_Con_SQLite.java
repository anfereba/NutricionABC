package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbCliente;

public class Logueo_Con_SQLite extends AppCompatActivity {
EditText Correo,Contraseña;
Button RClinte, RNutriologo,Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo_con_sqlite);
        Correo= (EditText) findViewById(R.id.editTextTextPersonName);
        Contraseña=(EditText) findViewById(R.id.editTextTextPersonName2);
        RClinte=(Button) findViewById(R.id.button2);
        Login=(Button) findViewById(R.id.button);
        RNutriologo=(Button) findViewById(R.id.button3);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbCliente dbCliente = new DbCliente(getApplicationContext());

                Toast.makeText(getApplicationContext(),"no se a programado esta funcion, Lo sentimos" , Toast.LENGTH_LONG).show();

            }
        });
        RClinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrarClienteConSQLite.class));
            }
        });
        RNutriologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrarNutriologoConSQLite.class));
            }
        });
    }

}
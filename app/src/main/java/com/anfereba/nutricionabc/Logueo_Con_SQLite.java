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
    EditText Correo,Password;
    Button BtnIngresar, BtnRegistroCliente,BtnRegistroNutriologo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo_con_sqlite);

        //Se recogen los elementos

        Correo= (EditText) findViewById(R.id.Correo);
        Password=(EditText) findViewById(R.id.Password);
        BtnIngresar=(Button) findViewById(R.id.BtnIngresar);
        BtnRegistroCliente=(Button) findViewById(R.id.BtnRegistroCliente);
        BtnRegistroNutriologo=(Button) findViewById(R.id.BtnRegistroNutriologo);

        //Se programa el evento Onclick

        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbCliente dbCliente = new DbCliente(getApplicationContext());

                Toast.makeText(getApplicationContext(),"no se a programado esta funcion, Lo sentimos" , Toast.LENGTH_LONG).show();

            }
        });
        BtnRegistroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrarClienteConSQLite.class));
            }
        });
        BtnRegistroNutriologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrarNutriologoConSQLite.class));
            }
        });
    }

}
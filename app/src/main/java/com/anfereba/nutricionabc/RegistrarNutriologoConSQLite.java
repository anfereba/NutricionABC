package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbCliente;
import com.anfereba.nutricionabc.db.DbNutriologo;

public class RegistrarNutriologoConSQLite extends AppCompatActivity {
EditText Nombre, Correo;
Button Crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nutriologo);
        Crear = findViewById(R.id.button5);
        Nombre = (EditText) findViewById(R.id.editTextTextPersonName5);
        Correo = (EditText) findViewById(R.id.editTextTextPersonName6);

        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Nombre.getText().toString().equals("")) {
                    DbNutriologo DbNutriologo = new DbNutriologo (getApplicationContext());

                    long id = DbNutriologo.insertarContacto(Nombre.getText().toString(), Correo.getText().toString());


                    if (id > 0) {
                        Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }


            }


        });

    }

    /* if (!Nombre.getText().toString().equals("") && !Correo.getText().toString().equals("")) {

    }*///este es un metodo que encontre para despues
    private void limpiar() {
        Nombre.setText("");
        Correo.setText("");
    }
}
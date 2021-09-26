package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.anfereba.nutricionabc.db.DbCliente;
import com.anfereba.nutricionabc.db.DbHelper;

public class RegistrarClienteConSQLite extends AppCompatActivity {
    Button btnCrear;
    EditText Nombre, Correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
        btnCrear = findViewById(R.id.button4);
        Nombre = (EditText) findViewById(R.id.editTextTextPersonName3);
        Correo = (EditText) findViewById(R.id.editTextTextPersonName4);

    btnCrear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!Nombre.getText().toString().equals("")) {
                DbCliente dbCliente = new DbCliente(getApplicationContext());

                long id = dbCliente.insertarContacto(Nombre.getText().toString(), Correo.getText().toString());


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
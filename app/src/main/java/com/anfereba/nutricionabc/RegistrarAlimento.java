package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.utilidades.DbAlimento;

public class RegistrarAlimento extends AppCompatActivity {
Button AgregarAlimento;
EditText NombreDelAlimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alimento);
        NombreDelAlimento = (EditText) findViewById(R.id.NombreDelAlimento);
        AgregarAlimento= (Button) findViewById(R.id.GuardarAlimento);
        AgregarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!NombreDelAlimento.getText().toString().equals("") ) {

                    DbAlimento dbAlimentos = new DbAlimento(RegistrarAlimento.this);
                    long id = dbAlimentos.insertarAlimento(NombreDelAlimento.getText().toString());

                    if (id > 0) {
                        Toast.makeText(RegistrarAlimento.this, "Alimento GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(RegistrarAlimento.this, "ERROR AL GUARDAR ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegistrarAlimento.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        NombreDelAlimento.setText("");

    }
}
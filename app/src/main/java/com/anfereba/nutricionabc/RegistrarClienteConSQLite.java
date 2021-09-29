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
    Button BtnRegistrarClienteBD;
    EditText TXTNombreUsuario, TXTApellidoUsuario,TXTFechaNacimientoUsuario,TXTCorreoUsuario,TXTPasswordUsuario,TXTFechaCreacionUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        //Inicializacion de elementos

        BtnRegistrarClienteBD = findViewById(R.id.BtnRegistrarClienteBD);
        TXTNombreUsuario = (EditText) findViewById(R.id.TXTNombreUsuario);
        TXTApellidoUsuario = (EditText) findViewById(R.id.TXTApellidoUsuario);
        TXTFechaNacimientoUsuario=(EditText)findViewById(R.id.TXTFechaNacimientoUsuario);
        TXTCorreoUsuario = (EditText) findViewById(R.id.TXTCorreoUsuario);
        TXTPasswordUsuario = (EditText) findViewById(R.id.TXTPasswordUsuario);
        TXTFechaCreacionUsuario=(EditText)findViewById(R.id.TXTFechaCreacionUsuario);


        BtnRegistrarClienteBD.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!TXTNombreUsuario.getText().toString().equals("")&& !TXTApellidoUsuario.getText().toString().equals("")
                    && !TXTFechaNacimientoUsuario.getText().toString().equals("")
                    && !TXTCorreoUsuario.getText().toString().equals("")
                    && !TXTPasswordUsuario.getText().toString().equals("")
                    && !TXTFechaCreacionUsuario.getText().toString().equals("")) {
                DbCliente dbCliente = new DbCliente(getApplicationContext());

                long id = dbCliente.insertarContacto(TXTNombreUsuario.getText().toString(),
                        TXTApellidoUsuario.getText().toString(),TXTFechaNacimientoUsuario.getText().toString(),
                        TXTCorreoUsuario.getText().toString(),TXTPasswordUsuario.getText().toString(),
                        TXTFechaCreacionUsuario.getText().toString());


                if (id > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO: "+String.valueOf(id), Toast.LENGTH_LONG).show();
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
        TXTNombreUsuario.setText("");
        TXTApellidoUsuario.setText("");
        TXTFechaNacimientoUsuario.setText("");
        TXTCorreoUsuario.setText("");
        TXTPasswordUsuario.setText("");
        TXTFechaCreacionUsuario.setText("");
    }
}
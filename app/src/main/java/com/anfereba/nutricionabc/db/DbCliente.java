package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;
//Aqui podremos realizar las operaciones CRUB en la tabla Cliente
public class DbCliente extends DbHelper{ //el extend lo hacemos para que podamos manejar la base de datos
    // y le heredamos la creacion de las tablas
    Context context;
    public DbCliente(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarContacto(String nombre, String correo, String contraseña) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("correo", correo);
            values.put("contraseña",contraseña);

            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public String consultarDato(String variableAconsultar, String filtroparabuscar, String informacionDelFiltro){
        /*Esta consulta solo sirve para buscar un String*/
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        //Cursor cursorClientes= db.rawQuery(" SELECT * FROM "+TABLE_CLIENTES+" WHERE id=1",null); consulta de un numero
        Cursor cursorClientes= db.rawQuery(" SELECT "+variableAconsultar+" FROM "+TABLE_CLIENTES+" WHERE "+filtroparabuscar+"='"+informacionDelFiltro+"'",null);
        cursorClientes.moveToFirst();



        String dato =cursorClientes.getString(0);
        cursorClientes.close();
        return dato;
    }
}

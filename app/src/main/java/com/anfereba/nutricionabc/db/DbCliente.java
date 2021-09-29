package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import org.w3c.dom.Text;


public class DbCliente extends DbHelper{ //el extend lo hacemos para que podamos manejar la base de datos
    // y le heredamos la creacion de las tablas
    Context context;
    public DbCliente(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarContacto(String nombres, String apellidos, String FechaNacimiento, String correo, String password, String FechaCreacion) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRES, nombres);
            values.put(Utilidades.CAMPO_APELLIDOS, apellidos);
            values.put(Utilidades.CAMPO_FECHA_NACIMIENTO, FechaNacimiento);
            values.put(Utilidades.CAMPO_CORREO, correo);
            values.put(Utilidades.CAMPO_PASSWORD,password);
            values.put(Utilidades.CAMPO_FECHA_CREACION,FechaCreacion);

            id = db.insert(Utilidades.TABLA_USUARIO, null, values);
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

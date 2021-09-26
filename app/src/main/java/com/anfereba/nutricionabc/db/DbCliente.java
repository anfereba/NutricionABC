package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbCliente extends DbHelper{ //el extend lo hacemos para que podamos manejar la base de datos
    // y le heredamos la creacion de las tablas
    Context context;
    public DbCliente(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarContacto(String nombre, String correo) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("correo", correo);

            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }


}

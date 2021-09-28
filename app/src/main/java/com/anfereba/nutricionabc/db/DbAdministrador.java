package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbAdministrador extends DbHelper{
    Context context;
    public DbAdministrador(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarAdministradores() {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", "fabian");
            values.put("correo", "fabian0647@hotmail.es");
            values.put("contraseña","fabian10");

            id = db.insert(TABLE_CLIENTES, null, values);
            values.put("nombre", "andres");
            values.put("correo", "fabian0647@hotmail.es");
            values.put("contraseña","fabian10");

            id = db.insert(TABLE_CLIENTES, null, values);
            values.put("nombre", "Lady");
            values.put("correo", "fabian0647@hotmail.es");
            values.put("contraseña","fabian10");

            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
}

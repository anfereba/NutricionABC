package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
//Aqui podremos realizar las operaciones CRUB en la tabla Nutriologo
public class DbNutriologo extends DbHelper {
    Context context;
    public DbNutriologo(@Nullable Context context) {
        super(context);
        this.context =context;
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

            id = db.insert(TABLE_NUTRIOLOGO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

}
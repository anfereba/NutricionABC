package com.anfereba.nutricionabc.db.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.DbHelper;

public class Alimento extends DbHelper {
    Context context;
    public Alimento(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarAlimento(String nombre) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_NOMBREAlimento, nombre);


            id = db.insert(Utilidades.TABLA_Alimento, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
}


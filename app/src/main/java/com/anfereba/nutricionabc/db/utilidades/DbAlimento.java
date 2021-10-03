package com.anfereba.nutricionabc.db.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.DbHelper;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;

import java.util.ArrayList;

public class DbAlimento extends DbHelper {
    Context context;
    public DbAlimento(@Nullable Context context) {
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


    public ArrayList<Alimentos> mostrarAlimentos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Alimentos> listaAlimentos = new ArrayList<>();
        Alimentos alimentos;
        Cursor cursorAlimentos;

        cursorAlimentos = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_Alimento, null);

        if (cursorAlimentos.moveToFirst()) {
            do {
                alimentos = new Alimentos();
                alimentos.setIdAlimento(cursorAlimentos.getInt(0));
                alimentos.setNombreAlimento(cursorAlimentos.getString(1));
                listaAlimentos.add(alimentos);
            } while (cursorAlimentos.moveToNext());
        }

        cursorAlimentos.close();

        return listaAlimentos;
    }
}


package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.utilidades.Utilidades;


public class DbCalificacion extends DbHelper {

    Context context;
    public DbCalificacion(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarCalificacion(int id_Padre, int id_nutriologo, Double puntaje, String comentario) {
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID_PADRE_CALIFICACION,id_Padre);
            values.put(Utilidades.CAMPO_ID_NUTRIOLOGO_CALIFICACION, id_nutriologo);
            values.put(Utilidades.CAMPO_PUNTUACION, puntaje);
            values.put(Utilidades.CAMPO_COMENTARIO, comentario);

            id = db.insert(Utilidades.TABLA_CALIFICACION, null, values);

            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return id;

    }
}

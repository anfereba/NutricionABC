package com.anfereba.nutricionabc.db.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.DbHelper;

public class DbPlanNutricional extends DbHelper {
    Context context;
    public DbPlanNutricional(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarPlan(Integer IdUsuario, String nombre) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_ID_USUARIO2,IdUsuario);//<---- registrara La id del nutriologo que la cree
            values.put(Utilidades.CAMPO_NOMBRE, nombre);


            id = db.insert(Utilidades.TABLA_PlanNutricional, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

}

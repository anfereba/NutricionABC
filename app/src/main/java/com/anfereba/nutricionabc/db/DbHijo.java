package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

public class DbHijo extends DbHelper {
    Context context;
    public DbHijo(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarHijo(String nombreHijo, String EstaturaHijo,int EdadHijo,int PesoHijo,int IDUsuario3,byte[]FotoHijo ) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_FOTO_HIJO,FotoHijo);
            values.put(Utilidades.CAMPO_NOMBRE_HIJO,nombreHijo);
            values.put(Utilidades.CAMPO_ESTATURA_HIJO,EstaturaHijo);
            values.put(Utilidades.CAMPO_EDAD_HIJO,EdadHijo);
            values.put(Utilidades.CAMPO_PESO_HIJO,PesoHijo);
            values.put(Utilidades.CAMPO_ID_USUARIO3,IDUsuario3);
            values.put(Utilidades.CAMPO_ID_PlanNutricional3,0);




            id = db.insert(Utilidades.TABLA_Hijo, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public ArrayList<Hijos> mostrarHijos(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Hijos> listaHijos = new ArrayList<>();
        Hijos hijos;
        Cursor cursorHijos;

        cursorHijos = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_Hijo +" WHERE "+Utilidades.CAMPO_ID_USUARIO3+" = "+id+"", null);

        if (cursorHijos.moveToFirst()) {
            do {
                hijos = new Hijos();
                hijos.setIdHijos(cursorHijos.getInt(0));
                hijos.setFotoHijos(cursorHijos.getBlob(1));
                hijos.setNombreHijos(cursorHijos.getString(2));
                hijos.setEstaturaHijos(cursorHijos.getString(3));
                hijos.setEdadHijos(cursorHijos.getInt(4));
                hijos.setPesoHijos(cursorHijos.getInt(5));
                listaHijos.add(hijos);
            } while (cursorHijos.moveToNext());
        }

        cursorHijos.close();

        return listaHijos;
    }
}

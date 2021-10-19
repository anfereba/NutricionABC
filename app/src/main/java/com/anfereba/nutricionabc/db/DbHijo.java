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
    public ArrayList<Hijos> mostrarTodosLosHijosQueNoTienenPlan(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Hijos> listaHijos = new ArrayList<>();
        Hijos hijos;
        Cursor cursorHijos;

        cursorHijos = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_Hijo +" WHERE "+Utilidades.CAMPO_ID_PlanNutricional3+" = "+id+"", null);

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
    public Hijos verHijos(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Hijos hijos = null;
        Cursor cursorHijos;

        cursorHijos = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_Hijo+" WHERE idHijo = "+id+" LIMIT 1 ", null);

        if (cursorHijos.moveToFirst()) {

            hijos = new Hijos();
            hijos.setIdHijos(cursorHijos.getInt(0));
            hijos.setFotoHijos(cursorHijos.getBlob(1));
            hijos.setNombreHijos(cursorHijos.getString(2));
            hijos.setEstaturaHijos(cursorHijos.getString(3));
            hijos.setEdadHijos(cursorHijos.getInt(4));
            hijos.setPesoHijos(cursorHijos.getInt(5));
        }

        cursorHijos.close();

        return hijos;
    }
    public boolean EliminarHijo(int id) {

        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+Utilidades.TABLA_Hijo+" WHERE idHijo= '"+ id +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
    public long EditarHijo(int id, String nombreHijo,String EstaturaHijo, int EdadHijo, int PesoHijo, byte[] FotoHijo) {

        long correcto =0;
        String[] parametros = {Integer.toString(id)};

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_FOTO_HIJO,FotoHijo);
            values.put(Utilidades.CAMPO_NOMBRE_HIJO,nombreHijo);
            values.put(Utilidades.CAMPO_ESTATURA_HIJO,EstaturaHijo);
            values.put(Utilidades.CAMPO_EDAD_HIJO,EdadHijo);
            values.put(Utilidades.CAMPO_PESO_HIJO,PesoHijo);



            correcto = db.update(Utilidades.TABLA_Hijo,values,Utilidades.CAMPO_ID_HIJO+"=?",parametros);
        } catch (Exception ex) {
            ex.toString();
        }



        return correcto;
    }
}

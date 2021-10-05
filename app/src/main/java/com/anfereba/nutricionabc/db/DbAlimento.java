package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.DbHelper;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

public class DbAlimento extends DbHelper {
    Context context;
    public DbAlimento(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarAlimento(String nombre, int calorias,byte[] FotoAlimento ) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_FOTO_ALIMENTO,FotoAlimento);
            Toast.makeText(context.getApplicationContext(), FotoAlimento.toString(),Toast.LENGTH_LONG).show();
            values.put(Utilidades.CAMPO_ID_CALORIAS,calorias);
            values.put(Utilidades.CAMPO_NOMBREAlimento,nombre);




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
                alimentos.setFotoAlimento(cursorAlimentos.getBlob(1));
                alimentos.setCalorias(cursorAlimentos.getInt(2));
                alimentos.setNombreAlimento(cursorAlimentos.getString(3));
                listaAlimentos.add(alimentos);
            } while (cursorAlimentos.moveToNext());
        }

        cursorAlimentos.close();

        return listaAlimentos;
    }
    public Alimentos verAlimentos(int  id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Alimentos alimentos = null;
        Cursor cursorAlimentos;

        cursorAlimentos = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_Alimento+" WHERE IdAlimento = "+id+" LIMIT 1 ", null);

        if (cursorAlimentos.moveToFirst()) {

                alimentos = new Alimentos();
                alimentos.setIdAlimento(cursorAlimentos.getInt(0));
                alimentos.setFotoAlimento(cursorAlimentos.getBlob(1));
                alimentos.setCalorias(cursorAlimentos.getInt(2));
                alimentos.setNombreAlimento(cursorAlimentos.getString(3));


        }

        cursorAlimentos.close();

        return alimentos;
    }

    public long EditarAlimento(int id, String nombre, int calorias,byte[] FotoAlimento) {

        long correcto =0;
        String[] parametros = {Integer.toString(id)};

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_FOTO_ALIMENTO,FotoAlimento);
            values.put(Utilidades.CAMPO_ID_CALORIAS,calorias);
            values.put(Utilidades.CAMPO_NOMBREAlimento,nombre);




           correcto = db.update(Utilidades.TABLA_Alimento,values,Utilidades.CAMPO_ID_Alimento+"=?",parametros);
        } catch (Exception ex) {
            ex.toString();
        }



        return correcto;
    }
    public boolean EliminarAlimento(int id) {

        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+Utilidades.TABLA_Alimento+" WHERE idAlimento= '"+ id +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
}


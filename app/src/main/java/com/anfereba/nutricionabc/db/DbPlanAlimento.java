package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesAlimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

public class DbPlanAlimento extends DbHelper{
    Context context;
    public DbPlanAlimento(@Nullable Context context) {
        super(context);
        this.context=context;
    }




    public long insertarPlanAlimento(int IdPLanNutricional,int IdAlimento ) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_ID_PlanNutricional2,IdPLanNutricional);
            values.put(Utilidades.CAMPO_ID_Alimento2,IdAlimento);




            id = db.insert(Utilidades.TABLA_PlanAlimento, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public ArrayList<PlanesAlimentos> mostrarPlan() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PlanesAlimentos> listaPlanesAlimentos = new ArrayList<>();
        PlanesAlimentos planesAlimentos;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PlanAlimento, null);

        if (cursorPlanes.moveToFirst()) {
            do {
                planesAlimentos = new PlanesAlimentos();
                planesAlimentos.setIdPlanAlimento(cursorPlanes.getInt(0));
                planesAlimentos.setIdPlanNutricional(cursorPlanes.getInt(1));
                planesAlimentos.setIdAlimento(cursorPlanes.getInt(2));
                listaPlanesAlimentos.add(planesAlimentos);
            } while (cursorPlanes.moveToNext());
        }

        cursorPlanes.close();

        return listaPlanesAlimentos;
    }
    public ArrayList<PlanesAlimentos> mostrarIdAlimento(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PlanesAlimentos> listaPlanesAlimentos = new ArrayList<>();
        PlanesAlimentos planesAlimentos;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM  "+Utilidades.TABLA_PlanAlimento+" WHERE "+Utilidades.CAMPO_ID_PlanNutricional2+" = "+id+"", null);

        if (cursorPlanes.moveToFirst()) {
            do {
                planesAlimentos = new PlanesAlimentos();
                planesAlimentos.setIdPlanAlimento(cursorPlanes.getInt(0));
                planesAlimentos.setIdPlanNutricional(cursorPlanes.getInt(1));
                planesAlimentos.setIdAlimento(cursorPlanes.getInt(2));
                Cursor cursorNombreAlimentos =db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBREAlimento+" FROM  "+Utilidades.TABLA_Alimento+" WHERE "+Utilidades.CAMPO_ID_Alimento+" = "+planesAlimentos.getIdAlimento()+"", null);
                cursorNombreAlimentos.moveToFirst();
                planesAlimentos.setNombrePlanesAlimentos(cursorNombreAlimentos.getString(0));
                System.out.println(cursorNombreAlimentos.getString(0));
                listaPlanesAlimentos.add(planesAlimentos);

            } while (cursorPlanes.moveToNext());
        }

        cursorPlanes.close();

        return listaPlanesAlimentos;
    }
}
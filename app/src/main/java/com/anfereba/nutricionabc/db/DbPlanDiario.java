package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesAlimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

public class DbPlanDiario extends DbHelper{
    Context context;
    public DbPlanDiario(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarPlanDiario(int IdHijo, int idPlanNutricional ) {

        long id = 0;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PlanesAlimentos> listaPlanesAlimentos = new ArrayList<>();
        PlanesAlimentos planesAlimentos;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PlanAlimento+" WHERE "+Utilidades.CAMPO_ID_PlanNutricional2+" = "+idPlanNutricional+"", null);

        if (cursorPlanes.moveToFirst()) {
            do {
                planesAlimentos = new PlanesAlimentos();
                planesAlimentos.setIdPlanAlimento(cursorPlanes.getInt(0));
                try {
                    ContentValues values = new ContentValues();
                    //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
                    values.put(Utilidades.CAMPO_ID_HIJO2,IdHijo);
                    values.put(Utilidades.CAMPO_ID_PlanAlimento2,planesAlimentos.getIdPlanAlimento());
                    values.put(Utilidades.CAMPO_Cumplimiento,1);//Para el cumplimiento 1 es no y 2 es si cumplio
                    id = db.insert(Utilidades.TABLA_Cumplimiennto_Plan_Diario, null, values);
                } catch (Exception ex) {
                    ex.toString();
                }
                planesAlimentos.setIdPlanNutricional(cursorPlanes.getInt(1));
                planesAlimentos.setIdAlimento(cursorPlanes.getInt(2));
                listaPlanesAlimentos.add(planesAlimentos);
            } while (cursorPlanes.moveToNext());
        }

        cursorPlanes.close();
        return id;
    }
    public ArrayList<PlanesDiarios> mostrarCumplimientoDelPlanDiario(int IdHijo, int IdPlanNutricional) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PlanesAlimentos> listaPlanesAlimentos = new ArrayList<>();
        ArrayList<PlanesDiarios> listaPlanesDiarios = new ArrayList<>();
        PlanesAlimentos planesAlimentos;
        PlanesDiarios planesDiarios;
        Cursor cursorPlanes;
        Cursor cursorPlanesDiarios;
        cursorPlanesDiarios = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_Cumplimiennto_Plan_Diario+" WHERE "+Utilidades.CAMPO_ID_HIJO2+" = "+IdHijo+"", null);

        if (cursorPlanesDiarios.moveToFirst()) {
            do {
                planesDiarios = new PlanesDiarios();
                planesDiarios.setIdCumplimientoPlanDiario(cursorPlanesDiarios.getInt(0));
                planesDiarios.setIdHijo(cursorPlanesDiarios.getInt(1));
                planesDiarios.setIdPlanAlimento(cursorPlanesDiarios.getInt(2));
                planesDiarios.setCumplimiento(cursorPlanesDiarios.getInt(3));
                cursorPlanes = db.rawQuery("SELECT idAlimento,idPlanNutricional,Dia FROM "+Utilidades.TABLA_PlanAlimento+" WHERE "+Utilidades.CAMPO_ID_PlanAlimento+" = "+planesDiarios.getIdPlanAlimento()+"", null);
                cursorPlanes.moveToFirst();
                planesDiarios.setIdAlimento(cursorPlanes.getInt(0));
                planesDiarios.setPlanNutricional(cursorPlanes.getInt(1));
                planesDiarios.setDia(cursorPlanes.getInt(2));
                Cursor cursorNombreAlimentos =db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBREAlimento+" FROM  "+Utilidades.TABLA_Alimento+" WHERE "+Utilidades.CAMPO_ID_Alimento+" = "+planesDiarios.getIdAlimento()+"", null);
                cursorNombreAlimentos.moveToFirst();
                planesDiarios.setNombreAlimento(cursorNombreAlimentos.getString(0));
                cursorPlanes.close();
                if (planesDiarios.getPlanNutricional()==IdPlanNutricional){
                    listaPlanesDiarios.add(planesDiarios);
                }
                cursorPlanes.close();
            } while (cursorPlanesDiarios.moveToNext());
        }
        return listaPlanesDiarios;
    }
    public long EditarCumplimientoDelPlanDiario(int IdCumplimientoPlanDiario,int valor) {

        long correcto = 0;
        String[] parametros = {Integer.toString(IdCumplimientoPlanDiario)};

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente

            values.put(Utilidades.CAMPO_Cumplimiento, valor);


            correcto = db.update(Utilidades.TABLA_Cumplimiennto_Plan_Diario, values, Utilidades.CAMPO_ID_Cumplimiento_Plan_Diario + "=?", parametros);
        } catch (Exception ex) {
            ex.toString();
        }
        return correcto;
    }
}

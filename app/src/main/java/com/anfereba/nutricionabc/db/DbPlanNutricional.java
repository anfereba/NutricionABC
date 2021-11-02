package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.HistorialPlanes;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import org.w3c.dom.Text;

import java.util.ArrayList;

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
            values.put(Utilidades.CAMPO_NOMBREPlanNutricional, nombre);


            id = db.insert(Utilidades.TABLA_PlanNutricional, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public ArrayList<PlanesNutricionales> mostrarPlan(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PlanesNutricionales> listaPlanesNutricionales = new ArrayList<>();
        PlanesNutricionales planesNutricionales;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PlanNutricional +" WHERE "+Utilidades.CAMPO_ID_USUARIO2+" = "+id+"", null);

        if (cursorPlanes.moveToFirst()) {
            do {
                planesNutricionales = new PlanesNutricionales();
                planesNutricionales.setIdPlanNutricional(cursorPlanes.getInt(0));
                planesNutricionales.setIdUsuario(cursorPlanes.getInt(1));
                planesNutricionales.setNombrePlan(cursorPlanes.getString(2));
                listaPlanesNutricionales.add(planesNutricionales);
            } while (cursorPlanes.moveToNext());
        }

        cursorPlanes.close();

        return listaPlanesNutricionales;
    }
    public PlanesNutricionales verPlan(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        PlanesNutricionales planesNutricionales=null;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM  "+Utilidades.TABLA_PlanNutricional+" WHERE IdPlanNutricional = "+id+"", null);

        if (cursorPlanes.moveToFirst()) {

                planesNutricionales = new PlanesNutricionales();
                planesNutricionales.setIdPlanNutricional(cursorPlanes.getInt(0));
                planesNutricionales.setIdUsuario(cursorPlanes.getInt(1));
                planesNutricionales.setNombrePlan(cursorPlanes.getString(2));


        }

        cursorPlanes.close();

        return planesNutricionales;
    }
    public boolean EditarPlan(int id, String nombre) {
        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+Utilidades.TABLA_PlanNutricional+ " SET NombrePlamNutricional = '"+nombre+"' WHERE idPlanNutricional= '"+ id +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
    public boolean EliminarPlan(int id) {

        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+Utilidades.TABLA_PlanNutricional+" WHERE idPlanNutricional= '"+ id +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
    public long insertarHistorialPlanNutricional(int idHijo, int idPlanNutricional) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente


            values.put(Utilidades.CAMPO_ID_HIJO3,idHijo);
            values.put(Utilidades.CAMPO_ID_PlanNutricional4,idPlanNutricional);//<---- registrara La id del nutriologo que la cree
            values.put(Utilidades.CAMPO_Comentarios_Nutriologo," ");
            values.put(Utilidades.CAMPO_Cumplimiento2, 0);
            values.put(Utilidades.CAMPO_Visto_Bueno_Nutriologo,0);


            id = db.insert(Utilidades.TABLA_Historial_Planes_Nutricionales, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public boolean Comprobar_ExistenciaHistorialPlan(int idHijo, int idPlanNutricional){
        boolean existePlan = false;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT 1 FROM " + Utilidades.TABLA_Historial_Planes_Nutricionales +" WHERE "+Utilidades.CAMPO_ID_HIJO3+" = "+idHijo+" AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = "+idPlanNutricional+"", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0){
                existePlan = true;
            }else {
                existePlan = false;
            }
            db.close();
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            existePlan = false;
        }
        return existePlan;
    }
    public boolean EditarPorcentajePlanes(int idPlanNutricional, int IdHijo, int Porcentage) {

        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+Utilidades.TABLA_Historial_Planes_Nutricionales+ " SET "+Utilidades.CAMPO_Cumplimiento2+" = '"+Porcentage+"' WHERE "+Utilidades.CAMPO_ID_HIJO3+"= '"+ IdHijo +"' AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = '"+idPlanNutricional +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public Integer verPorcentagePlan(int IdHijo, int idPlanNutricional) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int Porcentage = 0;

        PlanesNutricionales planesNutricionales=null;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT "+Utilidades.CAMPO_Cumplimiento2+" FROM  "+Utilidades.TABLA_Historial_Planes_Nutricionales+" WHERE "+Utilidades.CAMPO_ID_HIJO3+"= '"+ IdHijo +"' AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = '"+idPlanNutricional +"'", null);

        if (cursorPlanes.moveToFirst()) {
            Porcentage =cursorPlanes.getInt(0);
        }

        cursorPlanes.close();

        return Porcentage;
    }
    public boolean EditarVistoBueno(int idPlanNutricional, int IdHijo, boolean VistoBueno) {

        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+Utilidades.TABLA_Historial_Planes_Nutricionales+ " SET "+Utilidades.CAMPO_Visto_Bueno_Nutriologo+" = '"+VistoBueno+"' WHERE "+Utilidades.CAMPO_ID_HIJO3+"= '"+ IdHijo +"' AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = '"+idPlanNutricional +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
    public boolean EditarComentarioNutriologo(int idPlanNutricional, int IdHijo, String Comentario) {

        boolean correcto =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+Utilidades.TABLA_Historial_Planes_Nutricionales+ " SET "+Utilidades.CAMPO_Comentarios_Nutriologo+" = '"+Comentario+"' WHERE "+Utilidades.CAMPO_ID_HIJO3+"= '"+ IdHijo +"' AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = '"+idPlanNutricional +"'");
            correcto=true;
        } catch (Exception ex) {
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
    public String verComentarioNutriologo(int IdHijo, int idPlanNutricional) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Porcentage = "";
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT "+Utilidades.CAMPO_Comentarios_Nutriologo+" FROM "+Utilidades.TABLA_Historial_Planes_Nutricionales+" WHERE "+Utilidades.CAMPO_ID_HIJO3+"= '"+ IdHijo +"' AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = '"+idPlanNutricional +"'", null);

        if (cursorPlanes.moveToFirst()) {
            Porcentage =cursorPlanes.getString(0);
        }

        cursorPlanes.close();

        return Porcentage;
    }
    public ArrayList<HistorialPlanes> mostrarHistorialPlan(int idUsuario) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<HistorialPlanes> listaHistorialPlanes = new ArrayList<>();
        HistorialPlanes historialPlanes;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_Hijo +" WHERE "+Utilidades.CAMPO_ID_USUARIO3+" = "+idUsuario+"", null);

        if (cursorPlanes.moveToFirst()) {
            do {
                historialPlanes = new HistorialPlanes();
                historialPlanes.setIdHijo(cursorPlanes.getInt(0));
                historialPlanes.setFotoHijos(cursorPlanes.getBlob(1));
                historialPlanes.setNombreHijo(cursorPlanes.getString(2));

               /* Cursor cursorHistorialPlanes = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_Historial_Planes_Nutricionales +" WHERE "+Utilidades.CAMPO_ID_HIJO3+" = "+historialPlanes.getIdHijo()+"", null);
                    historialPlanes.setIdPlanNutricional(cursorHistorialPlanes.getInt(2));
                    historialPlanes.setCumplimientoDelPlan(cursorHistorialPlanes.getInt(4));
                    historialPlanes.setVistoBueno(cursorHistorialPlanes.getString(5));
                cursorHistorialPlanes.close();
                Cursor cursorPlanNutricional = db.rawQuery("SELECT " + Utilidades.CAMPO_NOMBREPlanNutricional + " FROM " + Utilidades.TABLA_PlanNutricional +" WHERE "+Utilidades.CAMPO_ID_PlanNutricional+" = "+historialPlanes.getIdPlanNutricional()+"", null);
               cursorPlanNutricional.moveToFirst();
                historialPlanes.setNombrePlanNutricional(cursorPlanNutricional.getString(0));
                cursorPlanNutricional.close();*/
                listaHistorialPlanes.add(historialPlanes);
            } while (cursorPlanes.moveToNext());
        }

        cursorPlanes.close();

        return listaHistorialPlanes;
    }
    public ArrayList<HistorialPlanes> mostrarItemsHistorialPlan(int idHijo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<HistorialPlanes> listaHistorialPlanes = new ArrayList<>();
        HistorialPlanes historialPlanes;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_Historial_Planes_Nutricionales +" WHERE "+Utilidades.CAMPO_ID_HIJO3+" = "+idHijo+"", null);

        if (cursorPlanes.moveToFirst()) {
            do {
                historialPlanes = new HistorialPlanes();
                historialPlanes.setIdHijo(cursorPlanes.getInt(1));
                historialPlanes.setIdPlanNutricional(cursorPlanes.getInt(2));
                historialPlanes.setCumplimientoDelPlan(cursorPlanes.getInt(4));
                historialPlanes.setVistoBueno(cursorPlanes.getString(5));
                Cursor cursorPlanNutricional = db.rawQuery("SELECT " + Utilidades.CAMPO_NOMBREPlanNutricional + " FROM " + Utilidades.TABLA_PlanNutricional +" WHERE "+Utilidades.CAMPO_ID_PlanNutricional+" = "+historialPlanes.getIdPlanNutricional()+"", null);
                cursorPlanNutricional.moveToFirst();
                historialPlanes.setNombrePlanNutricional(cursorPlanNutricional.getString(0));
                cursorPlanNutricional.close();
                listaHistorialPlanes.add(historialPlanes);
            } while (cursorPlanes.moveToNext());
        }

        cursorPlanes.close();

        return listaHistorialPlanes;
    }
    public String verVistoBueno(int IdHijo, int idPlanNutricional) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String verVistoBueno = "";

        PlanesNutricionales planesNutricionales=null;
        Cursor cursorPlanes;

        cursorPlanes = db.rawQuery("SELECT "+Utilidades.CAMPO_Visto_Bueno_Nutriologo+" FROM  "+Utilidades.TABLA_Historial_Planes_Nutricionales+" WHERE "+Utilidades.CAMPO_ID_HIJO3+"= '"+ IdHijo +"' AND "+Utilidades.CAMPO_ID_PlanNutricional4+" = '"+idPlanNutricional +"'", null);

        if (cursorPlanes.moveToFirst()) {
            verVistoBueno =cursorPlanes.getString(0);
        }

        cursorPlanes.close();

        return verVistoBueno;
    }
}

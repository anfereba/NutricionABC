package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.VistasDb.VistaCalificacionUsuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;


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

    public ArrayList<VistaCalificacionUsuario> ConsultarListaCalificaciones(int Id_Nutriologo) {
        Log.i("Id Nutrilogo",String.valueOf(Id_Nutriologo));
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<VistaCalificacionUsuario> listaCalificaciones = new ArrayList<>();
        VistaCalificacionUsuario calificacion = null;
        Cursor cursorCalificaciones = null;
        String consulta = "SELECT * FROM "+Utilidades.VISTA_CALIFICACION_USUARIO+
                " WHERE "+Utilidades.CAMPO_VISTA_ID_NUTRIOLOGO +" = "+Id_Nutriologo;
        cursorCalificaciones = db.rawQuery(consulta,null);
        Log.i("Consulta",consulta);
        if (cursorCalificaciones.moveToFirst()){
            do{
                calificacion = new VistaCalificacionUsuario();
                calificacion.setIdCalificacion(cursorCalificaciones.getInt(0));
                calificacion.setIdPadre(cursorCalificaciones.getInt(1));
                calificacion.setIdNutriologo(cursorCalificaciones.getInt(2));
                calificacion.setNombrePadre(cursorCalificaciones.getString(3));
                calificacion.setApellidosPadre(cursorCalificaciones.getString(4));
                calificacion.setCorreoPadre(cursorCalificaciones.getString(5));
                calificacion.setFotoPadre(cursorCalificaciones.getBlob(6));
                calificacion.setNombreNutriologo(cursorCalificaciones.getString(7));
                calificacion.setApellidosNutriologo(cursorCalificaciones.getString(8));
                calificacion.setCorreoNutriologo(cursorCalificaciones.getString(9));
                calificacion.setFotoNutriologo(cursorCalificaciones.getBlob(10));
                calificacion.setPuntuacion(cursorCalificaciones.getDouble(11));
                calificacion.setComentario(cursorCalificaciones.getString(12));
                listaCalificaciones.add(calificacion);
            }while (cursorCalificaciones.moveToNext());
        }
        cursorCalificaciones.close();
        return listaCalificaciones;
    }
}

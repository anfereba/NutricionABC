package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

public class DbCalificacion extends DbHelper {
    Context context;
    public DbCalificacion(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarCalificacion(int IdUsuario,int IdPerfilSistema ) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID_USUARIO_3,IdUsuario);
            values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA_3,IdPerfilSistema);
            values.put(Utilidades.CAMPO_Rating,IdPerfilSistema);

            id = db.insert(Utilidades.TABLA_Calificacion, null, values);
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Usuario> mostrarCalificacion() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuario> listaCalificacionNutri = new ArrayList<>();
        Usuario calificacionNutri;
        Cursor cursorCalificacion;
//trae la informacion de la tabla
        cursorCalificacion = db.rawQuery("SELECT idUsuario,idPerfilSistema,Nombres FROM " + Utilidades.TABLA_USUARIO +" WHERE "+Utilidades.CAMPO_ID_PERFIL_SISTEMA+ "="+ 2, null);
//si hay resultados movetofirst

      ///ME FALTA REVISAR COMO FUNCIONA
      /*  if (cursorCalificacion.moveToFirst()) {
            do {
                calificacionNutri = new Usuario();
                calificacionNutri.setIdUsuario(cursorCalificacion.getInt(0));
                calificacionNutri.setIdPerfilSistema(cursorCalificacion.getInt(1));
                calificacionNutri.setNombres(cursorCalificacion.getString(2));
                listaCalificacionNutri.add(calificacionNutri);
            } while (cursorCalificacion.moveToNext());
        }*/
      //  cursorCalificacion.close();


        return listaCalificacionNutri;
    }


}

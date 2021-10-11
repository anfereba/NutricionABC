package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

import static com.anfereba.nutricionabc.db.utilidades.Utilidades.TABLA_Calificacion;
import static com.anfereba.nutricionabc.db.utilidades.Utilidades.TABLA_USUARIO;

public class DbCalificacion extends DbHelper {

    Context context;
    public DbCalificacion(@Nullable Context context) {
        super(context);
        this.context=context;
    }
/*
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
*/
    public Cursor mostrarCalificacion() {
try {
    SQLiteDatabase db =this.getReadableDatabase();
    Cursor cursorCalificacion = db.rawQuery("SELECT * FROM " + TABLA_USUARIO + "", null);
    if (cursorCalificacion.moveToFirst()){
        return cursorCalificacion;
    }else{
        return null;
    }
} catch (Exception ex) {
return null;
          }
    }
}

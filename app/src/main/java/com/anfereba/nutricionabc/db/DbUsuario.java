package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import org.w3c.dom.Text;

import okhttp3.internal.Util;


public class DbUsuario extends DbHelper{

    Context context;



    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarUsuario(String nombres, String apellidos, String FechaNacimiento,
                                 String correo, String password, String Direccion, String Ciudad, String Telefono, String FechaCreacion, byte[] FotoUsuario) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,1); // <---- Por defecto se registrara como cliente
            values.put(Utilidades.CAMPO_NOMBRES, nombres);
            values.put(Utilidades.CAMPO_APELLIDOS, apellidos);
            values.put(Utilidades.CAMPO_FECHA_NACIMIENTO, FechaNacimiento);
            values.put(Utilidades.CAMPO_CORREO, correo);
            values.put(Utilidades.CAMPO_PASSWORD,password);
            values.put(Utilidades.CAMPO_DIRECCION_USUARIO, Direccion);
            values.put(Utilidades.CAMPO_CIUDAD, Ciudad);
            values.put(Utilidades.CAMPO_TELEFONO,Telefono);
            values.put(Utilidades.CAMPO_FECHA_CREACION,FechaCreacion);
            values.put(Utilidades.CAMPO_FOTO_USUARIO,FotoUsuario);

            id = db.insert(Utilidades.TABLA_USUARIO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public boolean Comprobar_Correo(String email){
        boolean existeCorreo = false;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,
                    new String[]{Utilidades.CAMPO_CORREO},
                    Utilidades.CAMPO_CORREO+"=?",
                    new String[]{email},null,null,null);

            cursor.moveToFirst();
            if (cursor.getCount() > 0){
                existeCorreo = true;
            }else {
                existeCorreo = false;
            }
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            existeCorreo = false;
        }
        return existeCorreo;
    }

    public String Comprobar_Correo_Password(String email, String password){
        String CredencialesCorrectas = null;

        String[] parametros = {email, password};

        String query = "SELECT a."+Utilidades.CAMPO_CORREO+
                ", a."+Utilidades.CAMPO_PASSWORD+", b."+Utilidades.CAMPO_NOMBRE_PERFIL
                +" FROM "+ Utilidades.TABLA_USUARIO+ " a INNER JOIN "+Utilidades.TABLA_PERFIL_SISTEMA+ " b ON "
                +"a."+Utilidades.CAMPO_ID_PERFIL_SISTEMA+" = b."+Utilidades.CAMPO_ID_PERFIL_SISTEMA_2
                +" WHERE a."+Utilidades.CAMPO_CORREO+" =? AND a."+Utilidades.CAMPO_PASSWORD+ " =?";

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query,parametros);
            cursor.moveToFirst();
            if (cursor.getCount() > 0){
                CredencialesCorrectas = cursor.getString(2);
            }else {
                CredencialesCorrectas = null;
            }
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return CredencialesCorrectas;
    }

    public String Comprobar_Perfil(int IdSesion){
        String CredencialesCorrectas = null;

        String[] parametros = {Integer.toString(IdSesion)};

        String query = "SELECT a."+Utilidades.CAMPO_CORREO+
                ", a."+Utilidades.CAMPO_PASSWORD+", b."+Utilidades.CAMPO_NOMBRE_PERFIL
                +" FROM "+ Utilidades.TABLA_USUARIO+ " a INNER JOIN "+Utilidades.TABLA_PERFIL_SISTEMA+ " b ON "
                +"a."+Utilidades.CAMPO_ID_PERFIL_SISTEMA+" = b."+Utilidades.CAMPO_ID_PERFIL_SISTEMA_2
                +" WHERE a."+Utilidades.CAMPO_ID_PERFIL_SISTEMA+" =?";

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query,parametros);
            cursor.moveToFirst();
            if (cursor.getCount() > 0){
                CredencialesCorrectas = cursor.getString(2);
            }else {
                CredencialesCorrectas = null;
            }
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return CredencialesCorrectas;
    }


    public String consultarDato(String variableAconsultar, String filtroparabuscar, String informacionDelFiltro){
        /*Esta consulta solo sirve para buscar un String*/
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        //Cursor cursorClientes= db.rawQuery(" SELECT * FROM "+TABLE_CLIENTES+" WHERE id=1",null); consulta de un numero
        Cursor cursorClientes= db.rawQuery(" SELECT "+variableAconsultar+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+filtroparabuscar+"='"+informacionDelFiltro+"'",null);
        cursorClientes.moveToFirst();


        String dato =cursorClientes.getString(0);
        cursorClientes.close();
        return dato;
    }
}

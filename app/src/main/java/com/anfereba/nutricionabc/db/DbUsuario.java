package com.anfereba.nutricionabc.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.AESCrypt;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.internal.Util;


public class DbUsuario extends DbHelper{

    Context context;
    ArrayList<String> listaUsuarios;
    ArrayList<Usuario> usuariosList;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarUsuario(String nombres, String apellidos, String FechaNacimiento,
                                 String correo, String password, String Direccion,
                                String Ciudad, String Telefono, String FechaCreacion,
                                byte[] FotoUsuario, int idPerfil, String PreguntaUno, String PreguntaDos) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            password = AESCrypt.encrypt(password);

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID_PERFIL_SISTEMA,idPerfil); // <---- Por defecto se registrara como cliente
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
            values.put(Utilidades.CAMPO_PREGUNTA_UNO,PreguntaUno);
            values.put(Utilidades.CAMPO_PREGUNTA_DOS,PreguntaDos);

            id = db.insert(Utilidades.TABLA_USUARIO, null, values);

            db.close();
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
            db.close();
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            existeCorreo = false;
        }
        return existeCorreo;
    }

    public String Comprobar_Correo_Password(String email, String password){
        String CredencialesCorrectas = null;

        try{

            password = AESCrypt.encrypt(password);
            String[] parametros = {email, password};
            String query = "SELECT a."+Utilidades.CAMPO_CORREO+
                    ", a."+Utilidades.CAMPO_PASSWORD+", b."+Utilidades.CAMPO_NOMBRE_PERFIL
                    +" FROM "+ Utilidades.TABLA_USUARIO+ " a INNER JOIN "+Utilidades.TABLA_PERFIL_SISTEMA+ " b ON "
                    +"a."+Utilidades.CAMPO_ID_PERFIL_SISTEMA+" = b."+Utilidades.CAMPO_ID_PERFIL_SISTEMA_2
                    +" WHERE a."+Utilidades.CAMPO_CORREO+" =? AND a."+Utilidades.CAMPO_PASSWORD+ " =?";

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query,parametros);
            cursor.moveToFirst();
            if (cursor.getCount() > 0){
                CredencialesCorrectas = cursor.getString(2);
            }else {
                CredencialesCorrectas = null;
            }
            db.close();
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return CredencialesCorrectas;
    }

    public String Comprobar_Perfil(int IdSesion){
        String CredencialesCorrectas = "";


        String query = "SELECT a."+Utilidades.CAMPO_CORREO+
                ", a."+Utilidades.CAMPO_PASSWORD+", b."+Utilidades.CAMPO_NOMBRE_PERFIL
                +" FROM "+ Utilidades.TABLA_USUARIO+ " a INNER JOIN "+Utilidades.TABLA_PERFIL_SISTEMA+ " b ON "
                +"a."+Utilidades.CAMPO_ID_PERFIL_SISTEMA+" = b."+Utilidades.CAMPO_ID_PERFIL_SISTEMA_2
                +" WHERE a."+Utilidades.CAMPO_ID_USUARIO+" = "+IdSesion;

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query,null);


            cursor.moveToFirst();
            if (cursor.getCount() > 0){
                CredencialesCorrectas = cursor.getString(2);
            }else {
                CredencialesCorrectas = "";

            }
            db.close();
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

    public void actualizarUsuario(String Nombres, String Apellidos, String FechaNacimiento, String Correo, String Direccion, String ciudad, String Telefono, int Id_Usuario){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] parametros = {Integer.toString(Id_Usuario)};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRES,Nombres);
        values.put(Utilidades.CAMPO_APELLIDOS,Apellidos);
        values.put(Utilidades.CAMPO_FECHA_NACIMIENTO,FechaNacimiento);
        values.put(Utilidades.CAMPO_CORREO,Correo);
        values.put(Utilidades.CAMPO_DIRECCION_USUARIO,Direccion);
        values.put(Utilidades.CAMPO_CIUDAD,ciudad);
        values.put(Utilidades.CAMPO_TELEFONO,Telefono);
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID_USUARIO+"=?",parametros);
        Toast.makeText(context, "Datos Actualizados", Toast.LENGTH_SHORT).show();
        db.close();
    }


    public void actualizarUsuario(String Nombres, String Apellidos, String FechaNacimiento, String Correo, String Direccion, String ciudad, String Telefono,byte[] FotoUsuario, int Id_Usuario){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] parametros = {Integer.toString(Id_Usuario)};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRES,Nombres);
        values.put(Utilidades.CAMPO_APELLIDOS,Apellidos);
        values.put(Utilidades.CAMPO_FECHA_NACIMIENTO,FechaNacimiento);
        values.put(Utilidades.CAMPO_CORREO,Correo);
        values.put(Utilidades.CAMPO_DIRECCION_USUARIO,Direccion);
        values.put(Utilidades.CAMPO_CIUDAD,ciudad);
        values.put(Utilidades.CAMPO_TELEFONO,Telefono);
        values.put(Utilidades.CAMPO_FOTO_USUARIO,FotoUsuario);
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID_USUARIO+"=?",parametros);
        Toast.makeText(context, "Datos Actualizados", Toast.LENGTH_SHORT).show();
        db.close();
    }



    public void eliminarUsuario(String Id_Usuario){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] parametros = {Id_Usuario};
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID_USUARIO+"=?",parametros);
        Toast.makeText(context, "Ya se elimino el usuario", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public ArrayList<Usuario> ConsultarListaUsuarios(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario = null;
        Cursor cursorUsuarios = null;
        String consulta = "SELECT * FROM "+Utilidades.TABLA_USUARIO;

        cursorUsuarios = db.rawQuery(consulta,null);
        if (cursorUsuarios.moveToFirst()){
            do{
                usuario = new Usuario();
                usuario.setIdUsuario(cursorUsuarios.getInt(0));
                usuario.setIdPerfilSistema(cursorUsuarios.getInt(1));
                usuario.setNombres(cursorUsuarios.getString(2));
                usuario.setApellidos(cursorUsuarios.getString(3));
                usuario.setFechaNacimiento(cursorUsuarios.getString(4));
                usuario.setCorreo(cursorUsuarios.getString(5));
                usuario.setPassword(cursorUsuarios.getString(6));
                usuario.setDireccion(cursorUsuarios.getString(7));
                usuario.setCiudad(cursorUsuarios.getString(8));
                usuario.setTelefono(cursorUsuarios.getString(9));
                usuario.setFechaCreacion(cursorUsuarios.getString(10));
                usuario.setFotoPerfil(cursorUsuarios.getBlob(11));
                usuario.setPreguntaUno(cursorUsuarios.getString(12));
                usuario.setPreguntaDos(cursorUsuarios.getString(13));
                listaUsuarios.add(usuario);
            }while (cursorUsuarios.moveToNext());
        }
        cursorUsuarios.close();
        return listaUsuarios;
    }

    public long RestablecerPassword(String Correo, String Password, String PreguntaUno, String PreguntaDos) {

        long id = 0;
        try {
            Password = AESCrypt.encrypt(Password);
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String[] parametros = {Correo,PreguntaUno,PreguntaDos};
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_PASSWORD,Password);
            id = db.update(Utilidades.TABLA_USUARIO,values,
                    Utilidades.CAMPO_CORREO+"=? and "
                            +Utilidades.CAMPO_PREGUNTA_UNO+"=? and "
                            +Utilidades.CAMPO_PREGUNTA_DOS+"=?",parametros);
            db.close();

        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return id;
    }

    public ArrayList<Usuario> ObtenerDatosUsuario(int idUsuario) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Usuario> datosUsuario = new ArrayList<>();
        Usuario usuario = null;
        Cursor cursorUsuarios = null;
        String consulta = "SELECT * FROM "+Utilidades.TABLA_USUARIO + " WHERE "+Utilidades.CAMPO_ID_USUARIO + " = "+idUsuario;
        cursorUsuarios = db.rawQuery(consulta,null);
        if (cursorUsuarios.moveToFirst()){
            usuario = new Usuario();
            usuario.setIdUsuario(cursorUsuarios.getInt(0));
            usuario.setIdPerfilSistema(cursorUsuarios.getInt(1));
            usuario.setNombres(cursorUsuarios.getString(2));
            usuario.setApellidos(cursorUsuarios.getString(3));
            usuario.setFechaNacimiento(cursorUsuarios.getString(4));
            usuario.setCorreo(cursorUsuarios.getString(5));
            usuario.setPassword(cursorUsuarios.getString(6));
            usuario.setDireccion(cursorUsuarios.getString(7));
            usuario.setCiudad(cursorUsuarios.getString(8));
            usuario.setTelefono(cursorUsuarios.getString(9));
            usuario.setFechaCreacion(cursorUsuarios.getString(10));
            usuario.setFotoPerfil(cursorUsuarios.getBlob(11));
            usuario.setPreguntaUno(cursorUsuarios.getString(12));
            usuario.setPreguntaDos(cursorUsuarios.getString(13));
            datosUsuario.add(usuario);
        }
        cursorUsuarios.close();
        return datosUsuario;
    }
}

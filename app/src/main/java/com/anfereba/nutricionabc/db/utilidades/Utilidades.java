package com.anfereba.nutricionabc.db.utilidades;

public class Utilidades {

    // Base de datos

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOMBRE = "NutricionABC.db";

    //Campos Tabla PerfilSistema

    public static final String TABLA_PERFIL_SISTEMA = "PerfilSistema";
    public static final String CAMPO_ID_PERFIL_SISTEMA_2 = "idPerfilSistema";
    public static final String CAMPO_NOMBRE_PERFIL = "NombrePefil";

    //Campos Tabla Usuario

    public static final String TABLA_USUARIO = "Usuario";
    public static final String CAMPO_ID_USUARIO = "idUsuario";
    public static final String CAMPO_ID_PERFIL_SISTEMA = "idPerfilSistema"; //FK_PerfilSistema
    public static final String CAMPO_NOMBRES = "Nombres";
    public static final String CAMPO_APELLIDOS = "Apellidos";
    public static final String CAMPO_FECHA_NACIMIENTO = "FechaNacimiento";
    public static final String CAMPO_CORREO = "Correo";
    public static final String CAMPO_PASSWORD = "Password";
    public static final String CAMPO_FECHA_CREACION = "FechaCreacion";
    public static final String CAMPO_FOTO_USUARIO = "FotoPerfil";


    //Sentencia para crear tabla PerfilSistema

    public static final String CREAR_TABLA_PERFIL_SISTEMA = "CREATE TABLE "
            + TABLA_PERFIL_SISTEMA + "(" + CAMPO_ID_PERFIL_SISTEMA_2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_NOMBRE_PERFIL + " VARCHAR(100) NOT NULL UNIQUE)";

    //Sentencia para insertar perfiles

    public static String INSERTAR_PERFIL_DEFAULT(String VALOR_NOMBRE_PERFIL){
        return "INSERT INTO "
                + TABLA_PERFIL_SISTEMA + " (" +CAMPO_NOMBRE_PERFIL  + ")"
                + " VALUES " +"('"+VALOR_NOMBRE_PERFIL+"') ";
    }

    //Sentencia para crear tabla Usuario

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "
            + TABLA_USUARIO + "(" + CAMPO_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_PERFIL_SISTEMA + " INTEGER NOT NULL, "
            + CAMPO_NOMBRES + " VARCHAR(100) NOT NULL, "
            + CAMPO_APELLIDOS + " VARCHAR(100) NOT NULL, "
            + CAMPO_FECHA_NACIMIENTO + " DATETIME NOT NULL, "
            + CAMPO_CORREO + " VARCHAR(100) NOT NULL UNIQUE, "
            + CAMPO_PASSWORD + " VARCHAR(100) NOT NULL, "
            + CAMPO_FECHA_CREACION + " VARCHAR(100) NOT NULL, "
            + CAMPO_FOTO_USUARIO + " BLOB NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_PERFIL_SISTEMA+") "
            +"REFERENCES "+TABLA_PERFIL_SISTEMA+"("+CAMPO_ID_PERFIL_SISTEMA+"))";



}

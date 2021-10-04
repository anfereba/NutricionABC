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
    public static final String CAMPO_DIRECCION_USUARIO = "Direccion";
    public static final String CAMPO_CIUDAD = "Ciudad";
    public static final String CAMPO_TELEFONO = "Telefono";
    public static final String CAMPO_FOTO_USUARIO = "FotoPerfil";


    //Campos Tabla PlanNutricional
    public static final String TABLA_PlanNutricional = "PlanNutricional";
    public static final String CAMPO_ID_PlanNutricional = "idPlanNutricional";
    public static final String CAMPO_ID_USUARIO2 = "idUsuario"; //FK_idUsuario
    public static final String CAMPO_NOMBREPlanNutricional = "NombrePlamNutricional";

    //Campos Tabla Alimento
    public static final String TABLA_Alimento = "Alimento";
    public static final String CAMPO_ID_Alimento = "idAlimento";
    public static final String CAMPO_NOMBREAlimento = "NombreAlimento";

    //Campos Tabla PlanAlimento
    public static final String TABLA_PlanAlimento = "PlanAlimento";
    public static final String CAMPO_ID_PlanAlimento = "idPlanAlimento";
    public static final String CAMPO_ID_PlanNutricional2 = "idPlanNutricional";//FK_PlanNutricional
    public static final String CAMPO_ID_Alimento2 = "idAlimento";//FK_idAlimento



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
            + CAMPO_DIRECCION_USUARIO + " VARCHAR(70) NOT NULL, "
            + CAMPO_CIUDAD + " VARCHAR(50) NOT NULL, "
            + CAMPO_TELEFONO + " VARCHAR(11) NOT NULL, "
            + CAMPO_FECHA_CREACION + " VARCHAR(100) NOT NULL, "
            + CAMPO_FOTO_USUARIO + " BLOB NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_PERFIL_SISTEMA+") "
            +"REFERENCES "+TABLA_PERFIL_SISTEMA+"("+CAMPO_ID_PERFIL_SISTEMA+"))";

    //Sentencia para crear tabla PlanNutricional

    public static final String CREAR_TABLA_PlanNutricional = "CREATE TABLE "
            + TABLA_PlanNutricional + "(" + CAMPO_ID_PlanNutricional + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_USUARIO2 + " INTEGER NOT NULL, "
            + CAMPO_NOMBREPlanNutricional + " VARCHAR(100) NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_USUARIO2+") "
            +"REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID_USUARIO2+"))";

    //Sentencia para crear tabla TABLA_Alimento

    public static final String CREAR_TABLA_Alimento = "CREATE TABLE "
            + TABLA_Alimento + "(" + CAMPO_ID_Alimento + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_NOMBREAlimento + " VARCHAR(100) NOT NULL)";

    //Sentencia para crear tabla TABLA_PlanAlimento

    public static final String CREAR_TABLA_PlanAlimento = "CREATE TABLE "
            + TABLA_PlanAlimento + "(" + CAMPO_ID_PlanAlimento + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_PlanNutricional2 + " VARCHAR(100) NOT NULL, "
            + CAMPO_ID_Alimento2 + " VARCHAR(100) NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_PlanNutricional2+") "
            +"REFERENCES "+TABLA_PlanNutricional+"("+CAMPO_ID_PlanNutricional2+")"
            +"FOREIGN KEY"+ "("+CAMPO_ID_Alimento2+") "
            +"REFERENCES "+TABLA_Alimento+"("+CAMPO_ID_Alimento2+"))";




}
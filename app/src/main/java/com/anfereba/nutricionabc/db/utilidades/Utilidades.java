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
    public static final String CAMPO_PREGUNTA_UNO = "PreguntaUno";
    public static final String CAMPO_PREGUNTA_DOS = "PreguntaDos";

    //Campos Tabla PlanNutricional

    public static final String TABLA_PlanNutricional = "PlanNutricional";
    public static final String CAMPO_ID_PlanNutricional = "idPlanNutricional";
    public static final String CAMPO_ID_USUARIO2 = "idUsuario"; //FK_idUsuario
    public static final String CAMPO_NOMBREPlanNutricional = "NombrePlamNutricional";

    //Campos Tabla Alimento

    public static final String TABLA_Alimento = "Alimento";
    public static final String CAMPO_ID_Alimento = "idAlimento";
    public static final String CAMPO_NOMBREAlimento = "NombreAlimento";
    public static final String CAMPO_FOTO_ALIMENTO = "FotoAlimento";
    public static final String CAMPO_ID_CALORIAS = "Calorias";

    //Campos Tabla PlanAlimento

    public static final String TABLA_PlanAlimento = "PlanAlimento";
    public static final String CAMPO_ID_PlanAlimento = "idPlanAlimento";
    public static final String CAMPO_ID_PlanNutricional2 = "idPlanNutricional";//FK_PlanNutricional
    public static final String CAMPO_ID_Alimento2 = "idAlimento";//FK_idAlimento
    public static final String CAMPO_Dia = "Dia";

    //Campos Tabla Hijo

    public static final String TABLA_Hijo = "Hijo";
    public static final String CAMPO_ID_HIJO = "idHijo";
    public static final String CAMPO_FOTO_HIJO = "FotoHijo";
    public static final String CAMPO_NOMBRE_HIJO = "NombreHijo";
    public static final String CAMPO_ESTATURA_HIJO = "Estatura";
    public static final String CAMPO_EDAD_HIJO = "Edad";
    public static final String CAMPO_PESO_HIJO = "Peso";
    public static final String CAMPO_ID_USUARIO3 = "idUsuario";//FK_idUsuario
    public static final String CAMPO_ID_PlanNutricional3 = "idPlanNutricional";//FK_idPlanNutricional

    //Campos Tabla de calificacion

    public static final String TABLA_CALIFICACION = "Calificacion";
    public static final String CAMPO_ID_CALIFICACION = "IdCalificacion";
    public static final String CAMPO_ID_PADRE_CALIFICACION = "IdPadre";//FK_Usuario
    public static final String CAMPO_ID_NUTRIOLOGO_CALIFICACION = "IdNutriologo";//FK_PerfilSistema
    public static final String CAMPO_PUNTUACION = "Puntuacion";
    public static final String CAMPO_COMENTARIO = "Comentario";

    //Campos Vista V_CALIFICACION_USUARIO

    public static final String VISTA_CALIFICACION_USUARIO = "v_calificacion_usuario";
    public static final String CAMPO_VISTA_ID_CALIFICACION = "IdCalificacion";
    public static final String CAMPO_VISTA_ID_PADRE = "IdPadre";
    public static final String CAMPO_VISTA_ID_NUTRIOLOGO = "IdNutriologo";
    public static String CAMPO_VISTA_NOMBRE_PADRE = "Nombres";
    public static String CAMPO_VISTA_APELLIDOS_PADRE = "Apellidos";
    public static String CAMPO_VISTA_CORREO_PADRE = "Correo";
    public static String CAMPO_VISTA_FOTO_PADRE = "FotoPerfil";
    public static String CAMPO_VISTA_NOMBRE_NUTRIOLOGO = "Nombres";
    public static String CAMPO_VISTA_APELLIDOS_NUTRIOLOGO = "Apellidos";
    public static String CAMPO_VISTA_CORREO_NUTRIOLOGO = "Correo";
    public static String CAMPO_VISTA_FOTO_NUTRIOLOGO = "FotoPerfil";
    public static final String CAMPO_VISTA_PUNTUACION = "Puntuacion";
    public static final String CAMPO_VISTA_COMENTARIO = "Comentario";

    public static final String ALIAS_CAMPO_NOMBRE_PADRE = "nombrePadre";
    public static final String ALIAS_CAMPO_APELLIDOS_PADRE = "apellidosPadre";
    public static final String ALIAS_CAMPO_CORREO_PADRE = "CorreoPadre";
    public static final String ALIAS_CAMPO_FOTO_PADRE = "fotoPadre";

    public static final String ALIAS_CAMPO_NOMBRE_NUTRIOLOGO= "nombreNutriologo";
    public static final String ALIAS_CAMPO_APELLIDOS_NUTRIOLOGO = "apellidosNutriologo";
    public static final String ALIAS_CAMPO_CORREO_NUTRIOLOGO = "CorreoNutriologo";
    public static final String ALIAS_CAMPO_FOTO_NUTRIOLOGO = "fotoNutriologo";

//Campos Tabla Cumplimiento Plan Diario

    public static final String TABLA_Cumplimiennto_Plan_Diario = "CumplimienntoPlanDiario";
    public static final String CAMPO_ID_Cumplimiento_Plan_Diario = "idCumplimientoPlanDiario";
    public static final String CAMPO_ID_HIJO2 = "idHijo"; //FK_idHIJO
    public static final String CAMPO_ID_PlanAlimento2 = "idPlanAlimento"; //FK_idPlanAlimento
    public static final String CAMPO_Cumplimiento = "Cumplimiento";

    //Campos Tabla Historial Planes Nutricionales

    public static final String TABLA_Historial_Planes_Nutricionales = "HistorialPlanesNutricionales";
    public static final String CAMPO_ID_Historial_Planes_Nutricionales = "idHistorialPlanNutricional";
    public static final String CAMPO_ID_HIJO3 = "idHijo"; //FK_idHIJO
    public static final String CAMPO_ID_PlanNutricional4 = "idPlanNutricional";
    public static final String CAMPO_Comentarios_Nutriologo = "Comentarios_Nutriologo";
    public static final String CAMPO_Cumplimiento2 = "Cumplimiento";
    public static final String CAMPO_Visto_Bueno_Nutriologo = "VistoBueno";



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
            + CAMPO_PREGUNTA_UNO+ " VARCHAR(100) NOT NULL, "
            + CAMPO_PREGUNTA_DOS + " VARCHAR(100) NOT NULL, "
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
            + CAMPO_FOTO_ALIMENTO + " BLOB NOT NULL, "
            + CAMPO_ID_CALORIAS + " INTEGER NOT NULL, "
            + CAMPO_NOMBREAlimento + " VARCHAR(100) NOT NULL)";

    //Sentencia para crear tabla TABLA_PlanAlimento

    public static final String CREAR_TABLA_PlanAlimento = "CREATE TABLE "
            + TABLA_PlanAlimento + "(" + CAMPO_ID_PlanAlimento + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_PlanNutricional2 + " INTEGER NOT NULL, "
            + CAMPO_ID_Alimento2 + " INTEGER NOT NULL, "
            + CAMPO_Dia + " INTEGER NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_PlanNutricional2+") "
            +"REFERENCES "+TABLA_PlanNutricional+"("+CAMPO_ID_PlanNutricional2+")"
            +"FOREIGN KEY"+ "("+CAMPO_ID_Alimento2+") "
            +"REFERENCES "+TABLA_Alimento+"("+CAMPO_ID_Alimento2+"))";
//Sentencia para crear tabla TABLA_HIJO

    public static final String CREAR_TABLA_HIJO = "CREATE TABLE "
            + TABLA_Hijo + "(" + CAMPO_ID_HIJO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_FOTO_HIJO + " BLOB NOT NULL, "
            + CAMPO_NOMBRE_HIJO + " VARCHAR(100) NOT NULL, "
            + CAMPO_ESTATURA_HIJO + " VARCHAR(100) NOT NULL, "
            + CAMPO_EDAD_HIJO + " INTEGER NOT NULL, "
            + CAMPO_PESO_HIJO + " INTEGER NOT NULL, "
            + CAMPO_ID_USUARIO3 + " INTEGER NOT NULL, "
            + CAMPO_ID_PlanNutricional3 + " INTEGER NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_PlanNutricional3+") "
            +"REFERENCES "+TABLA_PlanNutricional+"("+CAMPO_ID_PlanNutricional3+")"
            +"FOREIGN KEY"+ "("+CAMPO_ID_USUARIO3+") "
            +"REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID_USUARIO3+"))";

    //Sentencia para crear tabla TABLA_Calificacion

    public static final String CREAR_TABLA_CALIFICACION = "CREATE TABLE "
            + TABLA_CALIFICACION + "(" + CAMPO_ID_CALIFICACION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_PADRE_CALIFICACION + " INTEGER NOT NULL, "
            + CAMPO_ID_NUTRIOLOGO_CALIFICACION + " INTEGER NOT NULL, "
            + CAMPO_PUNTUACION + " REAL NOT NULL, "
            + CAMPO_COMENTARIO + " TEXT NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_PADRE_CALIFICACION+") "
            +"REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID_USUARIO+")"
            +"FOREIGN KEY"+ "("+CAMPO_ID_NUTRIOLOGO_CALIFICACION+") "
            +"REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID_USUARIO+"))";

    //Crear Vista v_calificacion_usuario;

    public static final String CREAR_VISTA_CALIFIACION_USUARIO = " CREATE VIEW "+Utilidades.VISTA_CALIFICACION_USUARIO + " AS " +
            "SELECT a."+Utilidades.CAMPO_VISTA_ID_CALIFICACION+
            ",a."+CAMPO_VISTA_ID_PADRE+",a."+CAMPO_VISTA_ID_NUTRIOLOGO+
            ",b."+CAMPO_VISTA_NOMBRE_PADRE+ " AS "+ALIAS_CAMPO_NOMBRE_PADRE+
            ",b."+CAMPO_VISTA_APELLIDOS_PADRE+ " AS "+ALIAS_CAMPO_APELLIDOS_PADRE+
            ",b."+CAMPO_VISTA_CORREO_PADRE+ " AS "+ALIAS_CAMPO_CORREO_PADRE+
            ",b."+CAMPO_VISTA_FOTO_PADRE+ " AS "+ALIAS_CAMPO_FOTO_PADRE+
            ",c."+CAMPO_VISTA_NOMBRE_NUTRIOLOGO+ " AS "+ALIAS_CAMPO_NOMBRE_NUTRIOLOGO+
            ",c."+CAMPO_VISTA_APELLIDOS_NUTRIOLOGO+ " AS "+ALIAS_CAMPO_APELLIDOS_NUTRIOLOGO+
            ",c."+CAMPO_VISTA_CORREO_NUTRIOLOGO+ " AS "+ALIAS_CAMPO_CORREO_NUTRIOLOGO+
            ",c."+CAMPO_VISTA_FOTO_NUTRIOLOGO+ " AS "+ALIAS_CAMPO_FOTO_NUTRIOLOGO+
            ",a."+CAMPO_VISTA_PUNTUACION+",a."+CAMPO_VISTA_COMENTARIO+
            " FROM "+TABLA_CALIFICACION+ " a INNER JOIN "+TABLA_USUARIO+" b ON a."+
            CAMPO_VISTA_ID_PADRE+" = b."+CAMPO_ID_USUARIO+
            " INNER JOIN "+TABLA_USUARIO+ " c ON a."+CAMPO_VISTA_ID_NUTRIOLOGO+ " = c."+CAMPO_ID_USUARIO;

    public static void AsignarAlias(){

        CAMPO_VISTA_NOMBRE_PADRE = ALIAS_CAMPO_NOMBRE_PADRE;
        CAMPO_VISTA_APELLIDOS_PADRE = ALIAS_CAMPO_APELLIDOS_PADRE;
        CAMPO_VISTA_CORREO_PADRE = ALIAS_CAMPO_CORREO_PADRE;
        CAMPO_VISTA_FOTO_PADRE = ALIAS_CAMPO_FOTO_PADRE;
        CAMPO_VISTA_NOMBRE_NUTRIOLOGO = ALIAS_CAMPO_NOMBRE_NUTRIOLOGO;
        CAMPO_VISTA_APELLIDOS_NUTRIOLOGO = ALIAS_CAMPO_APELLIDOS_NUTRIOLOGO;
        CAMPO_VISTA_CORREO_NUTRIOLOGO = ALIAS_CAMPO_CORREO_NUTRIOLOGO;
        CAMPO_VISTA_FOTO_NUTRIOLOGO = ALIAS_CAMPO_FOTO_NUTRIOLOGO;

    }
    //Sentencia para crear tabla CumplimientoPlanDiario

    public static final String CREAR_TABLA_Cumplimiennto_Plan_Diario = "CREATE TABLE "
            + TABLA_Cumplimiennto_Plan_Diario + "(" + CAMPO_ID_Cumplimiento_Plan_Diario + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_HIJO2 + " INTEGER NOT NULL, "
            + CAMPO_ID_PlanAlimento2 + " INTEGER NOT NULL, "
            + CAMPO_Cumplimiento + " INTEGER NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_HIJO2+") "
            +"REFERENCES "+TABLA_Hijo+"("+CAMPO_ID_HIJO+")"
            +"FOREIGN KEY"+ "("+CAMPO_ID_PlanAlimento2+") "
            +"REFERENCES "+TABLA_PlanAlimento+"("+CAMPO_ID_PlanAlimento+"))";

    //Sentencia para crear TABLA_Historial_Planes_Nutricionales
    public static final String CREAR_TABLA_Historial_Planes_Nutricionales = "CREATE TABLE "
            + TABLA_Historial_Planes_Nutricionales + "(" + CAMPO_ID_Historial_Planes_Nutricionales + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_HIJO3 + " INTEGER NOT NULL, "
            + CAMPO_ID_PlanNutricional4 + " INTEGER NOT NULL, "
            + CAMPO_Comentarios_Nutriologo + " TEXT NOT NULL, "
            + CAMPO_Cumplimiento2 + " INTEGER NOT NULL, "
            + CAMPO_Visto_Bueno_Nutriologo + " BOOLEAN NOT NULL, "
            +"FOREIGN KEY"+ "("+CAMPO_ID_HIJO3+") "
            +"REFERENCES "+TABLA_Hijo+"("+CAMPO_ID_HIJO+"))";

}

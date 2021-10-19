package com.anfereba.nutricionabc.db;//utilizaremos esta para crear las bases de datos

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.anfereba.nutricionabc.db.utilidades.Utilidades;



//Aqui podremos crear las tablas y si queremos poner las versiones de la base de datos
public class DbHelper extends SQLiteOpenHelper {


    public static final String TABLE_CLIENTES = "t_clientes";//Creamos la tabla de clientes
    public static final String TABLE_NUTRIOLOGO = "t_nutriologo";//Creamos la tabla de Nutriologo
    public static final String TABLE_ADMINISTRADOR = "t_administrador";//Creamos la tabla de Nutriologo

    //constructor
        public DbHelper(@Nullable Context context) {
            super(context, Utilidades.DATABASE_NOMBRE, null, Utilidades.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_PERFIL_SISTEMA);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_PlanNutricional);
        db.execSQL(Utilidades.CREAR_TABLA_Alimento);
        db.execSQL(Utilidades.CREAR_TABLA_PlanAlimento);
        db.execSQL(Utilidades.CREAR_TABLA_HIJO);
        db.execSQL(Utilidades.CREAR_TABLA_CALIFICACION);

        db.execSQL(Utilidades.INSERTAR_PERFIL_DEFAULT("Cliente"));
        db.execSQL(Utilidades.INSERTAR_PERFIL_DEFAULT("Nutriologo"));
        db.execSQL(Utilidades.INSERTAR_PERFIL_DEFAULT("Administrador"));

        db.execSQL(Utilidades.CREAR_VISTA_CALIFIACION_USUARIO);
        Utilidades.AsignarAlias();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {/*esta es utilizada para
     las actualizaciones // lo que hace es eliminar la tabla anterior y poner la version nueva depende
     mucho del numero que pongan arriba*/

        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_USUARIO);
        
        //sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NUTRIOLOGO);
        onCreate(db);

    }
}

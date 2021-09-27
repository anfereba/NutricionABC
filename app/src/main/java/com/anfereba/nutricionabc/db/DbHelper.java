package com.anfereba.nutricionabc.db;//utilizaremos esta para crear las bases de datos

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//Aqui podremos crear las tablas y si queremos poner las versiones de la base de datos
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; //es la version de actualizaciones que le hagamos a la base de datos
    private static final String DATABASE_NOMBRE = "NutricionABC.db";//este es el nombre de la base de datos
    public static final String TABLE_CLIENTES = "t_clientes";//Creamos la tabla de clientes
    public static final String TABLE_NUTRIOLOGO = "t_nutriologo";//Creamos la tabla de Nutriologo
        public DbHelper(@Nullable Context context) {

            super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +/*Creamos la tabla nutriologo y
                 le asignamos los atributos */
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "contraseña TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "correo TEXT NoT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NUTRIOLOGO + "(" +/*Creamos la tabla nutriologo y
                 le asignamos los atributos */
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "contraseña TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "correo TEXT NoT NULL)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {/*esta es utilizada para
     las actualizaciones // lo que hace es eliminar la tabla anterior y poner la version nueva depende
     mucho del numero que pongan arriba*/

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NUTRIOLOGO);
        onCreate(sqLiteDatabase);

    }
}

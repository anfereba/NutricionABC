package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.anfereba.nutricionabc.db.DbHelper;

public class Carga extends AppCompatActivity {

    TextView app_name, desarrolladores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga);

        app_name = findViewById(R.id.app_name);
        desarrolladores = findViewById(R.id.desarrolladores);

        //Cambio de Letra

        String ubicacion = "fuentes/sans_negrita.ttf";
        Typeface tf = Typeface.createFromAsset(Carga.this.getAssets(),ubicacion);

        //Creacion de la Base de datos

        DbHelper conn = new DbHelper(this);

        final int DURACION = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Codigo que se ejecutara
                Intent intent = new Intent(Carga.this, Login_App.class);
                startActivity(intent);
                finish();
            }
        },DURACION);

        app_name.setTypeface(tf);
        desarrolladores.setTypeface(tf);
    }
}
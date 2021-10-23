package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.MainActivityNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

public class RegistrarPlanNutricional extends AppCompatActivity {
TextView NombrePlanNutricional;
ImageView btnAtras;
Button Guardarplan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plan_nutricional);
        TomarIdNutriologo(); //Con este podemos traer la informacion que se ha guardado en el login
        NombrePlanNutricional = findViewById(R.id.NombrePlanNutricional);
        Guardarplan=findViewById(R.id.GuardarPlan);
        btnAtras = findViewById(R.id.btnAtras);

        Guardarplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!NombrePlanNutricional.getText().toString().equals("") ) {

                    DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(RegistrarPlanNutricional.this);
                    long id = dbPlanNutricional.insertarPlan(TomarIdNutriologo(),NombrePlanNutricional.getText().toString());

                    if (id > 0) {
                        Toast.makeText(RegistrarPlanNutricional.this, "Plan GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(RegistrarPlanNutricional.this, "ERROR AL GUARDAR ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegistrarPlanNutricional.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void limpiar() {
        NombrePlanNutricional.setText("");

    }
    private Integer TomarIdNutriologo() {
        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);

        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
        return iDNutriologo;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finishAffinity ();//se utiliza para terminará la actividad actual y todas las actividades de los padres
            //((MainActivityNutriologo)getApplicationContext()).finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
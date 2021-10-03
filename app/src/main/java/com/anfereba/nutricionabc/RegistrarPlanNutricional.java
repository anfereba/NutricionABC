package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.utilidades.DbAlimento;
import com.anfereba.nutricionabc.db.utilidades.DbPlanNutricional;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

public class RegistrarPlanNutricional extends AppCompatActivity {
TextView NombrePlanNutricional;
Button Guardarplan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plan_nutricional);
        TomarIdNutriologo();
        NombrePlanNutricional = findViewById(R.id.NombrePlanNutricional);
        Guardarplan=findViewById(R.id.GuardarPlan);
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
    }

    private void limpiar() {
        NombrePlanNutricional.setText("");

    }
    private Integer TomarIdNutriologo() {
        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);

        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
        return iDNutriologo;
    }
}
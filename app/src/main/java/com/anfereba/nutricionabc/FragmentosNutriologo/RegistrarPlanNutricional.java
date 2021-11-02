package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.MainActivityNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class RegistrarPlanNutricional extends AppCompatActivity implements Validator.ValidationListener {

    private boolean DatosValidados;

    @NotEmpty
    @Length(min = 8, max =14)
    EditText NombrePlanNutricional;
    ImageView btnAtras;
    Button Guardarplan;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plan_nutricional);
        TomarIdNutriologo(); //Con este podemos traer la informacion que se ha guardado en el login
        NombrePlanNutricional = findViewById(R.id.NombrePlanNutricional);
        Guardarplan=findViewById(R.id.GuardarPlan);
        btnAtras = findViewById(R.id.btnAtras);

        validator = new Validator(this);
        validator.setValidationListener(this);

        Guardarplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();

                if(DatosValidados) {

                    DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(RegistrarPlanNutricional.this);
                    long id = dbPlanNutricional.insertarPlan(TomarIdNutriologo(),NombrePlanNutricional.getText().toString());

                    if (id > 0) {
                        Toast.makeText(RegistrarPlanNutricional.this, "Plan GUARDADO", Toast.LENGTH_LONG).show();
                        RegistrarPlanNutricional.this.finish();
                        limpiar();
                    } else {
                        Toast.makeText(RegistrarPlanNutricional.this, "ERROR AL GUARDAR ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegistrarPlanNutricional.this, "El campo no puede estar vacio y debe tener entre 8 y 14 caracteres", Toast.LENGTH_LONG).show();
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

    @Override
    public void onValidationSucceeded() {
        DatosValidados = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        DatosValidados = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

        }

    }
}
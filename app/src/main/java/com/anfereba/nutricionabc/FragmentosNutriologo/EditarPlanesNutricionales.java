package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarPlanesNutricionales extends AppCompatActivity {
EditText NombrePlanNutricional;
Button modificarPlanNutricional;
PlanesNutricionales planesNutricionales;
FloatingActionButton fabEditarPlan,fabEliminarPlan;
boolean correcto= false;
int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_planes_nutricionales);
        NombrePlanNutricional=(EditText) findViewById(R.id.ModificarNombrePlan);
        modificarPlanNutricional=(Button) findViewById(R.id.ModificarPlan);
        fabEditarPlan=(FloatingActionButton) findViewById(R.id.floatingEditarPlan);
        fabEliminarPlan=(FloatingActionButton) findViewById(R.id.floatingEliminarPlan);
        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("IdPlanesNutricionales");
            }
        }else{
            id=(int)savedInstanceState.getSerializable("IdPlanesNutricionales");
        }
        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(EditarPlanesNutricionales.this);
        planesNutricionales =dbPlanNutricional.verPlan(id);
        if(planesNutricionales != null){
            NombrePlanNutricional.setText(planesNutricionales.getNombrePlan());
            /*modificarPlanNutricional.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombrePlanNutricional.setInputType(InputType.TYPE_NULL);//le quito el teclado a los */
            fabEditarPlan.setVisibility(View.INVISIBLE);
            fabEliminarPlan.setVisibility(View.INVISIBLE);
            modificarPlanNutricional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!NombrePlanNutricional.getText().toString().equals("")){
                        correcto=dbPlanNutricional.EditarPlan(id,NombrePlanNutricional.getText().toString());
                        verRegistro();
                        if(correcto){
                           Toast.makeText(EditarPlanesNutricionales.this,"Plan Modificado",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(EditarPlanesNutricionales.this,"El Plan no ha sido Modificado",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(EditarPlanesNutricionales.this,"Debe llenar los campos obligatorios",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    private void verRegistro(){
        Intent intent = new Intent(this,VerPlanesNutricionales.class);
        intent.putExtra("IdPlanesNutricionales",id);
        startActivity(intent);
    }
}
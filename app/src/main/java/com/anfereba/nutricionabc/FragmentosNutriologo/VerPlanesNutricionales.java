package com.anfereba.nutricionabc.FragmentosNutriologo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.anfereba.nutricionabc.MainActivityNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerPlanesNutricionales extends AppCompatActivity {
    EditText NombrePlanNutricional;
    Button modificarPlanNutricional;
    PlanesNutricionales planesNutricionales;
    FloatingActionButton fabEditarPlan,fabEliminarPlan;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_planes_nutricionales);
        NombrePlanNutricional=(EditText) findViewById(R.id.ModificarNombrePlan);
        modificarPlanNutricional=(Button) findViewById(R.id.ModificarPlan);
        fabEditarPlan= (FloatingActionButton) findViewById(R.id.floatingEditarPlan);
        fabEliminarPlan= (FloatingActionButton)findViewById(R.id.floatingEliminarPlan);
        fabEditarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPlanesNutricionales.this,EditarPlanesNutricionales.class);
                intent.putExtra("IdPlanesNutricionales",id);
                startActivity(intent);
            }
        });
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
        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(VerPlanesNutricionales.this);
        planesNutricionales =dbPlanNutricional.verPlan(id);
        if(planesNutricionales != null){
            NombrePlanNutricional.setText(planesNutricionales.getNombrePlan());
            modificarPlanNutricional.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombrePlanNutricional.setInputType(InputType.TYPE_NULL);//le quito el teclado a los editext
        }
        fabEliminarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerPlanesNutricionales.this);
                builder.setMessage("¿Desea eliminar este plan?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbPlanNutricional.EliminarPlan(id)){
                            lista();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, MainActivityNutriologo.class);
        startActivity(intent);
    }
}
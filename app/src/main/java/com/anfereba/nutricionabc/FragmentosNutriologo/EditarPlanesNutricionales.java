package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaAlimentosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesAlimentosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesAlimentosEditarAdapter;
import com.anfereba.nutricionabc.MainActivityNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.DbPlanAlimento;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesAlimentos;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditarPlanesNutricionales extends AppCompatActivity {
EditText NombrePlanNutricional;
RecyclerView ListaPlanesAlimentos2;
PlanesNutricionales planesNutricionales;
FloatingActionButton fabEditarPlan,fabEliminarPlan,agregarAlimentos,modificarPlanNutricional;
Spinner spinnerAlimentos;
boolean correcto= false;
int id=0;
int id2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_planes_nutricionales);
        NombrePlanNutricional=(EditText) findViewById(R.id.ModificarNombrePlan);
        modificarPlanNutricional=(FloatingActionButton) findViewById(R.id.ModificarPlan);
        fabEditarPlan=(FloatingActionButton) findViewById(R.id.floatingEditarPlan);
        spinnerAlimentos=(Spinner)findViewById(R.id.spinnerAlimentos);
        agregarAlimentos=(FloatingActionButton)findViewById(R.id.agregarAlimentos);
        fabEliminarPlan=(FloatingActionButton) findViewById(R.id.floatingEliminarPlan);
        ListaPlanesAlimentos2 = (RecyclerView) findViewById(R.id.ListaPlanesAlimentos);
        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
                id2 = Integer.parseInt(null);
            }else{
                id = extras.getInt("IdPlanesNutricionales");
                id2 = extras.getInt("IdPlanAlimento");
            }
        }else{
            id=(int)savedInstanceState.getSerializable("IdPlanesNutricionales");
            id2=(int)savedInstanceState.getSerializable("IdPlanAlimento");
        }
        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(EditarPlanesNutricionales.this);
        planesNutricionales =dbPlanNutricional.verPlan(id);
        if(planesNutricionales != null){
            NombrePlanNutricional.setText(planesNutricionales.getNombrePlan());
            /*modificarPlanNutricional.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombrePlanNutricional.setInputType(InputType.TYPE_NULL);//le quito el teclado a los */
            fabEditarPlan.setVisibility(View.INVISIBLE);
            fabEliminarPlan.setVisibility(View.INVISIBLE);
            spinnerAlimentos.setVisibility(View.INVISIBLE);
            agregarAlimentos.setVisibility(View.INVISIBLE);
            modificarPlanNutricional.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!NombrePlanNutricional.getText().toString().equals("")){
                        correcto=dbPlanNutricional.EditarPlan(id,NombrePlanNutricional.getText().toString());
                        verRegistro();
                        finish();
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
        /*DbPlanAlimento dbPlanAlimento= new DbPlanAlimento(getApplicationContext());
        ListaPlanesAlimentosEditarAdapter adapter = new ListaPlanesAlimentosEditarAdapter(dbPlanAlimento.mostrarIdAlimento(id));
        ListaPlanesAlimentos2.setAdapter(adapter);*/
        ListaPlanesAlimentos2 = (RecyclerView) findViewById(R.id.ListaPlanesAlimentos);
        ListaPlanesAlimentos2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DbPlanAlimento  dbPlanAlimento= new DbPlanAlimento(getApplicationContext());
        ArrayList<PlanesAlimentos> listaArrayAlimentos = new ArrayList<>();
        ListaPlanesAlimentosEditarAdapter adapter =new ListaPlanesAlimentosEditarAdapter(dbPlanAlimento.mostrarIdAlimento(id));
        ListaPlanesAlimentos2.setAdapter(adapter);
    }
    private void verRegistro(){
        Intent intent = new Intent(this,VerPlanesNutricionales.class);
        intent.putExtra("IdPlanesNutricionales",id);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finish();
            ((MainActivityNutriologo)getApplicationContext()).finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
package com.anfereba.nutricionabc.FragmentosNutriologo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesAlimentosAdapter;
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
import java.util.List;

public class VerPlanesNutricionales extends AppCompatActivity {

    EditText NombrePlanNutricional;
    PlanesNutricionales planesNutricionales;
    FloatingActionButton fabEditarPlan,fabEliminarPlan,modificarPlanNutricional;
    Spinner spinnerAlimentos;
    ImageView btn_Atras;
    FloatingActionButton agregarAlimentos;
    RecyclerView ListaPlanesAlimentos;
    DbAlimento alimentos;
    List<Alimentos> listaAlimentos;
    ArrayAdapter<Alimentos> arrayAdapter;
    DbPlanAlimento dbPlanAlimento;
    int id=0;
    int dia=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_planes_nutricionales);
        ListaPlanesAlimentos=(RecyclerView)findViewById(R.id.ListaPlanesAlimentos);
        ListaPlanesAlimentos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dbPlanAlimento=new DbPlanAlimento(getApplicationContext());
        ArrayList<PlanesAlimentos> listaArrayPlanesAlimentos= new ArrayList<>();

        NombrePlanNutricional=(EditText) findViewById(R.id.ModificarNombrePlan);
        modificarPlanNutricional=(FloatingActionButton) findViewById(R.id.ModificarPlan);
        fabEditarPlan= (FloatingActionButton) findViewById(R.id.floatingEditarPlan);
        fabEliminarPlan= (FloatingActionButton)findViewById(R.id.floatingEliminarPlan);
        spinnerAlimentos=(Spinner)findViewById(R.id.spinnerAlimentos);
        alimentos =new DbAlimento(VerPlanesNutricionales.this);

        listaAlimentos = alimentos.mostrarAlimentos();
        agregarAlimentos=(FloatingActionButton)findViewById(R.id.agregarAlimentos);
        arrayAdapter =new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,listaAlimentos);
        spinnerAlimentos.setAdapter(arrayAdapter);
        btn_Atras = findViewById(R.id.btn_Atras);
        btn_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fabEditarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPlanesNutricionales.this,EditarPlanesNutricionales.class);
                intent.putExtra("IdPlanesNutricionales",id);
                startActivity(intent);
                finish();
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
                            dbPlanAlimento.EliminarPlanAlimento(id);
                            lista();
                            finish();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
        ListaPlanesAlimentosAdapter adapter = new ListaPlanesAlimentosAdapter(dbPlanAlimento.mostrarIdAlimento(id));
        ListaPlanesAlimentos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinnerAlimentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idAlimentos = ((Alimentos)adapterView.getSelectedItem()).getIdAlimento();
                DbPlanAlimento dbPlanAlimento=new DbPlanAlimento(VerPlanesNutricionales.this);
                agregarAlimentos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbPlanAlimento.insertarPlanAlimento(id,idAlimentos,dia);
                        dia = dia +1;
                        ListaPlanesAlimentosAdapter adapter = new ListaPlanesAlimentosAdapter(dbPlanAlimento.mostrarIdAlimento(id));
                        ListaPlanesAlimentos.setAdapter(adapter);
                        //finish();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void lista(){
        /*Intent intent = new Intent(this, MainActivityNutriologo.class);
        startActivity(intent);*/
        finish();
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
package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanesAlimentosAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.DbPlanAlimento;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EditarPlanesAlimentos extends AppCompatActivity {
int id =0;
int id2=0;
Spinner spinnerAlimentos;
FloatingActionButton agregarAlimentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_planes_alimentos);
        spinnerAlimentos = findViewById(R.id.spinnerEditarPlanesAlimentos);
        agregarAlimentos= (FloatingActionButton)findViewById(R.id.EditarPlanesAlimentos);
        DbAlimento alimentos =new DbAlimento(EditarPlanesAlimentos.this);
        List<Alimentos> listaAlimentos = alimentos.mostrarAlimentos();
        ArrayAdapter<Alimentos> arrayAdapter =new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,listaAlimentos);
        spinnerAlimentos.setAdapter(arrayAdapter);

        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
                id2 = Integer.parseInt(null);
            }else{
                id = extras.getInt("IdPlanAlimento");
                id2 = extras.getInt("IdPlanNutricional");
            }
        }else{
            id=(int)savedInstanceState.getSerializable("IdPlanAlimento");
            id2=(int)savedInstanceState.getSerializable("IdPlanNutricional");
        }
        spinnerAlimentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idAlimentos = ((Alimentos)adapterView.getSelectedItem()).getIdAlimento();
                DbPlanAlimento dbPlanAlimento=new DbPlanAlimento(EditarPlanesAlimentos.this);
                agregarAlimentos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbPlanAlimento.EditarPlanesAlimentos(id,idAlimentos);
                        Intent intent = new Intent(EditarPlanesAlimentos.this,EditarPlanesNutricionales.class);
                        intent.putExtra("IdPlanesNutricionales",id2);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
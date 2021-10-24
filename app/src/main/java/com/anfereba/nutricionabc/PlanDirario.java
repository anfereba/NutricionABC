package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaHijosSinPlanAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanDiarioAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.DbPlanDiario;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlanDirario extends AppCompatActivity {
int IdHijo =0;
int IdPlanNutricional =0;
TextView PlanDiario;
RecyclerView AlimentosDiarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_dirario);
        PlanDiario = findViewById(R.id.PlanDiario);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                IdHijo = Integer.parseInt(null);
                IdPlanNutricional = Integer.parseInt(null);
            } else {
                IdHijo = extras.getInt("IdHijo");
                IdPlanNutricional = extras.getInt("IdPlanNutricional");
            }
        } else {
            IdHijo = (int) savedInstanceState.getSerializable("IdHijo");
            IdPlanNutricional = (int) savedInstanceState.getSerializable("IdPlanNutricional");
        }
        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(PlanDirario.this);
        PlanesNutricionales planesNutricionales= dbPlanNutricional.verPlan(IdPlanNutricional);
        PlanDiario.setText(planesNutricionales.getNombrePlan());


        /*DbHijo dbHijo = new DbHijo(this);
        AlimentosDiarios = (RecyclerView) findViewById(R.id.AlimentosDiarios);
        AlimentosDiarios.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Hijos>listaArrayHijosSinPlan=new ArrayList<>();
        Integer iDUsuario=0;
        ListaHijosSinPlanAdapter adapter =new ListaHijosSinPlanAdapter(dbHijo.mostrarTodosLosHijosQueNoTienenPlan(iDUsuario));
        AlimentosDiarios.setAdapter(adapter);*/

       DbPlanDiario dbPlanDiario = new DbPlanDiario(PlanDirario.this);
        AlimentosDiarios = (RecyclerView) findViewById(R.id.AlimentosDiarios);
        AlimentosDiarios.setLayoutManager(new LinearLayoutManager(PlanDirario.this));
        ArrayList<PlanesDiarios> listaPlanDiario = new ArrayList<>();
        ListaPlanDiarioAdapter adapter = new ListaPlanDiarioAdapter(dbPlanDiario.mostrarCumplimientoDelPlanDiario(IdHijo,IdPlanNutricional));
        AlimentosDiarios.setAdapter(adapter);

    }
}
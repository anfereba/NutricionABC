package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanDiarioAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.DbPlanDiario;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

public class PlanDirario extends AppCompatActivity {
int IdHijo =0;
int IdPlanNutricional =0;
TextView PlanDiario;
RecyclerView AlimentosDiarios;
EditText ComentarioNutriologo;
Button GuardarComentarioDiario, VistoBueno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_dirario);
        PlanDiario = findViewById(R.id.PlanDiario);
        ComentarioNutriologo = findViewById(R.id.ComentarioNutriologo);
        GuardarComentarioDiario = findViewById(R.id.GuardarComentarioDiario);
        VistoBueno = findViewById(R.id.VistoBueno);
        VistoBueno.setVisibility(View.INVISIBLE);

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
        ComentarioNutriologo.setText(dbPlanNutricional.verComentarioNutriologo(IdHijo,IdPlanNutricional));
        GuardarComentarioDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbPlanNutricional.EditarComentarioNutriologo(IdHijo,IdPlanNutricional,(ComentarioNutriologo.getText()).toString());
                Toast.makeText(PlanDirario.this,"Comentario Guardado",Toast.LENGTH_LONG).show();
            }
        });



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


        DbUsuario dbUsuario = new DbUsuario(PlanDirario.this);
        String U = dbUsuario.consultarDato("idPerfilSistema","idUsuario",TomarIdUsuario().toString());
        int u = Integer.parseInt (U);
        System.out.println(u);
        if(u==1){
            ComentarioNutriologo.setInputType(InputType.TYPE_NULL);
            GuardarComentarioDiario.setVisibility(View.INVISIBLE);
            GuardarComentarioDiario.setInputType(InputType.TYPE_NULL);
            VistoBueno.setVisibility(View.INVISIBLE);
            VistoBueno.setInputType(InputType.TYPE_NULL);
        }
        if(dbPlanNutricional.verPorcentagePlan(IdHijo,IdPlanNutricional)==100&&u==2){
            VistoBueno.setVisibility(View.VISIBLE);
            VistoBueno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbPlanNutricional.EditarVistoBueno(IdHijo,IdPlanNutricional,true);
                    Toast.makeText(PlanDirario.this,"has dado el visto bueno",Toast.LENGTH_LONG).show();
                    DbHijo dbHijo = new DbHijo(PlanDirario.this);
                    dbHijo.AsignarPlanNutricionalAlHijo(IdHijo,0);
                }
            });
        }

    }
    private Integer TomarIdUsuario() {
        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);
        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
        return iDNutriologo;
    }
}
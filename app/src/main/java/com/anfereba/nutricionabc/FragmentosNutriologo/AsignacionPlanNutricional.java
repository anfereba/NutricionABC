package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbPlanDiario;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AsignacionPlanNutricional extends AppCompatActivity {
TextView NombreSeleccionado, EdadSeleccionado, EstaturaSeleccionado,PesoSeleccionado;
CircleImageView ImagenSeleccionado;
Spinner spinnerPlanSeleccionado;
Button SeleccionarPlan;
    int id =0;
    Hijos hijos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignacion_plan_nutricional);
        NombreSeleccionado = findViewById(R.id.NombreSeleccionado);
        EdadSeleccionado = findViewById(R.id.EdadSeleccionado);
        EstaturaSeleccionado = findViewById(R.id.EstaturaSeleccionado);
        PesoSeleccionado = findViewById(R.id.PesoSeleccionado);
        ImagenSeleccionado = findViewById(R.id.ImagenSeleccionado);
        spinnerPlanSeleccionado = findViewById(R.id.spinnerPlanSeleccionado);
        SeleccionarPlan = findViewById(R.id.SeleccionarPlan);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("IdHijo");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("IdHijo");
        }
        DbHijo dbHijo = new DbHijo(AsignacionPlanNutricional.this);
        hijos = dbHijo.verHijos(id);
        if (hijos != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hijos.getFotoHijos(), 0, hijos.getFotoHijos().length);
            NombreSeleccionado.setText(hijos.getNombreHijos());
            EdadSeleccionado.setText((hijos.getEdadHijos()).toString()+" a??os");
            EstaturaSeleccionado.setText((hijos.getEstaturaHijos()).toString()+" m");
            PesoSeleccionado.setText((hijos.getPesoHijos()).toString()+" kilos");
            ImagenSeleccionado.setImageBitmap(bitmap);
        }
        DbPlanDiario dbPlanDiario= new DbPlanDiario(AsignacionPlanNutricional.this);
        DbPlanNutricional dbPlanNutricional =new DbPlanNutricional(AsignacionPlanNutricional.this);
        List<PlanesNutricionales> planesNutricionales = dbPlanNutricional.mostrarPlan(TomarIdNutriologo());
        ArrayAdapter<PlanesNutricionales> arrayAdapter =new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,planesNutricionales);
        spinnerPlanSeleccionado.setAdapter(arrayAdapter);
        spinnerPlanSeleccionado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idPlanNutricional = ((PlanesNutricionales)adapterView.getSelectedItem()).getIdPlanNutricional();
                SeleccionarPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context =  view.getContext();
                        if(dbPlanNutricional.Comprobar_ExistenciaHistorialPlan(id,idPlanNutricional)){
                            Toast.makeText(context,"El Paciente ya habia intentado este plan Nutricional Antes",Toast.LENGTH_LONG).show();
                            Toast.makeText(context,"Seleccione otro por favor",Toast.LENGTH_LONG).show();
                        }else {
                            if(dbPlanNutricional.PlanContieneAlimentos(idPlanNutricional)){
                                dbPlanNutricional.insertarHistorialPlanNutricional(id,idPlanNutricional);
                                dbHijo.AsignarPlanNutricionalAlHijo(id,idPlanNutricional);
                                finish();
                                dbPlanDiario.insertarPlanDiario(id,idPlanNutricional);
                            }else{
                                Toast.makeText(context, "Debe registrar al menos un alimento en este plan antes de asignarlo a un paciente", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private Integer TomarIdNutriologo() {
        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);
        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
        return iDNutriologo;
    }

    public void IrAtras(View view) {
        onBackPressed();
    }
}
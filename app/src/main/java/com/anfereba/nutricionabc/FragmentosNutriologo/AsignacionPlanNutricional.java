package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anfereba.nutricionabc.FragmentosCliente.VerHijo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.Entidades.Hijos;

import de.hdodenhof.circleimageview.CircleImageView;

public class AsignacionPlanNutricional extends AppCompatActivity {
TextView NombreSeleccionado, EdadSeleccionado, EstaturaSeleccionado,PesoSeleccionado;
ImageView ImagenSeleccionado;
Spinner spinnerPlanSeleccionado;
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
            EdadSeleccionado.setText((hijos.getEdadHijos()).toString()+" años");
            EstaturaSeleccionado.setText((hijos.getEstaturaHijos()).toString()+" m");
            PesoSeleccionado.setText((hijos.getPesoHijos()).toString()+" kilos");
            ImagenSeleccionado.setImageBitmap(bitmap);
        }
    }
}
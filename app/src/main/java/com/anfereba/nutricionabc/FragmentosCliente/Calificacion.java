package com.anfereba.nutricionabc.FragmentosCliente;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbCalificacion;
import com.anfereba.nutricionabc.db.Entidades.CalificacionNutriologo;

import java.util.ArrayList;
import java.util.List;

public class Calificacion extends AppCompatActivity {

    Spinner SpinnerCalif;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.fragment_opcion_uno_cliente);
        SpinnerCalif = findViewById(R.id.SpinnerCalif);

        List<CalificacionNutriologo> listaCalificacion = llenarCalificacion();
        //enviamos el adapatador que se va adecuar a lo que traiga
        ArrayAdapter<CalificacionNutriologo> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listaCalificacion);
        SpinnerCalif.setAdapter(arrayAdapter);
    }
//llamar la consulta

    private List<CalificacionNutriologo> llenarCalificacion(){
    List<CalificacionNutriologo> listCali = new ArrayList<>();
    //consulta al modelo = el contexto nombre de la clase
        DbCalificacion dbCalificacion = new DbCalificacion(Calificacion.this);
        Cursor cursor = dbCalificacion.mostrarCalificacion();
        if(cursor != null){
            if (cursor.moveToFirst()){
                do {
                    CalificacionNutriologo cali= new CalificacionNutriologo();
                    cali.setIdPerfilSistema(cursor.getColumnIndex("idPerfilSistema"));
                    cali.setIdCalificacion(cursor.getColumnIndex("idCalificacion"));
                    cali.setIdUsuario(cursor.getColumnIndex("idUsuario"));
                    cali.setRating(cursor.getColumnIndex("rating"));
                    listCali.add(cali);
                }while(cursor.moveToNext());
            }
        }
      dbCalificacion.close();
        return listCali;
    }
}

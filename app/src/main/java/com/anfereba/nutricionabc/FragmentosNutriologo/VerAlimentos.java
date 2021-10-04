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
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerAlimentos extends AppCompatActivity {
    EditText NombreAlimento;
    Button GuardarAlimento;
    Alimentos alimentos;
    FloatingActionButton fabEditarAlimentos,fabEliminarAlimentos;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);
        NombreAlimento=(EditText) findViewById(R.id.ModificarNombreAlimento);
        GuardarAlimento=(Button) findViewById(R.id.ModificarAlimento);
        fabEditarAlimentos = (FloatingActionButton) findViewById(R.id.fabEditarAlimento);
        fabEliminarAlimentos=(FloatingActionButton) findViewById(R.id.fabEliminarAlimento);
        fabEditarAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerAlimentos.this,EditarAlimentos.class);
                intent.putExtra("IdAlimentos",id);
                startActivity(intent);
            }
        });
        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id=Integer.parseInt(null);
            }else {
                id= extras.getInt("IdAlimentos");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("IdAlimentos");
        }
        DbAlimento dbAlimento = new DbAlimento (VerAlimentos.this);
        alimentos = dbAlimento.verAlimentos(id);
        if(alimentos!=null){
            NombreAlimento.setText(alimentos.getNombreAlimento());
            GuardarAlimento.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombreAlimento.setInputType(InputType.TYPE_NULL);//le quito el teclado a los editext
        }
        fabEliminarAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerAlimentos.this);
                builder.setMessage("¿Desea eliminar este Alimento").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(dbAlimento.EliminarAlimento(id)){
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
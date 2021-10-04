package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarAlimentos extends AppCompatActivity {
EditText NombreAlimento;
Button GuardarAlimento;
Alimentos alimentos;
FloatingActionButton fabEditarAlimento,fabEliminarAlimento;
boolean correcto =false;
int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);
        NombreAlimento=(EditText) findViewById(R.id.ModificarNombreAlimento);
        GuardarAlimento=(Button) findViewById(R.id.ModificarAlimento);
        fabEditarAlimento= (FloatingActionButton) findViewById(R.id.fabEditarAlimento);
        fabEliminarAlimento= (FloatingActionButton) findViewById(R.id.fabEliminarAlimento);
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
        DbAlimento dbAlimento = new DbAlimento (EditarAlimentos.this);
        alimentos = dbAlimento.verAlimentos(id);
        if(alimentos!=null){
            NombreAlimento.setText(alimentos.getNombreAlimento());
            /*GuardarAlimento.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombreAlimento.setInputType(InputType.TYPE_NULL);//le quito el teclado a los editext*/
            fabEditarAlimento.setVisibility(View.INVISIBLE);
            fabEliminarAlimento.setVisibility(View.INVISIBLE);
            GuardarAlimento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!NombreAlimento.getText().toString().equals("")){
                        correcto=dbAlimento.EditarAlimento(id,NombreAlimento.getText().toString());
                        if(correcto){
                            Toast.makeText(EditarAlimentos.this,"Alimento Modificado",Toast.LENGTH_LONG).show();
                        verRegistro();
                        }else{
                            Toast.makeText(EditarAlimentos.this,"El Alimento no ha sido Modificado",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(EditarAlimentos.this,"Debe llenar los campos obligatorios",Toast.LENGTH_LONG);
                    }
                }
            });
        }
    }
    private void verRegistro(){
        Intent intent = new Intent(this,VerAlimentos.class);
        intent.putExtra("IdAlimentos",id);
        startActivity(intent);
    }
}
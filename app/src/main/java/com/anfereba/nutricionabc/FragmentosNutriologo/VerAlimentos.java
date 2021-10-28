package com.anfereba.nutricionabc.FragmentosNutriologo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.anfereba.nutricionabc.MainActivityNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Blob;
import java.util.ArrayList;

public class VerAlimentos extends AppCompatActivity {
    TextView ImgAtras;
    EditText NombreAlimento, CaloriasAlimento;
    ImageView EditarFotoAlimento;
    Button GuardarAlimento;
    Alimentos alimentos;
    FloatingActionButton fabEditarAlimentos, fabEliminarAlimentos;
    int id = 0;
    int id2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);
        NombreAlimento = (EditText) findViewById(R.id.ModificarNombreAlimento);
        CaloriasAlimento = (EditText) findViewById(R.id.EditarCalorias);
        EditarFotoAlimento = (ImageView) findViewById(R.id.EditarimagenAlimento);
        GuardarAlimento = (Button) findViewById(R.id.ModificarAlimento);
        fabEditarAlimentos = (FloatingActionButton) findViewById(R.id.fabEditarAlimento);
        fabEliminarAlimentos = (FloatingActionButton) findViewById(R.id.fabEliminarAlimento);
        ImgAtras = findViewById(R.id.ImgAtras);
        fabEditarAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerAlimentos.this, EditarAlimentos.class);
                intent.putExtra("IdAlimentos", id);
                startActivity(intent);
                finish();
            }
        });
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("IdAlimentos");
                id2 = extras.getInt("IdPlanAlimento");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("IdAlimentos");
            id2 = (int) savedInstanceState.getSerializable("IdPlanAlimento");
        }

        DbAlimento dbAlimento = new DbAlimento(VerAlimentos.this);
        alimentos = dbAlimento.verAlimentos(id);
        if (alimentos != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(alimentos.getFotoAlimento(), 0, alimentos.getFotoAlimento().length);
            NombreAlimento.setText(alimentos.getNombreAlimento());
            CaloriasAlimento.setText((alimentos.getCalorias()).toString());
            GuardarAlimento.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombreAlimento.setInputType(InputType.TYPE_NULL);//le quito el teclado a los editext
            CaloriasAlimento.setInputType(InputType.TYPE_NULL);//le quito el teclado a los editext
            EditarFotoAlimento.setImageBitmap(bitmap);
            if (id2 != 0) {
                fabEditarAlimentos.setVisibility(View.INVISIBLE);
                fabEliminarAlimentos.setVisibility(View.INVISIBLE);
            }
        }
        fabEliminarAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerAlimentos.this);
                builder.setMessage("¿Desea eliminar este Alimento").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (dbAlimento.EliminarAlimento(id)) {
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
        ImgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void lista() {
        /*Intent intent = new Intent(this, MainActivityNutriologo.class);
        startActivity(intent);*/
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && id2 == 0) {
            finishAffinity ();//se utiliza para terminará la actividad actual y todas las actividades de los padres
            //((MainActivityNutriologo) getApplicationContext()).finish();

        }
        return super.onKeyDown(keyCode, event);
    }

}
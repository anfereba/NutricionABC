package com.anfereba.nutricionabc.FragmentosCliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.anfereba.nutricionabc.FragmentosNutriologo.EditarAlimentos;
import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.MainActivityCliente;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerHijo extends AppCompatActivity {
    TextView NombreDelHijo;
    ImageView IMGAtras;
    CircleImageView EditarImagenHijo;
    EditText EditarNombreHijo, EditarEstaturaHijo, EditarEdadHijo, EditarPesoHijo;
    TextInputLayout editarNombreHijo;
    FloatingActionButton EditarHijo,EliminarHijo,GuardarEdiHijo;
    int id =0;
    Hijos hijos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_hijo);
        editarNombreHijo= (TextInputLayout) findViewById(R.id.editarNombreHijo);
        NombreDelHijo = (TextView) findViewById(R.id.NombreDelHijo);
        EditarImagenHijo = findViewById(R.id.EditarImagenHijo);
        EditarNombreHijo = (EditText) findViewById(R.id.EditarNombreHijo);
        EditarEstaturaHijo = (EditText)findViewById(R.id.EditarEstaturaHijo);
        EditarEdadHijo=(EditText) findViewById(R.id.EditarEdadHijo);
        EditarPesoHijo=(EditText) findViewById(R.id.EditarPesoHijo);
        EditarHijo=(FloatingActionButton) findViewById(R.id.EditarHijo);
        EliminarHijo=(FloatingActionButton) findViewById(R.id.EliminarHijo);
        GuardarEdiHijo=(FloatingActionButton) findViewById(R.id.GuardarEdiHijo);
        IMGAtras = findViewById(R.id.IMGAtras);
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
        DbHijo dbHijo = new DbHijo(VerHijo.this);
        hijos = dbHijo.verHijos(id);

        if (hijos != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hijos.getFotoHijos(), 0, hijos.getFotoHijos().length);
            NombreDelHijo.setText(hijos.getNombreHijos());
            EditarEstaturaHijo.setText(hijos.getEstaturaHijos());
            EditarEdadHijo.setText((hijos.getEdadHijos()).toString());
            EditarPesoHijo.setText((hijos.getPesoHijos()).toString());
            EditarNombreHijo.setVisibility(View.INVISIBLE);
            editarNombreHijo.setVisibility(View.INVISIBLE);
            GuardarEdiHijo.setVisibility(View.INVISIBLE);
            EditarEstaturaHijo.setInputType(InputType.TYPE_NULL);
            EditarEdadHijo.setInputType(InputType.TYPE_NULL);
            EditarPesoHijo.setInputType(InputType.TYPE_NULL);
            EditarImagenHijo.setImageBitmap(bitmap);
        }
        EditarHijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerHijo.this, EditarHijo.class);
                intent.putExtra("IdHijo", id);
                startActivity(intent);
                finish();
            }
        });
        EliminarHijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerHijo.this);
                builder.setMessage("¿Desea eliminar este Hijo").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dbHijo.EliminarHijo(id)) {
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
        IMGAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
        if (keyCode==event.KEYCODE_BACK){
            finishAffinity ();//se utiliza para terminará la actividad actual y todas las actividades de los padres
            //((MainActivityCliente)getApplicationContext()).finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

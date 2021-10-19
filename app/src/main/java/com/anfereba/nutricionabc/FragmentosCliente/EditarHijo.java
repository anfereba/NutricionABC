package com.anfereba.nutricionabc.FragmentosCliente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosNutriologo.VerAlimentos;
import com.anfereba.nutricionabc.MainActivityCliente;
import com.anfereba.nutricionabc.MainActivityNutriologo;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.Entidades.Hijos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EditarHijo extends AppCompatActivity implements Validator.ValidationListener{
    TextView NombreDelHijo;
    ImageView EditarImagenHijo;
    FloatingActionButton EditarHijo,EliminarHijo,GuardarEdiHijo;
    int id =0;
    Hijos hijos;
    boolean correcto =false;
    Uri RutaArchivoUri;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDeLHijo;
    int CODIGO_DE_SOLICITUD_IMAGEN = 5;
    private Validator validator;
    private boolean DatosValidados;
    @NotEmpty(message = "Este campo es Obligatorio")
    EditText EditarNombreHijo;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText EditarEstaturaHijo;
    @NotEmpty(message = "Este campo es Obligatorio")
    EditText EditarEdadHijo;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText EditarPesoHijo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_hijo);
        NombreDelHijo = (TextView) findViewById(R.id.NombreDelHijo);
        EditarImagenHijo = (ImageView) findViewById(R.id.EditarImagenHijo);
        EditarNombreHijo = (EditText) findViewById(R.id.EditarNombreHijo);
        EditarEstaturaHijo = (EditText)findViewById(R.id.EditarEstaturaHijo);
        EditarEstaturaHijo.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        EditarEdadHijo=(EditText) findViewById(R.id.EditarEdadHijo);
        EditarEdadHijo.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditarPesoHijo=(EditText) findViewById(R.id.EditarPesoHijo);
        EditarPesoHijo.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditarHijo=(FloatingActionButton) findViewById(R.id.EditarHijo);
        EliminarHijo=(FloatingActionButton) findViewById(R.id.EliminarHijo);
        GuardarEdiHijo=(FloatingActionButton) findViewById(R.id.GuardarEdiHijo);
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
        DbHijo dbHijo = new DbHijo(EditarHijo.this);
        hijos = dbHijo.verHijos(id);

        if (hijos != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hijos.getFotoHijos(), 0, hijos.getFotoHijos().length);
            EditarNombreHijo.setText(hijos.getNombreHijos());
            EditarEstaturaHijo.setText(hijos.getEstaturaHijos());
            EditarEdadHijo.setText((hijos.getEdadHijos()).toString());
            EditarPesoHijo.setText((hijos.getPesoHijos()).toString());
            NombreDelHijo.setText("Editar Hijo");
            EditarImagenHijo.setImageBitmap(bitmap);
            EditarHijo.setVisibility(View.INVISIBLE);
            EliminarHijo.setVisibility(View.INVISIBLE);
        }
        validator = new Validator(this);
        validator.setValidationListener(this);
        GuardarEdiHijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               validator.validate();
                if(DatosValidados && EditarImagenHijo.getDrawable()!=null && FotoHijoEnBytes()!=null) {


                    int numEdadHijo = Integer.parseInt(String.valueOf(EditarEdadHijo.getText()));
                    int numPesoHijo = Integer.parseInt(String.valueOf(EditarPesoHijo.getText()));
                    long QueryExitosa = dbHijo.EditarHijo(id,EditarNombreHijo.getText().toString(),
                            EditarEstaturaHijo.getText().toString(), numEdadHijo, numPesoHijo, FotoHijoEnBytes());

                    if (QueryExitosa > 0 ) {
                        verRegistro();
                        finish();
                        Toast.makeText(getApplicationContext(), "Hijo guardado con éxito: ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR: ", Toast.LENGTH_LONG).show();
                    }

                }else if (DatosValidados && ( FotoHijoEnBytes()==null)){

                    //Validacion manual de la foto de perfil
                    int numEdadHijo = Integer.parseInt(String.valueOf(EditarEdadHijo.getText()));
                    int numPesoHijo = Integer.parseInt(String.valueOf(EditarPesoHijo.getText()));
                    long QueryExitosa = dbHijo.EditarHijo(id,EditarNombreHijo.getText().toString(),
                            EditarEstaturaHijo.getText().toString(), numEdadHijo, numPesoHijo, hijos.getFotoHijos());
                    if (QueryExitosa > 0 ) {
                        verRegistro();
                        Toast.makeText(getApplicationContext(), "Hijo guardado con éxito: ", Toast.LENGTH_LONG).show();

                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR: ", Toast.LENGTH_LONG).show();
                    }

                    //Toast.makeText(getApplicationContext(), "Debe Escoger una Foto para el alimento", Toast.LENGTH_LONG).show();

                }
            }
        });
    EditarImagenHijo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Seleccione imagen del Hijo"),CODIGO_DE_SOLICITUD_IMAGEN);

        }
    });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, VerHijo.class);
        intent.putExtra("IdHijo",id);
        startActivity(intent);
    }



    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_DE_SOLICITUD_IMAGEN
                && resultCode == RESULT_OK
                && data !=null
                && data.getData() != null){

            RutaArchivoUri = data.getData();
            try {
                FotoDeLHijo = MediaStore.Images.Media.getBitmap(getContentResolver(),RutaArchivoUri);
                EditarImagenHijo.setImageBitmap(FotoDeLHijo);
            }catch (Exception e){
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private byte[] FotoHijoEnBytes(){
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)EditarImagenHijo.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            imagenEnBytes = byteArrayOutputStream.toByteArray();
        }else{
            imagenEnBytes = null;
        }
        return imagenEnBytes;
    }


    @Override
    public void onValidationSucceeded() {
        DatosValidados = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        DatosValidados = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finish();
            //((MainActivityCliente)getApplicationContext()).finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
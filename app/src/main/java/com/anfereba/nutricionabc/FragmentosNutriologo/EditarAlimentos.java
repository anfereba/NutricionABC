package com.anfereba.nutricionabc.FragmentosNutriologo;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Alimentos;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EditarAlimentos extends AppCompatActivity implements Validator.ValidationListener {

    ImageView AgregarFotoAlimento;
    Button GuardarAlimento;
    Alimentos alimentos;
    FloatingActionButton fabEditarAlimento,fabEliminarAlimento;
    boolean correcto =false;
    Uri RutaArchivoUri;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDeLAlimento;
    int CODIGO_DE_SOLICITUD_IMAGEN = 5;
    private Validator validator;
    private boolean DatosValidados;
    //Validacion de cada campo

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText NombreDelAlimento;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText CaloriasAlimento;

    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);
        NombreDelAlimento=(EditText) findViewById(R.id.ModificarNombreAlimento);
        CaloriasAlimento=(EditText)findViewById(R.id.EditarCalorias);
        CaloriasAlimento.setInputType(InputType.TYPE_CLASS_NUMBER);
        AgregarFotoAlimento=(ImageView)findViewById(R.id.EditarimagenAlimento);
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
            Bitmap bitmap = BitmapFactory.decodeByteArray(alimentos.getFotoAlimento(), 0,alimentos.getFotoAlimento().length);
            AgregarFotoAlimento.setImageBitmap(bitmap);
            NombreDelAlimento.setText(alimentos.getNombreAlimento());
            CaloriasAlimento.setText((alimentos.getCalorias()).toString());
            /*GuardarAlimento.setVisibility(View.INVISIBLE);//pongo invisible el boton
            NombreAlimento.setInputType(InputType.TYPE_NULL);//le quito el teclado a los editext*/
        }
        fabEditarAlimento.setVisibility(View.INVISIBLE);
        fabEliminarAlimento.setVisibility(View.INVISIBLE);
        validator = new Validator(this);
        validator.setValidationListener(this);
        GuardarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
                if(DatosValidados && AgregarFotoAlimento.getDrawable()!=null && FotoAlimentoEnBytes()!=null) {
                    DbAlimento dbAlimento = new DbAlimento(getApplicationContext());
                    int numEntero = Integer.parseInt(String.valueOf(CaloriasAlimento.getText()));
                    long QueryExitosa = dbAlimento.EditarAlimento(id,NombreDelAlimento.getText().toString(),
                            numEntero,FotoAlimentoEnBytes());

                    if (QueryExitosa > 0 ) {
                        verRegistro();
                        Toast.makeText(getApplicationContext(), "Alimento guardado con éxito: ", Toast.LENGTH_LONG).show();
                        limpiar();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR El ALIMENTO: ", Toast.LENGTH_LONG).show();
                    }



                }else if (DatosValidados && ( FotoAlimentoEnBytes()==null)){

                    //Validacion manual de la foto de perfil
                    int numEntero = Integer.parseInt(String.valueOf(CaloriasAlimento.getText()));
                    long QueryExitosa = dbAlimento.EditarAlimento(id,NombreDelAlimento.getText().toString(),
                            numEntero,alimentos.getFotoAlimento());
                    if (QueryExitosa > 0 ) {
                        verRegistro();
                        Toast.makeText(getApplicationContext(), "Alimento guardado con éxito: ", Toast.LENGTH_LONG).show();
                        limpiar();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR El ALIMENTO: ", Toast.LENGTH_LONG).show();
                    }

                    //Toast.makeText(getApplicationContext(), "Debe Escoger una Foto para el alimento", Toast.LENGTH_LONG).show();

                }
            }
        });
AgregarFotoAlimento.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccione imagen del alimento"),CODIGO_DE_SOLICITUD_IMAGEN);

    }
});
    }
    private void verRegistro(){
        Intent intent = new Intent(this,VerAlimentos.class);
        intent.putExtra("IdAlimentos",id);
        startActivity(intent);
        finish();
    }

    private void limpiar() {
        NombreDelAlimento.setText("");
        CaloriasAlimento.setText("");

    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_DE_SOLICITUD_IMAGEN
                && resultCode == RESULT_OK
                && data !=null
                && data.getData() != null){

            RutaArchivoUri = data.getData();
            try {
                FotoDeLAlimento = MediaStore.Images.Media.getBitmap(getContentResolver(),RutaArchivoUri);
                AgregarFotoAlimento.setImageBitmap(FotoDeLAlimento);
            }catch (Exception e){
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private byte[] FotoAlimentoEnBytes(){
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)AgregarFotoAlimento.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            imagenEnBytes = byteArrayOutputStream.toByteArray();
        }else{
            imagenEnBytes = null;
        }
        return imagenEnBytes;
    }
    //validacion exitosa
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
}

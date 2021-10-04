package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.anfereba.nutricionabc.Login_App;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RegistrarAlimento extends AppCompatActivity implements Validator.ValidationListener {
Button AgregarAlimento;
    ImageView AgregarFotoAlimento;
Uri RutaArchivoUri;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDeLAlimento;
    int CODIGO_DE_SOLICITUD_IMAGEN = 5;
    private Validator validator;
    private boolean DatosValidados;
    //Validacion de cada campo

    @NotEmpty
    EditText NombreDelAlimento;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText CaloriasAlimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alimento);
        NombreDelAlimento = (EditText) findViewById(R.id.NombreDelAlimento);
        AgregarAlimento= (Button) findViewById(R.id.GuardarAlimento);
        AgregarFotoAlimento=(ImageView)findViewById(R.id.ImagenAlimento);
        CaloriasAlimento=((EditText)findViewById(R.id.CaloriasAlimento));
        CaloriasAlimento.setInputType(InputType.TYPE_CLASS_NUMBER);
        validator = new Validator(this);
        validator.setValidationListener(this);

        AgregarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
                if(DatosValidados && AgregarFotoAlimento.getDrawable()!=null && FotoAlimentoEnBytes()!=null) {
                    DbAlimento dbAlimento = new DbAlimento(getApplicationContext());

                    //Si el correo no se encuentra en la BD, se hara el registro
                    int numEntero = Integer.parseInt(String.valueOf(CaloriasAlimento.getText()));



                        long QueryExitosa = dbAlimento.insertarAlimento(NombreDelAlimento.getText().toString(),
                                numEntero,FotoAlimentoEnBytes());

                        if (QueryExitosa > 0) {
                            Toast.makeText(getApplicationContext(), "Registro guardado con éxito: ", Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR REGISTRO: ", Toast.LENGTH_LONG).show();
                        }



                }else if (DatosValidados && (AgregarFotoAlimento.getDrawable()==null || FotoAlimentoEnBytes()==null)){

                    //Validacion manual de la foto de perfil

                    Toast.makeText(getApplicationContext(), "Debe Escoger una Foto para el alimento", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void limpiar() {
        NombreDelAlimento.setText("");
        CaloriasAlimento.setText("");

    }

    public void EscogerImagenDeGaleria(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccione imagen del alimento"),CODIGO_DE_SOLICITUD_IMAGEN);
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
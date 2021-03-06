package com.anfereba.nutricionabc.FragmentosCliente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbAlimento;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrarHijo extends AppCompatActivity implements Validator.ValidationListener {
    Button GuardarHijo;
    ImageView IMGAtras;
    CircleImageView AgregarFotoHijo;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDeLHijo;
    Uri RutaArchivoUri;
    int CODIGO_DE_SOLICITUD_IMAGEN = 1;
    private Validator validator;
    private boolean DatosValidados;
    @NotEmpty(message = "Este campo es Obligatorio")
    EditText NombreHijo;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText EstaturaHijo;
    @NotEmpty(message = "Este campo es Obligatorio")
    EditText EdadHijo;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText PesoHijo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_hijo);
        GuardarHijo = (Button) findViewById(R.id.GuardarHijo);
        AgregarFotoHijo= findViewById(R.id.AgregarFotoHijo);
        NombreHijo = (EditText) findViewById(R.id.NombreHijo);
        EstaturaHijo=(EditText) findViewById(R.id.EstaturaHijo);
        EdadHijo=(EditText) findViewById(R.id.EdadHijo);
        PesoHijo=(EditText) findViewById(R.id.PesoHijo);
        IMGAtras = findViewById(R.id.IMGAtras);
        EstaturaHijo.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        EdadHijo.setInputType(InputType.TYPE_CLASS_NUMBER);
        PesoHijo.setInputType(InputType.TYPE_CLASS_NUMBER);
        validator = new Validator(this);
        validator.setValidationListener(this);
        GuardarHijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
                if(DatosValidados && AgregarFotoHijo.getDrawable()!=null && FotoHijoEnBytes()!=null) {
                    DbHijo dbHijo = new DbHijo(getApplicationContext());

                    //Si el correo no se encuentra en la BD, se hara el registro



                    int numEdadHijo = Integer.parseInt(String.valueOf(EdadHijo.getText()));
                    int numPesoHijo = Integer.parseInt(String.valueOf(PesoHijo.getText()));



                    long QueryExitosa = dbHijo.insertarHijo(NombreHijo.getText().toString(),EstaturaHijo.getText().toString(),numEdadHijo,numPesoHijo
                            ,TomarIdCliente(),FotoHijoEnBytes());

                    if (QueryExitosa > 0) {
                        Toast.makeText(getApplicationContext(), "Registro guardado con ??xito: ", Toast.LENGTH_LONG).show();
                        RegistrarHijo.this.finish();
                        limpiar();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR REGISTRO: ", Toast.LENGTH_LONG).show();
                    }



                }else if (DatosValidados && (AgregarFotoHijo.getDrawable()==null || FotoHijoEnBytes()==null)){

                    //Validacion manual de la foto de perfil

                    Toast.makeText(getApplicationContext(), "Debe Escoger una Foto", Toast.LENGTH_LONG).show();

                }
            }
        });
        IMGAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private void limpiar() {
        NombreHijo.setText("");
        EstaturaHijo.setText("");
        EdadHijo.setText("");
        PesoHijo.setText("");

    }

    public void EscogerImagenDeGaleria(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccione imagen del Hijo"),CODIGO_DE_SOLICITUD_IMAGEN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_DE_SOLICITUD_IMAGEN
                && resultCode == RESULT_OK
                && data !=null
                && data.getData() != null){

            RutaArchivoUri = data.getData();
            CropImage.activity(RutaArchivoUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri RutaArchivoUri2 = result.getUri();
                try {
                    FotoDeLHijo = MediaStore.Images.Media.getBitmap(getContentResolver(),RutaArchivoUri2);
                    AgregarFotoHijo.setImageBitmap(FotoDeLHijo);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private byte[] FotoHijoEnBytes(){
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)AgregarFotoHijo.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            imagenEnBytes = byteArrayOutputStream.toByteArray();
        }else{
            imagenEnBytes = null;
        }
        return imagenEnBytes;
    }
    //validacion exitosa

    private Integer TomarIdCliente() {
        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);

        Integer iDCliente = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
        return iDCliente;

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
}
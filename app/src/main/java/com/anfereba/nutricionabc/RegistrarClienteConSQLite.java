package com.anfereba.nutricionabc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.anfereba.nutricionabc.db.DbCliente;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

public class RegistrarClienteConSQLite extends AppCompatActivity implements Validator.ValidationListener {
    Button BtnRegistrarClienteBD;
    DatePickerDialog picker;

    Uri RutaArchivoUri;

    ImageView AgregarFotoCliente;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDePefil;

    int CODIGO_DE_SOLICITUD_IMAGEN = 5;

    private Validator validator;
    private boolean DatosValidados;

    //Validacion de cada campo

    @NotEmpty
    @Email
    EditText TXTCorreoUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTNombreUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTApellidoUsuario;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTFechaNacimientoUsuario;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTFechaCreacionUsuario;

    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    EditText TXTPasswordUsuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        //Inicializacion de elementos

        BtnRegistrarClienteBD = findViewById(R.id.BtnRegistrarClienteBD);
        TXTNombreUsuario = (EditText) findViewById(R.id.TXTNombreUsuario);
        TXTApellidoUsuario = (EditText) findViewById(R.id.TXTApellidoUsuario);
        TXTFechaNacimientoUsuario=(EditText)findViewById(R.id.TXTFechaNacimientoUsuario);
        TXTCorreoUsuario = (EditText) findViewById(R.id.TXTCorreoUsuario);
        TXTPasswordUsuario = (EditText) findViewById(R.id.TXTPasswordUsuario);
        TXTFechaCreacionUsuario=(EditText)findViewById(R.id.TXTFechaCreacionUsuario);
        AgregarFotoCliente=(ImageView) findViewById(R.id.AgregarFotoCliente);

        CalcularFechaActual();

        //Inicializando el validador de campos

        validator = new Validator(this);
        validator.setValidationListener(this);


        BtnRegistrarClienteBD.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            validator.validate();

            if (DatosValidados && AgregarFotoCliente.getDrawable()!=null && FotoPerfilEnBytes()!=null){
                DbCliente dbCliente = new DbCliente(getApplicationContext());

                long QueryExitosa = dbCliente.insertarUsuario(TXTNombreUsuario.getText().toString(),
                        TXTApellidoUsuario.getText().toString(),TXTFechaNacimientoUsuario.getText().toString(),
                        TXTCorreoUsuario.getText().toString(),TXTPasswordUsuario.getText().toString(),
                        TXTFechaCreacionUsuario.getText().toString(),FotoPerfilEnBytes());

                if (QueryExitosa > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO: "+String.valueOf(QueryExitosa), Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR REGISTRO: ", Toast.LENGTH_LONG).show();
                }
            }else if (DatosValidados && (AgregarFotoCliente.getDrawable()==null || FotoPerfilEnBytes()==null)){

                //Validacion manual de la foto de perfil
                Toast.makeText(getApplicationContext(), "Debe Escoger una Foto de Perfil", Toast.LENGTH_LONG).show();

            }
        }
    });
    }

    //Limpia los campos

    private void limpiar() {
        TXTNombreUsuario.setText("");
        TXTApellidoUsuario.setText("");
        TXTFechaNacimientoUsuario.setText("");
        TXTCorreoUsuario.setText("");
        TXTPasswordUsuario.setText("");
    }

    //Si la validacion fue Exitosa

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_DE_SOLICITUD_IMAGEN
                && resultCode == RESULT_OK
                && data !=null
                && data.getData() != null){

            RutaArchivoUri = data.getData();
            try {
                FotoDePefil = MediaStore.Images.Media.getBitmap(getContentResolver(),RutaArchivoUri);
                AgregarFotoCliente.setImageBitmap(FotoDePefil);
            }catch (Exception e){
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

    //Abre la galeria para escoger imagen

    public void EscogerImagenDeGaleria(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccionar imagen"),CODIGO_DE_SOLICITUD_IMAGEN);
    }

    //Convierte la foto de perfil escogida en Bytes

    private byte[] FotoPerfilEnBytes(){
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)AgregarFotoCliente.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            imagenEnBytes = byteArrayOutputStream.toByteArray();
        }else{
            imagenEnBytes = null;
        }
        return imagenEnBytes;
    }

    //Si la validacion fue Exitosa
    @Override
    public void onValidationSucceeded() {
        DatosValidados = true;
    }

    //Si la validacion no fue Exitosa

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

    public void SeleccionarFechaDeNacimiento(View view) {

        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);

        //Date Picker Dialog
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                TXTFechaNacimientoUsuario.setText(year + "-"+month+"-"+day);
            }
        },año,mes,dia);
        picker.show();
    }


    public void CalcularFechaActual() {
        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);
        TXTFechaCreacionUsuario.setText(año + "-"+mes+"-"+dia);
    }
}
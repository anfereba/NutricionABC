package com.anfereba.nutricionabc.FragmentosAdministrador;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.anfereba.nutricionabc.Login_App;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InsertarUsuarioAdministrador extends AppCompatActivity implements Validator.ValidationListener {

    Button BtnRegistrarClienteBDA;
    DatePickerDialog picker;
    Spinner spinner;
    String[] idPerfil;

    Uri RutaArchivoUri;

    ImageView AgregarFotoClienteA;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDePefil;

    int CODIGO_DE_SOLICITUD_IMAGEN = 5;

    private Validator validator;
    private boolean DatosValidados;

    //Validacion de cada campo

    @NotEmpty
    @Email
    EditText TXTCorreoUsuarioA;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTNombreUsuarioA;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTApellidoUsuarioA;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTDireccionUsuarioA;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTCiudadUsuarioA;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTTelefonoUsuarioA;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTFechaNacimientoUsuarioA;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTFechaCreacionUsuarioA;

    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    EditText TXTPasswordUsuarioA;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTPreguntaUnoA;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTPreguntaDosA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuario_administrador);

        //Inicializacion de elementos

        BtnRegistrarClienteBDA = findViewById(R.id.BtnRegistrarClienteBDA);
        TXTNombreUsuarioA = (EditText) findViewById(R.id.TXTNombreUsuarioA);
        TXTApellidoUsuarioA = (EditText) findViewById(R.id.TXTApellidoUsuarioA);
        TXTFechaNacimientoUsuarioA=(EditText)findViewById(R.id.TXTFechaNacimientoUsuarioA);
        TXTCorreoUsuarioA = (EditText) findViewById(R.id.TXTCorreoUsuarioA);
        TXTPasswordUsuarioA = (EditText) findViewById(R.id.TXTPasswordUsuarioA);
        TXTFechaCreacionUsuarioA=(EditText)findViewById(R.id.TXTFechaCreacionUsuarioA);
        TXTDireccionUsuarioA = (EditText) findViewById(R.id.TXTDireccionUsuarioA);
        TXTCiudadUsuarioA = (EditText) findViewById(R.id.TXTCiudadUsuarioA);
        TXTTelefonoUsuarioA=(EditText)findViewById(R.id.TXTTelefonoUsuarioA);
        AgregarFotoClienteA=(ImageView) findViewById(R.id.AgregarFotoClienteA);
        spinner = (Spinner) findViewById(R.id.cmbPerfiles);
        TXTPreguntaUnoA=(EditText)findViewById(R.id.TXTPreguntaUnoA);
        TXTPreguntaDosA=(EditText) findViewById(R.id.TXTPreguntaDosA);

        List perfiles = new ArrayList();
        perfiles.add("1 Cliente");
        perfiles.add("2 Nutriologo");
        perfiles.add("3 Administrador");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_checked,perfiles);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idPerfil = (spinner.getSelectedItem().toString()).split(" ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        CalcularFechaActual();

        //Inicializando el validador de campos

        validator = new Validator(this);
        validator.setValidationListener(this);

        BtnRegistrarClienteBDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();

                //Si todos los campos ya estan validados

                if (DatosValidados && AgregarFotoClienteA.getDrawable()!=null && FotoPerfilEnBytes()!=null){
                    DbUsuario dbUsuario = new DbUsuario(getApplicationContext());

                    //Si el correo no se encuentra en la BD, se hara el registro

                    if (!dbUsuario.Comprobar_Correo(TXTCorreoUsuarioA.getText().toString())){

                        long QueryExitosa = dbUsuario.insertarUsuario(TXTNombreUsuarioA.getText().toString(),
                                TXTApellidoUsuarioA.getText().toString(),TXTFechaNacimientoUsuarioA.getText().toString(),
                                TXTCorreoUsuarioA.getText().toString(),TXTPasswordUsuarioA.getText().toString(),
                                TXTDireccionUsuarioA.getText().toString(),TXTCiudadUsuarioA.getText().toString(),
                                TXTTelefonoUsuarioA.getText().toString(),
                                TXTFechaCreacionUsuarioA.getText().toString(),FotoPerfilEnBytes(),Integer.parseInt(idPerfil[0]),
                                TXTPreguntaUnoA.getText().toString(), TXTPreguntaDosA.getText().toString());

                        if (QueryExitosa > 0) {
                            //startActivity(new Intent(getApplicationContext(), Login_App.class));
                            //finish();
                            Toast.makeText(getApplicationContext(), "Registro guardado con éxito: ", Toast.LENGTH_LONG).show();
                            limpiar();
                            InsertarUsuarioAdministrador.this.finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR REGISTRO: ", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Este email ya esta en uso. Prueba con otro.", Toast.LENGTH_LONG).show();
                    }

                }else if (DatosValidados && (AgregarFotoClienteA.getDrawable()==null || FotoPerfilEnBytes()==null)){

                    //Validacion manual de la foto de perfil

                    Toast.makeText(getApplicationContext(), "Debe Escoger una Foto de Perfil", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    //Limpia los campos

    private void limpiar() {
        TXTNombreUsuarioA.setText("");
        TXTApellidoUsuarioA.setText("");
        TXTFechaNacimientoUsuarioA.setText("");
        TXTCorreoUsuarioA.setText("");
        TXTPasswordUsuarioA.setText("");
        TXTDireccionUsuarioA.setText("");
        TXTCiudadUsuarioA.setText("");
        TXTTelefonoUsuarioA.setText("");
    }

    //Asigna la imagen escogida de la galeria al ImageView

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
                AgregarFotoClienteA.setImageBitmap(FotoDePefil);
            }catch (Exception e){
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Abre la galeria para escoger imagen

    public void EscogerImagenDeGaleriaA(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccionar imagen"),CODIGO_DE_SOLICITUD_IMAGEN);
    }

    //Convierte la foto de perfil escogida en Bytes

    private byte[] FotoPerfilEnBytes(){
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)AgregarFotoClienteA.getDrawable()).getBitmap();
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

    public void SeleccionarFechaDeNacimientoA(View view) {

        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);

        //Date Picker Dialog
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                TXTFechaNacimientoUsuarioA.setText(year + "-"+month+"-"+day);
            }
        },año,mes,dia);
        picker.show();
    }


    public void CalcularFechaActual() {
        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH)+1;
        int año = calendario.get(Calendar.YEAR);
        TXTFechaCreacionUsuarioA.setText(año + "-"+mes+"-"+dia);
    }
}
package com.anfereba.nutricionabc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosAdministrador.PerfilUsuario;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActualizarPerfil extends AppCompatActivity implements Validator.ValidationListener {


    ArrayList<Usuario> listaArrayUsuarios;
    DbUsuario db;
    int IdUsuario;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private byte[] imagenEnBytes = null;
    Bitmap FotoDePefil;
    
    Button BtnActualizarUsuarioBD, BtnEliminarUsuarioBD;
    CircleImageView ActualizarFotoUsuario;

    int CODIGO_DE_SOLICITUD_IMAGEN = 1;

    Uri RutaArchivoUri;

    @NotEmpty
    @Email
    EditText TXTActualizarCorreoUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarNombreUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarApellidoUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarDireccionUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarCiudadUsuario;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarTelefonoUsuario;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTActualizarFechaNacimientoUsuario;

    //Para validacion de Campos

    private boolean DatosValidados;
    private Validator validator;

    //Calendario

    DatePickerDialog picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_perfil);

        //Inicializamos los elementos del Dialog

        TXTActualizarNombreUsuario = (EditText) findViewById(R.id.TXTActualizarNombreUsuario);
        TXTActualizarApellidoUsuario = (EditText) findViewById(R.id.TXTActualizarApellidoUsuario);
        TXTActualizarFechaNacimientoUsuario = (EditText) findViewById(R.id.TXTActualizarFechaNacimientoUsuario);
        TXTActualizarCorreoUsuario = (EditText) findViewById(R.id.TXTActualizarCorreoUsuario);
        TXTActualizarDireccionUsuario = (EditText) findViewById(R.id.TXTActualizarDireccionUsuario);
        TXTActualizarCiudadUsuario = (EditText) findViewById(R.id.TXTActualizarCiudadUsuario);
        TXTActualizarTelefonoUsuario = (EditText) findViewById(R.id.TXTActualizarTelefonoUsuario);

        BtnActualizarUsuarioBD = (Button) findViewById(R.id.BtnActualizarUsuarioBD);
        BtnEliminarUsuarioBD = (Button) findViewById(R.id.BtnEliminarUsuarioBD);
        ActualizarFotoUsuario = (CircleImageView) findViewById(R.id.ActualizarFotoUsuario);
        BtnEliminarUsuarioBD.setVisibility(View.GONE);

        validator = new Validator(this);
        validator.setValidationListener((Validator.ValidationListener) this);
        
        SetearDatosEnOpcionActualizar();

        //Llamado al calendario tras hacer click en el campo de fecha
        TXTActualizarFechaNacimientoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarFechaDeNacimiento();
            }
        });

        BtnActualizarUsuarioBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarDatosUsuario();
            }
        });

    }
    private void SetearDatosEnOpcionActualizar() {

        IdUsuario = ObtenerIdUsuarioActual();
        db = new DbUsuario(this);
        listaArrayUsuarios = new ArrayList<>(db.ObtenerDatosUsuario(IdUsuario));


        TXTActualizarNombreUsuario.setText(listaArrayUsuarios.get(0).getNombres());
        TXTActualizarApellidoUsuario.setText(listaArrayUsuarios.get(0).getApellidos());
        TXTActualizarFechaNacimientoUsuario.setText(listaArrayUsuarios.get(0).getFechaNacimiento());
        TXTActualizarCorreoUsuario.setText(listaArrayUsuarios.get(0).getCorreo());
        TXTActualizarDireccionUsuario.setText(listaArrayUsuarios.get(0).getDireccion());
        TXTActualizarCiudadUsuario.setText(listaArrayUsuarios.get(0).getCiudad());
        TXTActualizarTelefonoUsuario.setText(listaArrayUsuarios.get(0).getTelefono());

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaArrayUsuarios.get(0).getFotoPerfil(),0,
                listaArrayUsuarios.get(0).getFotoPerfil().length);

        ActualizarFotoUsuario.setImageBitmap(bitmap);

    }

    private int ObtenerIdUsuarioActual() {

        preferences = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();
        int IdUsuario = preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        return IdUsuario;
    }

    private void ActualizarDatosUsuario() {

        validator.validate();

        //Variable Auxiliar para actualizar el email

        String AuxCorreoUsuario = listaArrayUsuarios.get(0).getCorreo();

        //Variable donde se almacena el id del usuario para ejecutar la sentencia UPDATE
        IdUsuario = listaArrayUsuarios.get(0).getIdUsuario();


        if (DatosValidados){

            DbUsuario db = new DbUsuario(this);

            //Si el usuario no desea cambiar su email actual

            if (AuxCorreoUsuario.equals(TXTActualizarCorreoUsuario.getText().toString())){

                // Se omite el metodo para la validacion de Correo


                if (FotoPerfilEnBytes() != null){

                    //Si el usuario cambio su foto de perfil

                    db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                            TXTActualizarApellidoUsuario.getText().toString(),
                            TXTActualizarFechaNacimientoUsuario.getText().toString(),
                            TXTActualizarCorreoUsuario.getText().toString(),
                            TXTActualizarDireccionUsuario.getText().toString(),
                            TXTActualizarCiudadUsuario.getText().toString(),
                            TXTActualizarTelefonoUsuario.getText().toString(),FotoPerfilEnBytes(),
                            IdUsuario);
                } else {

                    //Si el usuario no cambio su foto de perfil

                    db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                            TXTActualizarApellidoUsuario.getText().toString(),
                            TXTActualizarFechaNacimientoUsuario.getText().toString(),
                            TXTActualizarCorreoUsuario.getText().toString(),
                            TXTActualizarDireccionUsuario.getText().toString(),
                            TXTActualizarCiudadUsuario.getText().toString(),
                            TXTActualizarTelefonoUsuario.getText().toString(),
                            IdUsuario);

                }

            }else{

                //Se valida si el correo nuevo no se encuentra registrado en la BD

                if (!db.Comprobar_Correo(TXTActualizarCorreoUsuario.getText().toString())){

                    //Se ejecuta el UPDATE

                    if (FotoPerfilEnBytes() != null){

                        db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                                TXTActualizarApellidoUsuario.getText().toString(),
                                TXTActualizarFechaNacimientoUsuario.getText().toString(),
                                TXTActualizarCorreoUsuario.getText().toString(),
                                TXTActualizarDireccionUsuario.getText().toString(),
                                TXTActualizarCiudadUsuario.getText().toString(),
                                TXTActualizarTelefonoUsuario.getText().toString(),FotoPerfilEnBytes(),
                                IdUsuario);
                    }else{
                        db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                                TXTActualizarApellidoUsuario.getText().toString(),
                                TXTActualizarFechaNacimientoUsuario.getText().toString(),
                                TXTActualizarCorreoUsuario.getText().toString(),
                                TXTActualizarDireccionUsuario.getText().toString(),
                                TXTActualizarCiudadUsuario.getText().toString(),
                                TXTActualizarTelefonoUsuario.getText().toString(),
                                IdUsuario);

                    }

                }else{
                    Toast.makeText(this, "Este correo ya existe", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void ActualizarFechaDeNacimiento() {
        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);

        //Date Picker Dialog
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                TXTActualizarFechaNacimientoUsuario.setText(year + "-"+month+"-"+day);
            }
        },año,mes,dia);
        picker.show();
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
                    FotoDePefil = MediaStore.Images.Media.getBitmap(getContentResolver(),RutaArchivoUri2);
                    ActualizarFotoUsuario.setImageBitmap(FotoDePefil);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //Abre la galeria para escoger imagen

    public void EscogerImagenDeGaleria(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccione Imagen"),CODIGO_DE_SOLICITUD_IMAGEN);
    }

    //Convierte la foto de perfil escogida en Bytes

    private byte[] FotoPerfilEnBytes(){
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)ActualizarFotoUsuario.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            imagenEnBytes = byteArrayOutputStream.toByteArray();
        }else{
            imagenEnBytes = null;
        }
        return imagenEnBytes;
    }
}
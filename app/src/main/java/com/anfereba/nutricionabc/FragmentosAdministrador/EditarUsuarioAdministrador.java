package com.anfereba.nutricionabc.FragmentosAdministrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHelper;
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

public class EditarUsuarioAdministrador extends AppCompatActivity implements Validator.ValidationListener {


    CircleImageView ActualizarFotoCliente;

    int CODIGO_DE_SOLICITUD_IMAGEN = 1;

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

    Uri RutaArchivoUri;
    private byte[] imagenEnBytes = null;
    Bitmap FotoDePefil;

    private Context context;

    //Calendario

    DatePickerDialog picker;

    //Para almacenamiento de datos y busqueda de coincidencias

    private ArrayList<Usuario> listaUsuarios; //mData

    private Button BtnActualizarClienteBD;
    private Button BtnEliminarClienteBD;

    private int Id_Usuario;

    DbUsuario db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario_administrador);

        //Inicializamos los elementos del Dialog

        TXTActualizarNombreUsuario = (EditText) findViewById(R.id.TXTActualizarNombreUsuario);
        TXTActualizarApellidoUsuario = (EditText) findViewById(R.id.TXTActualizarApellidoUsuario);
        TXTActualizarFechaNacimientoUsuario = (EditText) findViewById(R.id.TXTActualizarFechaNacimientoUsuario);
        TXTActualizarCorreoUsuario = (EditText) findViewById(R.id.TXTActualizarCorreoUsuario);
        TXTActualizarDireccionUsuario = (EditText) findViewById(R.id.TXTActualizarDireccionUsuario);
        TXTActualizarCiudadUsuario = (EditText) findViewById(R.id.TXTActualizarCiudadUsuario);
        TXTActualizarTelefonoUsuario = (EditText) findViewById(R.id.TXTActualizarTelefonoUsuario);
        ActualizarFotoCliente = (CircleImageView) findViewById(R.id.ActualizarFotoUsuario);

        BtnActualizarClienteBD = (Button) findViewById(R.id.BtnActualizarClienteBD);
        BtnEliminarClienteBD = (Button) findViewById(R.id.BtnEliminarrClienteBD);

        validator = new Validator(this);
        validator.setValidationListener(this);

        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                Id_Usuario=Integer.parseInt(null);
            }else {
                Id_Usuario= extras.getInt(Utilidades.CAMPO_ID_USUARIO);
            }
        }else {
            Id_Usuario = (int) savedInstanceState.getSerializable(Utilidades.CAMPO_ID_USUARIO);
        }

        db = new DbUsuario(this);
        listaUsuarios = new ArrayList<>(db.ObtenerDatosUsuario(Id_Usuario));


        SetearDatos();


        //Llamado al calendario tras hacer click en el campo de fecha
        TXTActualizarFechaNacimientoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarFechaDeNacimiento();
            }
        });

        //Se actualizan los datos del cliente

        BtnActualizarClienteBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ActualizarDatosUsuario();

            }
        });

        //Boton para eliminar el cliente

        BtnEliminarClienteBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuario db = new DbUsuario(context);
                db.eliminarUsuario(Integer.toString(Id_Usuario));
                db.close();
            }
        });



    }

    private void SetearDatos() {

        //Asignamos imagen

        Bitmap bitmap = BitmapFactory.decodeByteArray(listaUsuarios.get(0).getFotoPerfil(), 0,
                listaUsuarios.get(0).getFotoPerfil().length);
        ActualizarFotoCliente.setImageBitmap(bitmap);

        TXTActualizarNombreUsuario.setText(listaUsuarios.get(0).getNombres());
        TXTActualizarApellidoUsuario.setText(listaUsuarios.get(0).getApellidos());
        TXTActualizarFechaNacimientoUsuario.setText(listaUsuarios.get(0).getFechaNacimiento());
        TXTActualizarCorreoUsuario.setText(listaUsuarios.get(0).getCorreo());
        TXTActualizarDireccionUsuario.setText(listaUsuarios.get(0).getDireccion());
        TXTActualizarCiudadUsuario.setText(listaUsuarios.get(0).getCiudad());
        TXTActualizarTelefonoUsuario.setText(listaUsuarios.get(0).getTelefono());

    }

    private void ActualizarDatosUsuario() {

        validator.validate();

        //Variable Auxiliar para actualizar el email

        String AuxCorreoUsuario = listaUsuarios.get(0).getCorreo();

        //Variable donde se almacena el id del usuario para ejecutar la sentencia UPDATE
        Id_Usuario = listaUsuarios.get(0).getIdUsuario();


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
                            Id_Usuario);
                } else {

                    //Si el usuario no cambio su foto de perfil

                    db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                            TXTActualizarApellidoUsuario.getText().toString(),
                            TXTActualizarFechaNacimientoUsuario.getText().toString(),
                            TXTActualizarCorreoUsuario.getText().toString(),
                            TXTActualizarDireccionUsuario.getText().toString(),
                            TXTActualizarCiudadUsuario.getText().toString(),
                            TXTActualizarTelefonoUsuario.getText().toString(),
                            Id_Usuario);

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
                                Id_Usuario);
                    }else{
                        db.actualizarUsuario(TXTActualizarNombreUsuario.getText().toString(),
                                TXTActualizarApellidoUsuario.getText().toString(),
                                TXTActualizarFechaNacimientoUsuario.getText().toString(),
                                TXTActualizarCorreoUsuario.getText().toString(),
                                TXTActualizarDireccionUsuario.getText().toString(),
                                TXTActualizarCiudadUsuario.getText().toString(),
                                TXTActualizarTelefonoUsuario.getText().toString(),
                                Id_Usuario);

                    }

                }else{
                    Toast.makeText(this, "Este correo ya existe", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private byte[] FotoPerfilEnBytes() {
        if (RutaArchivoUri != null){
            Bitmap bitmap = ((BitmapDrawable)ActualizarFotoCliente.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            imagenEnBytes = byteArrayOutputStream.toByteArray();
        }else{
            imagenEnBytes = null;
        }
        return imagenEnBytes;
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
            String message = error.getCollatedErrorMessage(context);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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
                    ActualizarFotoCliente.setImageBitmap(FotoDePefil);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //Abre la galeria para escoger imagen

    public void EscogerImagenDeGaleriaE(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Seleccione Imagen"),CODIGO_DE_SOLICITUD_IMAGEN);
    }

    //Convierte la foto de perfil escogida en Bytes
}
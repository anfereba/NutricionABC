package com.anfereba.nutricionabc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.anfereba.nutricionabc.db.DbCliente;

import java.io.ByteArrayOutputStream;

public class RegistrarClienteConSQLite extends AppCompatActivity {
    Button BtnRegistrarClienteBD;
    EditText TXTNombreUsuario, TXTApellidoUsuario,TXTFechaNacimientoUsuario,TXTCorreoUsuario,TXTPasswordUsuario,TXTFechaCreacionUsuario;

    Uri RutaArchivoUri;

    ImageView AgregarFotoCliente;

    int CODIGO_DE_SOLICITUD_IMAGEN = 5;

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


        BtnRegistrarClienteBD.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(!TXTNombreUsuario.getText().toString().equals("")&& !TXTApellidoUsuario.getText().toString().equals("")
                    && !TXTFechaNacimientoUsuario.getText().toString().equals("")
                    && !TXTCorreoUsuario.getText().toString().equals("")
                    && !TXTPasswordUsuario.getText().toString().equals("")
                    && !TXTFechaCreacionUsuario.getText().toString().equals("")
                    && AgregarFotoCliente.getDrawable()!=null
                    && FotoPerfilEnBytes()!=null) {

                DbCliente dbCliente = new DbCliente(getApplicationContext());

                long QueryExitosa = dbCliente.insertarUsuario(TXTNombreUsuario.getText().toString(),
                        TXTApellidoUsuario.getText().toString(),TXTFechaNacimientoUsuario.getText().toString(),
                        TXTCorreoUsuario.getText().toString(),TXTPasswordUsuario.getText().toString(),
                        TXTFechaCreacionUsuario.getText().toString(),FotoPerfilEnBytes());


                if (QueryExitosa > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO: "+String.valueOf(QueryExitosa), Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
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
        TXTFechaCreacionUsuario.setText("");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_DE_SOLICITUD_IMAGEN
                && resultCode == RESULT_OK
                && data !=null
                && data.getData() != null){

            RutaArchivoUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),RutaArchivoUri);
                AgregarFotoCliente.setImageBitmap(bitmap);
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
        Bitmap bitmap = ((BitmapDrawable)AgregarFotoCliente.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte [] data = byteArrayOutputStream.toByteArray();
        return data;
    }
}
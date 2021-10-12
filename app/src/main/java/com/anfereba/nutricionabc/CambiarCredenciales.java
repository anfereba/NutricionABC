package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.AESCrypt;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.ArrayList;
import java.util.List;

public class CambiarCredenciales extends AppCompatActivity{

    ArrayList<Usuario> listaArrayUsuarios;
    DbUsuario db;
    int IdUsuario;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String passwordAntiguo;

    EditText TXTPasswordAntiguoUsuario;

    EditText TXTPasswordNuevoUsuario;

    EditText TXTPreguntaUno;

    EditText TXTPreguntaDos;

    Button BtnActualizarCredencialesUsuarioBD, BtnActualizarPreguntasUsuarioBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_credenciales);

        //Se recogen elementos

        TXTPasswordAntiguoUsuario = findViewById(R.id.TXTPasswordAntiguoUsuario);
        TXTPasswordNuevoUsuario = findViewById(R.id.TXTPasswordNuevoUsuario);
        TXTPreguntaUno = findViewById(R.id.TXTPreguntaUno);
        TXTPreguntaDos = findViewById(R.id.TXTPreguntaDos);
        BtnActualizarCredencialesUsuarioBD = findViewById(R.id.BtnActualizarContraseñaUsuarioBD);
        BtnActualizarPreguntasUsuarioBD = findViewById(R.id.BtnActualizarPreguntasUsuarioBD);

        IdUsuario = ObtenerIdUsuarioActual();
        db = new DbUsuario(this);
        listaArrayUsuarios = new ArrayList<>(db.ObtenerDatosUsuario(IdUsuario));

        try {
            passwordAntiguo = AESCrypt.decrypt(listaArrayUsuarios.get(0).getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }



        BtnActualizarCredencialesUsuarioBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarPasswordUsuario();

            }
        });

        BtnActualizarPreguntasUsuarioBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarRespuestasUsuario();

            }   
        });

    }

    private void ActualizarRespuestasUsuario() {

        if(TXTPasswordAntiguoUsuario.getText().toString().equals(passwordAntiguo)){
                String preguntaUno = TXTPreguntaUno.getText().toString();
                String preguntaDos = TXTPreguntaDos.getText().toString();
                if (preguntaUno.equals("") || preguntaDos.equals("")){
                    Toast.makeText(this, "Las respuestas no pueden ser vacias", Toast.LENGTH_SHORT).show();
                }else{
                    db.ActualizarRespuestasUsuario(IdUsuario,preguntaUno, preguntaDos);
                    CambiarCredenciales.this.finish();
                }
        }else{
            Toast.makeText(this, "Tu contraseña no es correcta", Toast.LENGTH_SHORT).show();
        }
    }

    private void ActualizarPasswordUsuario() {

        //Si desea actualizar el password

        if(TXTPasswordAntiguoUsuario.getText().toString().equals(passwordAntiguo)){
            String nuevoPassword = TXTPasswordNuevoUsuario.getText().toString();
            if (nuevoPassword.equals("")){
                Toast.makeText(this, "La nueva contraseña no puede estar vacia", Toast.LENGTH_SHORT).show();
            }else{
                db.ActualizarPasswordUsuario(IdUsuario,nuevoPassword);
                CambiarCredenciales.this.finish();
            }
        }else{
            Toast.makeText(this, "Tu contraseña antigua no es correcta", Toast.LENGTH_SHORT).show();
        }
    }



    private int ObtenerIdUsuarioActual() {

        preferences = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();
        int IdUsuario = preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        return IdUsuario;

    }
}
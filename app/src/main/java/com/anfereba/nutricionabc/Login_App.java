package com.anfereba.nutricionabc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import org.w3c.dom.Text;

import java.util.concurrent.Executor;


public class Login_App extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor; //para manejar eventos callback

    private Integer Id_Sesion = -1;

    EditText Correo,Password;
    Button BtnIngresar, BtnHuellaDactilar;
    TextView BtnRegistroCliente, BtnReestablecerPassword;

    DbUsuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //Inicializa el sharedpreference y el editor para guardar y revisar sesion

        dbUsuario = new DbUsuario(getApplicationContext());

        preferences = this.getSharedPreferences("Sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        if (VerificarIdDeSesion() > 0){

            Id_Sesion = VerificarIdDeSesion();
            IniciarSesion(Id_Sesion);

        }else {
            Toast.makeText(getApplicationContext(), "Debe Iniciar Sesion", Toast.LENGTH_LONG).show();
        }

        //Se recogen los elementos

        Correo= (EditText) findViewById(R.id.Correo);
        Password=(EditText) findViewById(R.id.Password);
        BtnIngresar=(Button) findViewById(R.id.BtnIngresar);
        BtnRegistroCliente=(TextView) findViewById(R.id.BtnRegistroCliente);
        BtnReestablecerPassword=(TextView) findViewById(R.id.BtnReestablecerPassword);
        BtnHuellaDactilar=(Button) findViewById(R.id.BtnHuellaDactilar);
        executor = ContextCompat.getMainExecutor(this);

        BtnHuellaDactilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BiometricManager biometricManager = BiometricManager.from(getApplicationContext());
                if (biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS){
                    if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE){
                        toast("No puede autenticarse porque el hardware no est?? disponible. Int??ntelo de nuevo m??s tarde.");
                    }else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED){
                        toast("No puede autenticarse porque no se ha registrado ninguna credencial biom??trica o de dispositivo.");
                    }else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE){
                        toast("No puede autenticarse porque no hay un hardware adecuado (no hay un sensor biom??trico o no hay un keyguard).");
                    }
                    BtnHuellaDactilar.setText("No Validada");
                    BtnHuellaDactilar.setBackgroundColor(getResources().getColor(R.color.colorError));
                }
                biometricPrompt = new BiometricPrompt(Login_App.this, executor, new BiometricPrompt.AuthenticationCallback(){
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);

                        // Se llama cuando se ha encontrado un error irrecuperable y la operaci??n ha finalizado.

                        BtnHuellaDactilar.setText("No Validada");
                        BtnHuellaDactilar.setBackgroundColor(getResources().getColor(R.color.colorError));
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        BtnHuellaDactilar.setText("Validada");
                        BtnHuellaDactilar.setBackgroundColor(getResources().getColor(R.color.colorExito));

                        //Se llama cuando se reconoce un dato biom??trico.

                    }
                    @Override
                    public void onAuthenticationFailed() {

                        // Se llama cuando un dato biom??trico es v??lido pero no se reconoce

                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(), "La aplicaci??n no ha reconocido la huella dactilar colocada. Por favor, int??ntelo de nuevo", Toast.LENGTH_SHORT).show();
                    }

                });
                //Configuraci??n del t??tulo, descripci??n en el di??logo de autenticaci??n

                promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Autenticacion")
                        .setDescription("Utilizar la huella dactilar para iniciar sesi??n")
                        .setDeviceCredentialAllowed(true) //Habilitar el uso opcional de autenticacion con (PIN - PATRON o Contrase??a)
                        .build();
                biometricPrompt.authenticate(promptInfo);
            }
        });

        //Se programa el evento Onclick

        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbUsuario = new DbUsuario(getApplicationContext());

                //Se determina si las credenciales son correctas

                boolean UsuarioEsValido = dbUsuario.Comprobar_Correo_Password
                        (Correo.getText().toString(),Password.getText().toString())!=null;

                if(UsuarioEsValido && BtnHuellaDactilar.getText().toString().equals("Validada")){
                    IniciarSesion(Id_Sesion);

                }else if(!UsuarioEsValido){
                    Toast.makeText(getApplicationContext(),"El correo o la contrase??a no son correctos" , Toast.LENGTH_LONG).show();
                }else if (UsuarioEsValido && BtnHuellaDactilar.getText().toString().equals("Validar Huella")){
                    Toast.makeText(getApplicationContext(),"Tambien debe validar su huella para iniciar sesion" , Toast.LENGTH_LONG).show();
                }else if (BtnHuellaDactilar.getText().toString().equals("No Validada")){
                    Toast.makeText(getApplicationContext(),"No es posible iniciar sesion, huella no validada" , Toast.LENGTH_LONG).show();
                }

            }
        });

        //Se dirige al registro del cliente

        BtnRegistroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistroUsuarios.class));
                finish();
            }
        });

        BtnReestablecerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), restablecer_password.class));
                finish();

            }
        });


    }



    private void IniciarSesion(int Id_Sesion){

        dbUsuario = new DbUsuario(getApplicationContext());

        String PerfilUsuario = "";
        String Id_Usuario = null;
        String Nombre_Usuario = null;
        String Apellido_Usuario = null;


        if (Id_Sesion > 0){
            PerfilUsuario = dbUsuario.Comprobar_Perfil(Id_Sesion);
        }
        else{

            //Se determina el perfil del usuario para iniciar sesion
            PerfilUsuario = dbUsuario.Comprobar_Correo_Password(Correo.getText().toString(),Password.getText().toString());

            //Se consulta el Id para guardar su sesion
            Id_Usuario = dbUsuario.consultarDato(
                    Utilidades.CAMPO_ID_USUARIO,
                    Utilidades.CAMPO_CORREO,
                    Correo.getText().toString());
            Nombre_Usuario = dbUsuario.consultarDato(
                    Utilidades.CAMPO_NOMBRES,
                    Utilidades.CAMPO_CORREO,
                    Correo.getText().toString());
            Apellido_Usuario = dbUsuario.consultarDato(
                    Utilidades.CAMPO_APELLIDOS,
                    Utilidades.CAMPO_CORREO,
                    Correo.getText().toString());

            GuardarInicioDeSesion(Id_Usuario,Nombre_Usuario,Apellido_Usuario,PerfilUsuario);
        }

        Intent intent = null;

        switch (PerfilUsuario){
            case "Cliente":
                intent = new Intent(getApplicationContext(), MainActivityCliente.class);
                break;
            case "Nutriologo":
                intent = new Intent(getApplicationContext(), MainActivityNutriologo.class);
                break;
            case "Administrador":
                intent = new Intent(getApplicationContext(), MainActivityAdministrador.class);
                break;
        }

        startActivity(intent);
        finish();
    }

    private void GuardarInicioDeSesion(String Id_Usuario, String Nombre_Usuario, String Apellido_Usuario, String Perfil_Usuario) {
        editor.putInt(Utilidades.CAMPO_ID_USUARIO,Integer.parseInt(Id_Usuario));
        editor.putString(Utilidades.CAMPO_NOMBRES,Nombre_Usuario);
        editor.putString(Utilidades.CAMPO_APELLIDOS,Apellido_Usuario);
        editor.putString(Utilidades.CAMPO_NOMBRE_PERFIL,Perfil_Usuario);
        editor.apply();
    }

    private Integer VerificarIdDeSesion() {
        return this.preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
    }
    public void toast(String texto){
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }
}
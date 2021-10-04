package com.anfereba.nutricionabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anfereba.nutricionabc.db.DbUsuario;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.IllegalFormatCodePointException;
import java.util.List;

public class restablecer_password extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty
    @Email
    EditText TXTCorreoUsuarioR;

    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    EditText TXTPasswordUsuarioR;

    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTPreguntaDosR;


    @NotEmpty (message = "Este campo es Obligatorio")
    EditText TXTPreguntaUnoR;

    Button BtnRestablecerPasswordR;

    private Validator validator;
    private boolean DatosValidados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_password);

        BtnRestablecerPasswordR=(Button)findViewById(R.id.BtnRestablecerPasswordR);
        TXTCorreoUsuarioR = findViewById(R.id.TXTCorreoUsuarioR);
        TXTPreguntaUnoR = (EditText) findViewById(R.id.TXTPreguntaUnoR);
        TXTPreguntaDosR = (EditText) findViewById(R.id.TXTPreguntaDosR);
        TXTPasswordUsuarioR=(EditText)findViewById(R.id.TXTPasswordUsuarioR);

        validator = new Validator(this);
        validator.setValidationListener(this);

        BtnRestablecerPasswordR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();

                if (DatosValidados){
                    DbUsuario dbUsuario = new DbUsuario(getApplicationContext());

                    long QueryExitosa = dbUsuario.RestablecerPassword(TXTCorreoUsuarioR.getText().toString(),
                            TXTPasswordUsuarioR.getText().toString(),TXTPreguntaUnoR.getText().toString(),
                            TXTPreguntaDosR.getText().toString());

                    if (QueryExitosa > 0) {
                        startActivity(new Intent(getApplicationContext(), Login_App.class));
                        finish();
                        Toast.makeText(getApplicationContext(), "Contraseña restablecida Correctamente: ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No fue posible reestablecer: ", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
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
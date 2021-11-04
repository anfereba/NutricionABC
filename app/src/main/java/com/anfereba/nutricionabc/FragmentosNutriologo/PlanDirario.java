package com.anfereba.nutricionabc.FragmentosNutriologo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaHijosAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaHijosSinPlanAdapter;
import com.anfereba.nutricionabc.FragmentosNutriologo.Listas.ListaPlanDiarioAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbHijo;
import com.anfereba.nutricionabc.db.DbPlanDiario;
import com.anfereba.nutricionabc.db.DbPlanNutricional;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.PlanesDiarios;
import com.anfereba.nutricionabc.db.Entidades.PlanesNutricionales;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlanDirario extends AppCompatActivity {
int IdHijo =0;
int IdPlanNutricional =0;
TextView PlanDiario, NombresNutriologo, TelefonoNutriologo, CorreoNutriologo;
CircleImageView FotoNutriologo;
RecyclerView AlimentosDiarios;
EditText ComentarioNutriologo;
Button GuardarComentarioDiario, VistoBueno;
ImageView IMGAtras,SharePlan;
ArrayList<Usuario> datosNutriologo;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_dirario);
        PlanDiario = findViewById(R.id.PlanDiario);
        ComentarioNutriologo = findViewById(R.id.ComentarioNutriologo);
        GuardarComentarioDiario = findViewById(R.id.GuardarComentarioDiario);

        ComentarioNutriologo = findViewById(R.id.ComentarioNutriologo);
        GuardarComentarioDiario = findViewById(R.id.GuardarComentarioDiario);

        FotoNutriologo = findViewById(R.id.FotoNutriologo);
        NombresNutriologo = findViewById(R.id.NombresNutriologo);
        TelefonoNutriologo = findViewById(R.id.TelefonoNutriologo);

        CorreoNutriologo = findViewById(R.id.CorreoNutriologo);


        ComentarioNutriologo = findViewById(R.id.ComentarioNutriologo);
        GuardarComentarioDiario = findViewById(R.id.GuardarComentarioDiario);
        VistoBueno = findViewById(R.id.VistoBueno);
        VistoBueno.setVisibility(View.INVISIBLE);
        IMGAtras = findViewById(R.id.IMGAtras);
        SharePlan = findViewById(R.id.SharePlan);
        IMGAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SharePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(takeScreenshot()!=null){
                    Date now = new Date();
                    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
                    try {
                        // nombre y ruta de la imagen a incluir
                        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
                        File imageFile = new File(mPath);
                        FileOutputStream outputStream = new FileOutputStream(imageFile);
                        int quality = 100;
                        Bitmap bitmap=takeScreenshot();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        uri = Uri.fromFile(imageFile);

                    } catch (Throwable e) {
                        // Captura los distintos errores que puedan surgir
                        e.printStackTrace();
                    }
                    Bitmap bitmap = takeScreenshot();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(intent, "Share with"));
                }

            }
        });
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                IdHijo = Integer.parseInt(null);
                IdPlanNutricional = Integer.parseInt(null);
            } else {
                IdHijo = extras.getInt("IdHijo");
                IdPlanNutricional = extras.getInt("IdPlanNutricional");
            }
        } else {
            IdHijo = (int) savedInstanceState.getSerializable("IdHijo");
            IdPlanNutricional = (int) savedInstanceState.getSerializable("IdPlanNutricional");
        }
        DbPlanNutricional dbPlanNutricional = new DbPlanNutricional(PlanDirario.this);
        PlanesNutricionales planesNutricionales= dbPlanNutricional.verPlan(IdPlanNutricional);



        PlanDiario.setText(planesNutricionales.getNombrePlan());
        ComentarioNutriologo.setText(dbPlanNutricional.verComentarioNutriologo(IdHijo,IdPlanNutricional));
        GuardarComentarioDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbPlanNutricional.EditarComentarioNutriologo(IdPlanNutricional,IdHijo,(ComentarioNutriologo.getText()).toString());
                Toast.makeText(PlanDirario.this,"Comentario Guardado",Toast.LENGTH_LONG).show();
            }
        });


       DbPlanDiario dbPlanDiario = new DbPlanDiario(PlanDirario.this);
        AlimentosDiarios = (RecyclerView) findViewById(R.id.AlimentosDiarios);
        AlimentosDiarios.setLayoutManager(new LinearLayoutManager(PlanDirario.this));
        ArrayList<PlanesDiarios> listaPlanDiario = new ArrayList<>();
        ListaPlanDiarioAdapter adapter = new ListaPlanDiarioAdapter(dbPlanDiario.mostrarCumplimientoDelPlanDiario(IdHijo,IdPlanNutricional));
        AlimentosDiarios.setAdapter(adapter);


        DbUsuario dbUsuario = new DbUsuario(PlanDirario.this);
        datosNutriologo = new ArrayList<>(dbUsuario.ObtenerDatosUsuarioConPlan(planesNutricionales.getNombrePlan()));

        //Asignar datos nutriologo a vista

        NombresNutriologo.setText(datosNutriologo.get(0).getNombres() + " "+datosNutriologo.get(0).getApellidos());
        TelefonoNutriologo.setText(datosNutriologo.get(0).getTelefono());
        CorreoNutriologo.setText(datosNutriologo.get(0).getCorreo());
        Bitmap bitmap = BitmapFactory.decodeByteArray(datosNutriologo.get(0).getFotoPerfil(),0,
                datosNutriologo.get(0).getFotoPerfil().length);
        FotoNutriologo.setImageBitmap(bitmap);



        String U = dbUsuario.consultarDato("idPerfilSistema","idUsuario",TomarIdUsuario().toString());
        int u = Integer.parseInt (U);
        if(u==1){
            ComentarioNutriologo.setInputType(InputType.TYPE_NULL);
            GuardarComentarioDiario.setVisibility(View.INVISIBLE);
            GuardarComentarioDiario.setInputType(InputType.TYPE_NULL);
            VistoBueno.setVisibility(View.INVISIBLE);
            VistoBueno.setInputType(InputType.TYPE_NULL);
        }else {
            SharePlan.setVisibility(View.INVISIBLE);
        }
        if(dbPlanNutricional.verPorcentagePlan(IdHijo,IdPlanNutricional)==100&&u==2){
            VistoBueno.setVisibility(View.VISIBLE);
            VistoBueno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbPlanNutricional.EditarVistoBueno(IdPlanNutricional,IdHijo,true);
                    Toast.makeText(PlanDirario.this,"has dado el visto bueno",Toast.LENGTH_LONG).show();
                    DbHijo dbHijo = new DbHijo(PlanDirario.this);
                    dbHijo.AsignarPlanNutricionalAlHijo(IdHijo,0);
                    finish();
                }
            });
        }

    }
    private Integer TomarIdUsuario() {
        SharedPreferences shared = getSharedPreferences("Sesiones", MODE_PRIVATE);
        Integer iDNutriologo = shared.getInt(Utilidades.CAMPO_ID_USUARIO,0 );
        return iDNutriologo;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            finishAffinity ();//se utiliza para terminará la actividad actual y todas las actividades de los padres
            //((MainActivityCliente)getApplicationContext()).finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    private Bitmap takeScreenshot() {
        Bitmap bitmap= null;
        try {
            // crear un bitmap con la captura de pantalla
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
        return bitmap;
    }
}

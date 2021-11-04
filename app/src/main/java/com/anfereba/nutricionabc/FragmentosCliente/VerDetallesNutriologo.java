package com.anfereba.nutricionabc.FragmentosCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.FragmentosCliente.Listas.ListaCalificacionesNutriologoAdapter;
import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbCalificacion;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.VistasDb.VistaCalificacionUsuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerDetallesNutriologo extends AppCompatActivity {

    private int Id_Nutriologo;

    DbUsuario db;
    DbCalificacion dbCalificacion;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    RecyclerView listaCalificacionesRV;
    ListaCalificacionesNutriologoAdapter adapter;
    ArrayList<VistaCalificacionUsuario> listaArrayCalificaciones;


    CircleImageView imagenNutriologo;
    LinearLayout personalinfo, experience, review;
    TextView personalinfobtn, experiencebtn, reviewbtn,
            TXTNombreNutriologo, TXTTelefonoNutriologo, TXTCorreoNutriologo, TXTCiudadNutriologo;
    EditText TXTComentario;

    private ArrayList<Usuario> listaNutriologos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalles_nutriologo);


        personalinfo = findViewById(R.id.personalinfo);
        experience = findViewById(R.id.experience);
        review = findViewById(R.id.review);
        personalinfobtn = findViewById(R.id.personalinfobtn);
        experiencebtn = findViewById(R.id.experiencebtn);
        reviewbtn = findViewById(R.id.reviewbtn);
        imagenNutriologo = findViewById(R.id.imagenNutriologo);
        TXTNombreNutriologo = findViewById(R.id.TXTNombreNutriologo);
        TXTTelefonoNutriologo = findViewById(R.id.TXTTelefonoNutriologo);
        TXTCorreoNutriologo= findViewById(R.id.TXTCorreoNutriologo);
        TXTCiudadNutriologo = findViewById(R.id.TXTCiudadNutriologo);
        TXTComentario = findViewById(R.id.TXTComentario);


        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
        experience.setVisibility(View.GONE);
        review.setVisibility(View.GONE);


        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                Id_Nutriologo=Integer.parseInt(null);
            }else {
                Id_Nutriologo= extras.getInt(Utilidades.CAMPO_ID_USUARIO);
            }
        }else {
            Id_Nutriologo = (int) savedInstanceState.getSerializable(Utilidades.CAMPO_ID_USUARIO);
        }

        db = new DbUsuario(this);
        dbCalificacion = new DbCalificacion(this);

        listaNutriologos = new ArrayList<>(db.ObtenerDatosUsuario(Id_Nutriologo));



        SetearDatos();

        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.RatingCalifNutri);
        Button BtnCalifNutri = (Button) findViewById(R.id.BtnCalifNutri);
        BtnCalifNutri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String puntuacion = "Rating :: " + simpleRatingBar.getRating();
                Toast.makeText(getApplicationContext(), "Total de Estrellas:: "+totalStars + "\n" +
                        "Rating :: "+puntuacion +"\n", Toast.LENGTH_LONG).show();

                Double puntaje = Double.valueOf(simpleRatingBar.getRating());
                long query = dbCalificacion.insertarCalificacion(ObtenerIdUsuarioActual(),Id_Nutriologo, puntaje,TXTComentario.getText().toString());
                if (query > 0){
                    Toast.makeText(getApplicationContext(), "Calificacion Enviada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Error al enviar calificacion", Toast.LENGTH_SHORT).show();
                }
            }
        });


        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.VISIBLE);
                experience.setVisibility(View.GONE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        experiencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbUsuario dbUsuario = new DbUsuario(getApplicationContext());
                String PerfilUsuario = dbUsuario.Comprobar_Perfil(ObtenerIdUsuarioActual());
                if (PerfilUsuario.equals("Cliente")){
                    personalinfo.setVisibility(View.GONE);
                    experience.setVisibility(View.VISIBLE);
                    review.setVisibility(View.GONE);
                    personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                    experiencebtn.setTextColor(getResources().getColor(R.color.blue));
                    reviewbtn.setTextColor(getResources().getColor(R.color.grey));
                }else{
                    Toast.makeText(getApplicationContext(), "No puedes calificarte a ti mismo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarListaCalificaciones();
                personalinfo.setVisibility(View.GONE);
                experience.setVisibility(View.GONE);
                review.setVisibility(View.VISIBLE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.blue));

            }
        });
    }

    private void mostrarListaCalificaciones() {
        listaCalificacionesRV = findViewById(R.id.listaCalificacionesRV);
        listaCalificacionesRV.setLayoutManager(new LinearLayoutManager(this));
        DbCalificacion db = new DbCalificacion(VerDetallesNutriologo.this);
        listaArrayCalificaciones = new ArrayList<>();
        adapter = new ListaCalificacionesNutriologoAdapter(db.ConsultarListaCalificaciones(Id_Nutriologo),this);
        listaCalificacionesRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void SetearDatos() {
        Bitmap bitmap = BitmapFactory.decodeByteArray(listaNutriologos.get(0).getFotoPerfil(), 0,
                listaNutriologos.get(0).getFotoPerfil().length);

        imagenNutriologo.setImageBitmap(bitmap);

        TXTNombreNutriologo.setText(listaNutriologos.get(0).getNombres() + " " + listaNutriologos.get(0).getApellidos());
        TXTTelefonoNutriologo.setText(listaNutriologos.get(0).getTelefono());
        TXTCorreoNutriologo.setText(listaNutriologos.get(0).getCorreo());
        TXTCiudadNutriologo.setText(listaNutriologos.get(0).getCiudad() + " , "+
                listaNutriologos.get(0).getDireccion());
    }

    private int ObtenerIdUsuarioActual() {
        int IdUsuario = 0;
        preferences = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();
        IdUsuario = preferences.getInt(Utilidades.CAMPO_ID_USUARIO,0);
        return IdUsuario;
    }
}
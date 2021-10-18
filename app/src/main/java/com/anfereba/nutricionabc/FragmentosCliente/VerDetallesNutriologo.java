package com.anfereba.nutricionabc.FragmentosCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.DbUsuario;
import com.anfereba.nutricionabc.db.Entidades.Usuario;
import com.anfereba.nutricionabc.db.utilidades.Utilidades;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerDetallesNutriologo extends AppCompatActivity {

    private int Id_Usuario;

    DbUsuario db;

    CircleImageView imagenNutriologo;
    LinearLayout personalinfo, experience, review;
    TextView personalinfobtn, experiencebtn, reviewbtn,
            TXTNombreNutriologo, TXTTelefonoNutriologo, TXTCorreoNutriologo, TXTCiudadNutriologo;

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


        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
        experience.setVisibility(View.GONE);
        review.setVisibility(View.GONE);


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
        listaNutriologos = new ArrayList<>(db.ObtenerDatosUsuario(Id_Usuario));


        SetearDatos();

        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.RatingCalifNutri);
        Button BtnCalifNutri = (Button) findViewById(R.id.BtnCalifNutri);
        BtnCalifNutri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();
                Toast.makeText(getApplicationContext(), "Total de Estrellas:: "+totalStars + "\n" +
                        "Rating :: "+rating +"\n", Toast.LENGTH_LONG).show();
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

                personalinfo.setVisibility(View.GONE);
                experience.setVisibility(View.VISIBLE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                experiencebtn.setTextColor(getResources().getColor(R.color.blue));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                experience.setVisibility(View.GONE);
                review.setVisibility(View.VISIBLE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.blue));

            }
        });
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
}
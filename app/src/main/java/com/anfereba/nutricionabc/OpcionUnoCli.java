package com.anfereba.nutricionabc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpcionUnoCli extends AppCompatActivity   {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_opcion_uno_cliente);

        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.RatingCalifNutri);
        Button submitButton = (Button) findViewById(R.id.BtnCalifNutri);
        EditText text = (EditText)findViewById(R.id.EditTextCalifNutri);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();
                Toast.makeText(getApplicationContext(), "Total de Estrellas:: "+totalStars + "\n" + "Rating :: "+rating +"\n"
                        + "ID Nutriologo:: " +text.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


}

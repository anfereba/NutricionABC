package com.anfereba.nutricionabc.FragmentosCliente;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.anfereba.nutricionabc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionUnoCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionUnoCliente extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionUnoCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionUnoCliente.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionUnoCliente newInstance(String param1, String param2) {
        OpcionUnoCliente fragment = new OpcionUnoCliente();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_opcion_uno_cliente, container, false);
        final RatingBar simpleRatingBar = (RatingBar) view.findViewById(R.id.RatingCalifNutri);
        Button submitButton = (Button) view.findViewById(R.id.BtnCalifNutri);
      //  Spinner spinner =(Spinner) view.findViewById(R.id.spinnerCali);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();

                Toast.makeText(getActivity(), "Total de Estrellas:: "+totalStars + "\n" + "Rating :: "+rating +"\n"
                        + "ID Nutriologo:: ", Toast.LENGTH_LONG).show();
            }
        });
      return view;
    }
}
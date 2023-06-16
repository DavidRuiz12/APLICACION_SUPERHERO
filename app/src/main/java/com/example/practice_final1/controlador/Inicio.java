package com.example.practice_final1.controlador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practice_final1.R;
import com.example.practice_final1.adaptador.ReciiclerView;

public class Inicio extends AppCompatActivity {
    //Declaracion de las variables
    Button G_Personajes;
    Button Reciclerview;
    Button ajustes;
    TextView textView;
    Preferencias preferencias;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Inicializacion de las variables
        G_Personajes = (Button) findViewById(R.id.Galeria);
        Reciclerview = (Button) findViewById(R.id.btn_Recycler);
        ajustes = (Button) findViewById(R.id.btn_ajustes);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintinicio);

        Preferencias preferencias = new Preferencias();
        preferencias.guardarpreferencias(Inicio.this,constraintLayout);

        //Estoss botones se encargan de hacer posible el navegar entre pestañas.
        G_Personajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cuando pulsamos no lleva de la pantalla de Inicio a la de Galeria.
                Intent i = new Intent(Inicio.this, Galeria.class);
                //Inicializamos la pantalla de Galeria
                startActivity(i);
            }
        });

        Reciclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Inicio.this, ReciiclerView.class);
                startActivity(i);
            }
        });

        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Inicio.this, Preferencias.class);
                startActivity(i);
            }
        });


    }

    public void loadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Inicio.this);
        String emailText = sharedPreferences.getString("EMAILPREFERENCES","valor por defecto");
        textView.setText(emailText);
    }

}
package com.example.practice_final1.controlador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.practice_final1.R;

public class Galeria extends AppCompatActivity {

    //Inicializacion de variables
    private ImageView imagen;
    private Button Personaje1;
    private Button personaje2;
    private Button personaje3;
    private Button personaje4;
    private Button personaje5;
    private Button personaje6;
    Preferencias preferencias;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        //Declaracion de variables
        imagen = (ImageView) findViewById(R.id.imagen_s);
        Personaje1 = (Button) findViewById(R.id.Personaje_1);
        personaje2 = (Button) findViewById(R.id.Personaje_2);
        personaje3 = (Button) findViewById(R.id.Personaje_3);
        personaje4 = (Button) findViewById(R.id.Personaje_4_);
        personaje5 = (Button) findViewById(R.id.Personaje_5_);
        personaje6 = (Button) findViewById(R.id.Personaje_6_);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintgaleria);

        //Una vez que hayamos seleccionado o desdelecionado el modo oscuro, aparecera la proxima vez que abramos la pantalla.
        Preferencias preferencias = new Preferencias();
        preferencias.guardarpreferencias(Galeria.this,constraintLayout);

        //Creamos un onclickListener que se encargara de hacer un peticion REST de la imagen a la API
       Personaje1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               myLongToast("A-BOMB");
               Glide.with(Galeria.this)
                       .load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/1-a-bomb.jpg")
                       .error(R.mipmap.ic_launcher)
                       .into(imagen);
           }
       });
       personaje2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               myLongToast("WONDER WOMAN");
               Glide.with(Galeria.this)
                       .load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/720-wonder-woman.jpg")
                       .error(R.mipmap.ic_launcher)
                       .into(imagen);
           }
       });

        personaje3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("THOR");
                Glide.with(Galeria.this)
                        .load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/659-thor.jpg")
                        .error(R.mipmap.ic_launcher)
                        .into(imagen);
            }
        });

        personaje4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("SPIDER-MAN");
                Glide.with(Galeria.this)
                        .load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/620-spider-man.jpg")
                        .error(R.mipmap.ic_launcher)
                        .into(imagen);
            }
        });

        personaje5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("WOLVERINE");
                Glide.with(Galeria.this)
                        .load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/717-wolverine.jpg")
                        .error(R.mipmap.ic_launcher)
                        .into(imagen);
            }
        });

        personaje6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLongToast("STEPPENWOLF");
                Glide.with(Galeria.this)
                        .load("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/637-steppenwolf.jpg")
                        .error(R.mipmap.ic_launcher)
                        .into(imagen);
            }
        });


    }

    //Metodo que encapsular el TOAST, para crealos.
    public void myLongToast(String msg){
        Toast.makeText(Galeria.this, msg, Toast.LENGTH_LONG).show();
    }
}
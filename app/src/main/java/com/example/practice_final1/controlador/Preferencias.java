package com.example.practice_final1.controlador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.practice_final1.R;
import com.example.practice_final1.Fragments.SettingsFragment;

//Clase preferencias.
public class Preferencias extends AppCompatActivity {
    //Declaracion de las variables.
private ConstraintLayout constraintLayout;
public int cambio = 0;
private Button botonsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        botonsalir = (Button) findViewById(R.id.btn_salir);

        //Inicializacion del boton salir.
        botonsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Boton que se encarga de crear un AlertDaialog que nos pregunta si queremos salir de la aplicacion.
            AlertDialog alertDialog = crearalertdialog("Salir","¿Estas seguro/a de que qieres salir de aplicacion");
            alertDialog.show();
            }
        });

        //Linea de codigo que se encarga de configurar y mostrar el fragmento de SettingFragments en el constraintLayout.
        constraintLayout = (ConstraintLayout) findViewById(R.id.SettingContainer);
        guardarpreferencias(Preferencias.this,constraintLayout);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.SettingContainer,new SettingsFragment())
                .commit();
    }

    //Metodo que se encarga de guardar las prefrencias que hemos elegido, segun lo que hayamos seleccionado en el modo oscuro, cambiara
    //el color del backgrond.
    public void guardarpreferencias(Context context, ConstraintLayout constrain){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean modo_oscuro = sharedPreferences.getBoolean("btn_modo_oscuro",false);
        if(modo_oscuro){
            constrain.setBackgroundColor(Color.DKGRAY);
            cambio = 1;
        }
    }

    //Metodo encargado de configurar el AlertDialog
    public AlertDialog crearalertdialog(String titulo, String mensaje){
        //Primero creamos el el builder.
        AlertDialog.Builder builder = new AlertDialog.Builder(Preferencias.this);

        //El builder va a llevar un mensaje y un titulo.
        builder.setMessage(mensaje)
                .setTitle(titulo);

        //Aqui indicamos que pasa si seleccionamos al opcion positiva del AlertDialog.
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            //Si pulsamos que si se cierra toda actividad de la aplicacion.
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        //Si elegimos la opcion negativa no ocurre nada.
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //Devolvemos el builder ya creado.
        return builder.create();
    }


}
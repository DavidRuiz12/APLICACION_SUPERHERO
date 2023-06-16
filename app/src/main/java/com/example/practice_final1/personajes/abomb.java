package com.example.practice_final1.personajes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.practice_final1.Fragments.Powerstats;
import com.example.practice_final1.R;
import com.example.practice_final1.controlador.Preferencias;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class abomb extends AppCompatActivity {
    //Declaracion de las varaibles.
    private TextView textView;
    private static final String URL_JSON = "https://akabab.github.io/superhero-api/api/id/1.json"; // URL que vamos a utilizar.
    Preferencias preferencias;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abomb);
        textView = findViewById(R.id.txt_estadisticas);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintabomb);

        Preferencias preferencias = new Preferencias();
        preferencias.guardarpreferencias(abomb.this,constraintLayout);

        //Creamos un hilos.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Cogemos al URL base, nos conectamos a ella e indicamos que vamosa  utilizar un metodo GET sobre ella.
                    URL url = new URL(URL_JSON);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");

                    int responseCode = httpURLConnection.getResponseCode();
                    //Indicamos que si el servidor nos ha devuelto un OK  a la conexion.
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        //Leemos el json.
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        //Creamos un StringBuilder
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        //Con un bucle indicamos que lea linea a linea de json y lo introduzca al String.
                        while ((line = reader.readLine()) != null) {
                            //Añadimos al StringBuilder las lineas que estamos lellendo
                            stringBuilder.append(line);
                        }
                        //Cerramos el reader.
                        reader.close();
                        //Creamos un String json, donde introduciremos la informacion que hay dentro del stringBuilder.
                        String json = stringBuilder.toString();

                        //Utilizamos la libreria GSON para parsear el JSON.
                        Gson gson = new Gson();
                        //Creamos un JsonObject.
                        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);


                        int intelligence = -1;
                        int speed = -1;
                        int strench = -1;
                        int durability = -1;
                        int power = -1;
                        int combat = -1;

                        //Aqui tenemos que verificar si el objeto jsonObject, tiene una claver llamada powerstats si es asi se extraen los valores siguientes.
                        if (jsonObject.has("powerstats") && !jsonObject.get("powerstats").isJsonNull()) {
                            JsonObject powerstats = jsonObject.getAsJsonObject("powerstats");

                            //Si dentro de powerstats hay un clave llamada inteligencia, extraemos su infromacion.
                            if (powerstats.has("intelligence") && !powerstats.get("intelligence").isJsonNull()) {
                                intelligence = powerstats.get("intelligence").getAsInt();
                            }

                            if (powerstats.has("speed") && !powerstats.get("speed").isJsonNull()) {
                                speed = powerstats.get("speed").getAsInt();
                            }

                            if (powerstats.has("strength") && !powerstats.get("strength").isJsonNull()) {
                                strench = powerstats.get("strength").getAsInt();
                            }
                            if (powerstats.has("durability") && !powerstats.get("durability").isJsonNull()) {
                                durability = powerstats.get("durability").getAsInt();
                            }
                            if (powerstats.has("power") && !powerstats.get("power").isJsonNull()) {
                                power = powerstats.get("power").getAsInt();
                            }
                            if (powerstats.has("combat") && !powerstats.get("combat").isJsonNull()) {
                                combat = powerstats.get("combat").getAsInt();
                            }

                        }


                        String name = jsonObject.get("name").getAsString();

                        //Convertimos nuestro jsonObject a una instancia de Powerstats.
                        Powerstats myData = gson.fromJson(jsonObject, Powerstats.class);


                        Log.d("JSON", "Name: " + name);
                        //Aqui mostramos la informacion que hemos obtenido de nuestro JSON   nuestro gusto.
                        textView.setText("Nombre: " + name +"\n" + "Inteligencia: "+ intelligence + "\n" + "Velocidad: " + speed +  "\n"+ "Fuerza"  + strench + "\n" + "Durabilidad: " + durability + "\n" + "Poder: " + power + "\n" + "Combate: " + combat );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
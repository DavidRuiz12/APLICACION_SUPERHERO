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

public class thor extends AppCompatActivity {
    private TextView textView;
    private static final String URL_JSON = "https://akabab.github.io/superhero-api/api/id/659.json"; // Reemplaza con tu URL
    Preferencias preferencias;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thor);

        textView = findViewById(R.id.estadisticasT_txt);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constrainthor);

        Preferencias preferencias = new Preferencias();
        preferencias.guardarpreferencias(thor.this,constraintLayout);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(URL_JSON);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        reader.close();

                        String json = stringBuilder.toString();

                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);


                        int intelligence = -1;
                        int speed = -1;
                        int strench = -1;
                        int durability = -1;
                        int power = -1;
                        int combat = -1;

                        if (jsonObject.has("powerstats") && !jsonObject.get("powerstats").isJsonNull()) {
                            JsonObject powerstats = jsonObject.getAsJsonObject("powerstats");

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

                        Powerstats myData = gson.fromJson(jsonObject, Powerstats.class);

                        Log.d("JSON", "Name: " + name);
                        System.out.println(name);
                        textView.setText("Nombre: " + name +"\n" + "Inteligencia: "+ intelligence + "\n" + "Velocidad: " + speed +  "\n"+ "Fuerza"  + strench + "\n" + "Durabilidad: " + durability + "\n" + "Poder: " + power + "\n" + "Combate: " + combat );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
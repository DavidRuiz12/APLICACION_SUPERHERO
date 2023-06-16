package com.example.practice_final1.adaptador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.practice_final1.R;
import com.example.practice_final1.controlador.PersonajesM;
import com.example.practice_final1.controlador.Preferencias;

import java.util.ArrayList;
import java.util.List;

public class ReciiclerView extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerAdapter1 recAdapter;
    public PersonajesM itemselected = null;
    public String nombre;
    Preferencias preferencias;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciicler_view);

        recycler = (RecyclerView) findViewById(R.id.recView);
        recAdapter = new RecyclerAdapter1(getListCharacter(),this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintrec);

        Preferencias preferencias = new Preferencias();
        preferencias.guardarpreferencias(ReciiclerView.this,constraintLayout);


        recAdapter.setOnItemClickListener(new RecyclerAdapter1.OnItemClickListener() {

            public void onItemClick(int posicion) {
                itemselected = recAdapter.ListCharacter.get(posicion);
                System.out.println("Se muestra : " + nombre);
                nombre = itemselected.getNombre().toString();

            }
        });
        recycler.setAdapter(recAdapter);
        recycler.setLayoutManager(layoutManager);


    }


    public List<PersonajesM> getListCharacter(){

        ArrayList<PersonajesM> list = new ArrayList<PersonajesM>();

        list.add(new PersonajesM("Spider-Man","Nombre real: Peter Parker Un adolescente por el que fue picado una araña modificada geneticamente por Industrias Oscorp y fue la que dio poderes a Peter",R.drawable.peter));
        list.add(new PersonajesM("A-Bomb","Nombre real: Richard Milhouse Ex compañero de Bruce Banner (Hulk),obtenio sus poderes por absorber la radiacion gamma de una bomba nuclear.",R.drawable.rick));
        list.add(new PersonajesM("Wonder Womman","Nombre real: Diana, Princesa de las Amazonas, la cual es la guerrera mas poderosa y la encargada de la protectora de la tierra",R.drawable.wonder));
        list.add(new PersonajesM("Thor","Nombre real: Thor es el dios de la trueno Dios de la mitologia nordica, principe de Asghard e hijo de Odin. Los poderes de Thor son controlar los truenos y poder controlar a su martillo Miornir",R.drawable.thor));
        list.add(new PersonajesM("Wolverine","Nombre real: Logan, Logan es un mutante con garras de hueso y autoregenaracion aumentada. Experimentaron con el para hacerlo cun supersoldado y le bañaron todos sus huesos en metal",R.drawable.logan));
        list.add(new PersonajesM("Sttepenwolf","Nombre real: Sttepenwolf Subordinado de DarkSide el conquistador ,este se encarga de recuperar las cajas madre escondidas en la tierra",R.drawable.ste));
        return list;

    }



}
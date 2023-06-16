package com.example.practice_final1.controlador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practice_final1.io.DatabaseHelper;
import com.example.practice_final1.R;

public class MainActivity extends AppCompatActivity {

    //Declaracion de las variables
    DatabaseHelper mDB;
    Button Inicio;
    Button Registro;
    EditText nombre;
    EditText contraseña;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializacion de varaibles.
        mDB = new DatabaseHelper(this);
        mDB.getversionDB();

        Inicio = (Button) findViewById(R.id.btn_Iniciosesion);
        Registro = (Button) findViewById(R.id.btn_Registrar);
        nombre = (EditText) findViewById(R.id.edit_nameUsuario);
        contraseña = (EditText) findViewById(R.id.edit_contraseñaUduario);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintmain);
        Preferencias preferencias = new Preferencias();
        preferencias.guardarpreferencias(MainActivity.this,constraintLayout);


        //Boton que de iniciar sesion
        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variables booleanas que nos devuelver true o false dependiendo si existen el usuario o contraseña
                boolean existuser = comprobarusuario();
                boolean existcontra = comprobarcontraseña();
                //Metodos que comprueba si el usuario y la contraseñan estan dentro de la base de datos
                comprobarusuario();
                comprobarcontraseña();

                //Si el usuario y la contraseña estan dentro de la base de datos, se inicia sesion.
                if(existuser == true && existcontra ){
                    myLongToast("Sesion iniciada");
                    //Se cambia la pantalla a la pantalla de inicio.
                    Intent i = new Intent(MainActivity.this, com.example.practice_final1.controlador.Inicio.class);
                    startActivity(i);
                }else{
                    //Si no existen, no aparecera este mensaje.
                    myLongToast("El usuario o la contraseña son incorrectos");
                }
            }
        });

        //Boton que se se encarga de introducir la informacion dentro de la base de datos.
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variable booleana que devuelve true o false si dependiendo de si el usuario existe.
                boolean existuser = comprobarusuario();

                String usuario = nombre.getText().toString();
                String contraseñaa = contraseña.getText().toString();

                //Si el usuario no existe.
                if(existuser != true){
                    //Introducimos en la base de datos el usuario ya la contarseña.
                    if(mDB.insert(usuario,contraseñaa) != -1 ){
                        myLongToast("El usuario y la contraseña se han insertado");
                    }
                }else{
                    //Si existe, no indica un mensaje que el usuario ya existe.
                    myLongToast("El usuario ya existe");
                }
            }
        });
    }

    public void myLongToast(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    //Metodo que se encarga de comprobar si el usuario existe dentro de la base de datos.
    private boolean comprobarusuario(){
        boolean existe = false;
        //Guardamos el nombre que tenga en ese momento el EditText.
        String elementoBuscado = nombre.getText().toString().trim();

        //Metodo que se encarga de leer la base de datos.
        SQLiteDatabase database = mDB.getReadableDatabase();
        //Creacion de un cursor
        Cursor cursor = null;
        try {
            //Aqui indicamos la setencia SQL que vamosa  utilizar.
            String query = "SELECT * FROM " + DatabaseHelper.DB_TABLE_NAME +
                    " WHERE " + DatabaseHelper.USUARIO_COLUMN + " = ?";
            //Indicamos el elemento que estamos buscando.
            cursor = database.rawQuery(query, new String[]{elementoBuscado});

            //Si el cursor es diferente de 0 y es mayor de 0 sifinifca que ha encontrado un elemento dentro de la base de datos,
            //que concide con el elemento que estamos buscando.
            if (cursor != null && cursor.getCount() > 0) {
                existe = true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            // Cierra el cursor y la base de datos
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        //Decuelve una variable boolean que nos indica si existe el elemto buscado o no.
        return existe;
    }

    private boolean comprobarcontraseña(){
        boolean existe = false;
        String elementoBuscado = contraseña.getText().toString().trim();

        SQLiteDatabase database = mDB.getReadableDatabase();

        Cursor cursor = null;
        try {
            String query = "SELECT * FROM " + DatabaseHelper.DB_TABLE_NAME +
                    " WHERE " + DatabaseHelper.CONTRASEÑA_COLUMN + " = ?";
            cursor = database.rawQuery(query, new String[]{elementoBuscado});

            if (cursor != null && cursor.getCount() > 0) {
                existe = true;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {

            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return existe;
    }



}
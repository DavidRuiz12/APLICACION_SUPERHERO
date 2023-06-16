package com.example.practice_final1.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.JsonToken;
import android.util.Log;

//Clase que se encarga de la creacion de la Base de Datos.
public class DatabaseHelper extends SQLiteOpenHelper {
        //Nombre de la base de datos
        public static final String DB_NAME = "db_dam";
        //Nombre de la tabla
        public static final String DB_TABLE_NAME = "DB_LOGIN";
        //Version de la base de datos
        public static final int DB_VERSION = 2;

        //Nombre de las columnas que va a contener la base de datos.
        public static final String USUARIO_COLUMN = "userName";
        public static final String CONTRASEÑA_COLUMN = "contrasenaColumn";

        private Context mcontext;

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mcontext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //Version 2
            //Creacion de la tabla de la base de datos.
            String CREATE_USER_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "("
                    + USUARIO_COLUMN + " TEXT,"
                    + CONTRASEÑA_COLUMN + " TEXT)";

            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
            Log("Tablas creadas");
        }

        //Metodo que se encarga de la actualizacion de las diferentes versdiones de la base de datos.
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log("onUpgrade");
            Log("oldVersion -> " + oldVersion);

            switch (oldVersion) {
                case 1:
                    sqLiteDatabase.execSQL("ALTER TABLE " + DB_TABLE_NAME + " ADD COLUMN " + CONTRASEÑA_COLUMN + " TEXT");
                    Log.i("DB", "BBDD Actualizada a la versión 2");
            }
        }

        public void getversionDB() {
            Log(Integer.toString(this.getReadableDatabase().getVersion()));
        }

        //Metodo que se encarga de insertar elementos dentro de la base de datos.
        public long insert(String Usuario, String contraseña) {
            SQLiteDatabase db = this.getWritableDatabase();
            long result = -1;
            ContentValues values = new ContentValues();
            values.put(CONTRASEÑA_COLUMN, contraseña);
            values.put(USUARIO_COLUMN, Usuario);

            result = db.insert(DB_TABLE_NAME, null, values);
            db.close();
            return result;
        }

        private void Log(String message) {
            Log.d("DB", message);
        }
}


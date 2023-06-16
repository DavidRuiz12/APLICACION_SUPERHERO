package com.example.practice_final1.Fragments;

import com.google.gson.annotations.SerializedName;

//Clase que utilizo para el parseo del JSON
public class Powerstats {

    //Con serializablename indicamos que el valor de la clave inteligencia del JSON debe asiganarse al campo inteligencia de esta clase.
    @SerializedName("intelligence")
 private int inteligencia;
    @SerializedName("strength")
    private int fuerza;
    @SerializedName("speed")
 private int velocidad;
    @SerializedName("durability")
 private int durabilidad;
    @SerializedName("power")
 private int poder;
    @SerializedName("combat")
 private int combate;

    public Powerstats(int inteligencia, int fuerza, int velocidad, int durabilidad, int poder, int combate) {
        this.inteligencia = inteligencia;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.durabilidad = durabilidad;
        this.poder = poder;
        this.combate = combate;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getCombate() {
        return combate;
    }

    public void setCombate(int combate) {
        this.combate = combate;
    }
}

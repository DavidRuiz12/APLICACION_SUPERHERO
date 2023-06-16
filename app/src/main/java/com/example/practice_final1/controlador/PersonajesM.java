package com.example.practice_final1.controlador;


public class PersonajesM {
    private String Nombre;
    private String Descripcion;
    private int img;

    public PersonajesM (String Nombre, String Descripcion,int img){
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.img = img;

    }

    public String getNombre(){
        return Nombre;
    }

    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    public String getDescripcion(){
        return Descripcion;
    }

    public void setDescripcion(String Descripcion){
        this.Descripcion = Descripcion;
    }

    public int getImg(){
        return img;
    }

    public void setImg(int img){
        this.img = img;
    }






}

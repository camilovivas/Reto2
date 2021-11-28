package model;

import java.io.Serializable;

public class Entrenador implements Serializable {
    private String nombre;

    public Entrenador(){
    }

    public Entrenador(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

package es.backend.eltiempo.model;

public class Provincias {
    private String nombre;
    private int cod;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Provincias(){

    }
    public Provincias(String nombre, int code){
        this.nombre = nombre;
        this.cod = code;
    }
}

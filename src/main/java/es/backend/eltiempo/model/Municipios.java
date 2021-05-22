package es.backend.eltiempo.model;

public class Municipios {

    private String nombre;
    private int cod;
    private int codProvincia;

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

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

    public Municipios(){

    }
    public Municipios(String nombre, int code, int provincia){
        this.nombre = nombre;
        this.cod = code;
        this.codProvincia = provincia;
    }
}

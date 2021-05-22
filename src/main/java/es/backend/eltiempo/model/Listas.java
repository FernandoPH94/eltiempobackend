package es.backend.eltiempo.model;

import java.util.List;

public class Listas {
    
    public List<Municipios> getListamunicipios() {
        return listamunicipios;
    }
    public void setListamunicipios(List<Municipios> listamunicipios) {
        this.listamunicipios = listamunicipios;
    }
    public List<Provincias> getListaprovincias() {
        return listaprovincias;
    }
    public void setListaprovincias(List<Provincias> listaprovincias) {
        this.listaprovincias = listaprovincias;
    }
    
    private List<Provincias> listaprovincias;
    private List<Municipios> listamunicipios;

    public Listas(){

    }

    public Listas(List<Municipios> municipios, List<Provincias> provincias){
        this.listamunicipios = municipios;
        this.listaprovincias = provincias;
    }
    
}

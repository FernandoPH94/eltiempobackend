package es.backend.eltiempo.model;

import java.util.List;

public class MunicipioTemperatura {
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Temperatura> getTemperaturamunicipio() {
        return temperaturamunicipio;
    }
    public void setTemperaturamunicipio(List<Temperatura> temperaturamunicipio) {
        this.temperaturamunicipio = temperaturamunicipio;
    }

    private String name;
    private List<Temperatura> temperaturamunicipio;
    
    public MunicipioTemperatura(){

    }
    public MunicipioTemperatura(String name, List<Temperatura> listatemperatura){
        this.name = name;
        this.temperaturamunicipio = listatemperatura;
    }
}

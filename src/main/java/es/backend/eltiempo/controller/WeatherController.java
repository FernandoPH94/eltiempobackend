package es.backend.eltiempo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import es.backend.eltiempo.data.MunicipiosDaoImpl;
import es.backend.eltiempo.data.ProvinciasDaoImpl;
import es.backend.eltiempo.data.WeatherTodayDaoImpl;
import es.backend.eltiempo.model.Listas;
import es.backend.eltiempo.model.MunicipioTemperatura;
import es.backend.eltiempo.model.Municipios;
import es.backend.eltiempo.model.Provincias;
import es.backend.eltiempo.model.Temperatura;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {

    public String http = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/";
    public List<Temperatura> listatemperatura = new ArrayList<Temperatura>();
    public List<Municipios> listamunicipios = new ArrayList<Municipios>();
    public List<Provincias> listaprovincias = new ArrayList<Provincias>();

    //Envia la información de la temperatura semanal al cliente
    
    @RequestMapping(value = "municipio/{id}", 
    method = RequestMethod.GET,
    produces = "application/json")
    @ResponseBody
    public MunicipioTemperatura data(@PathVariable String id) {
        System.out.println(id);
        WeatherTodayDaoImpl wt = new WeatherTodayDaoImpl();
        return new MunicipioTemperatura(wt.weatherRequestMunicipio(http,id),wt.weatherRequest(http, id));
    }

    //Envia los municipios al dropdown
    @RequestMapping(value = "cod", 
    method = RequestMethod.GET,
    produces = "application/json")
    @ResponseBody
    public Listas data() {
        MunicipiosDaoImpl md = new MunicipiosDaoImpl();
        ProvinciasDaoImpl pd = new ProvinciasDaoImpl();
        if(listamunicipios.size() == 0)
            listamunicipios = md.codigoMunicipios();
        if(listaprovincias.size() == 0){
            listaprovincias = pd.getProvincias();
        }

        return new Listas(listamunicipios,listaprovincias);
    }

}


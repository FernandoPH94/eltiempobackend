package es.backend.eltiempo.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.backend.eltiempo.model.Municipios;

public class MunicipiosDaoImpl implements MunicipiosDao{
    public List<Municipios> codigoMunicipios(){
        List<Municipios> listamunicipios = new ArrayList<Municipios>();
        File jsonFile = Paths.get("src", "main", "resources", "Json","municipios.json").toFile();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(jsonFile);
            String value;
            String value2;
            for (JsonNode item : root.path("dic20")) {
                value = item.path("CMUN").toString().replace('"',' ').replace(" ","");
                value2 = item.path("CPRO").toString().replace('"',' ').replace(" ","");
                listamunicipios.add(new Municipios(item.path("NOMBRE ").toString().replace('"',' ').replace(" ",""),Integer.parseInt(value),Integer.parseInt(value2)));
            }
            
            return listamunicipios;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}

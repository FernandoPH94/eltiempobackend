package es.backend.eltiempo.data;

import es.backend.eltiempo.model.Provincias;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProvinciasDaoImpl implements ProvinciasDao {
    
    public List<Provincias> getProvincias(){
        List<Provincias> listaProvincias = new ArrayList<Provincias>();
        File jsonFile = Paths.get("src", "main", "resources", "Json","provincias.json").toFile();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(jsonFile);
            String value;
            for (JsonNode item : root.path("dic20")) {
                value = item.path("Cod").toString().replace('"',' ').replace(" ","");
                listaProvincias.add(new Provincias(item.path("Nombre").toString().replace('"',' ').replace(" ",""),Integer.parseInt(value)));
            }
            return listaProvincias;
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

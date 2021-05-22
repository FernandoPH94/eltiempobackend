package es.backend.eltiempo.data;

import org.springframework.web.client.RestTemplate;

import es.backend.eltiempo.model.Temperatura;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;

public class WeatherTodayDaoImpl implements WeatherTodayDao {

    private String apikey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZXJuYW5kb3RyYWJham9waEBob3RtYWlsLmNvbSIsImp0aSI6IjcwY2RlMzlmLTcyMzctNDM0OC05YWMyLTZjNjYxZGM4N2M1YiIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNTkwMzQ4OTI1LCJ1c2VySWQiOiI3MGNkZTM5Zi03MjM3LTQzNDgtOWFjMi02YzY2MWRjODdjNWIiLCJyb2xlIjoiIn0.cIleEWvDZKZH2UUQjOwKFGzQIK9B0aBbs5FIETInjkk";

    public List<Temperatura> weatherRequest(String http, String id){
        LocalDate localDate = LocalDate.now();    
        /*
        File jsonFile;
        Path path = Paths.get("src/main/resources/Json/JsonDataWeather/"+id+"_"+localDate+".json");
        if(Files.notExists(path)){
            createWeatherFile(http,id, localDate);
        }
        jsonFile = Paths.get("src", "main", "resources", "Json","JsonDataWeather",id+"_"+localDate+".json").toFile();
        */
        //Quitar esto cuando se pueda
        RestTemplate restTemplate = new RestTemplate(); 
        ResponseEntity<String> response = restTemplate.getForEntity(http+id+"?api_key="+apikey, String.class);
        // Hasta aquí
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        int value;
        int value1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        List<Temperatura> temperaturas = new ArrayList<Temperatura>();
        try {
            root = mapper.readTree(response.getBody());
            String map = root.path("datos").toString().replace('"',' ').replace(" ","");
            ResponseEntity<String> response2 = restTemplate.getForEntity(map, String.class);
            root = mapper.readTree(response2.getBody());
            //root = mapper.readTree(jsonFile);           
            for (JsonNode item : root.path(0).path("prediccion").path("dia")) {
                value = Integer.parseInt(item.path("temperatura").path("maxima").toString().replace('"',' ').replace(" ",""));
                value1 = Integer.parseInt(item.path("temperatura").path("minima").toString().replace('"',' ').replace(" ",""));
                LocalDate date = LocalDate.parse(item.path("fecha").toString().replace('"',' ').replace(" ",""), formatter);
                String dataday = Integer.toString(date.getDayOfMonth());
                if(dataday.length()<2){
                    dataday = "0"+dataday;
                }
                String dateimport = date.getDayOfWeek()+" - "+dataday;      
                temperaturas.add(new Temperatura(dateimport,value,value1)); 
            }       
            return temperaturas;

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } /*catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }*/
    }

    public String weatherRequestMunicipio(String http, String id){
        
        RestTemplate restTemplate = new RestTemplate(); 
        ResponseEntity<String> response = restTemplate.getForEntity(http+id+"?api_key="+apikey, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        String value;
        try {
            root = mapper.readTree(response.getBody());
            value = root.path(0).path("nombre").toString().replace('"',' ').replace(" ","");
            return value;

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void createWeatherFile(String http, String id, LocalDate localdate){
        RestTemplate restTemplate = new RestTemplate(); 
        ResponseEntity<String> response = restTemplate.getForEntity(http+id+"?api_key="+apikey, String.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("prueba");
        try {
            JsonNode root = mapper.readTree(response.getBody());
            String map = root.path("datos").toString().replace('"',' ').replace(" ","");
            ResponseEntity<String> response2 = restTemplate.getForEntity(map, String.class);
            root = mapper.readTree(response2.getBody());
            mapper.writeValue(Paths.get("src/main/resources/Json/JsonDataWeather/"+id+"_"+localdate+".json").toFile(), root);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

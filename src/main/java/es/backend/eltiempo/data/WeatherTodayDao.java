package es.backend.eltiempo.data;

import java.time.LocalDate;
import java.util.List;

import es.backend.eltiempo.model.Temperatura;

public interface WeatherTodayDao {
    public List<Temperatura> weatherRequest(String http, String id);
    public String weatherRequestMunicipio(String http, String id);
    public void createWeatherFile(String http, String id, LocalDate localdate);
}

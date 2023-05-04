package hr.kbratko.iisclient.service;

import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import java.util.List;

public interface WeatherService {

  List<City> getAllCities();

  CityWithWeather getWeatherByCityName(String name);

}

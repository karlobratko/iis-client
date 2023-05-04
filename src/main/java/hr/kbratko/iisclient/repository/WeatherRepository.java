package hr.kbratko.iisclient.repository;

import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

  List<City> fetchAllCities();

  Optional<CityWithWeather> fetchCityWithWeatherByName(String name);

}

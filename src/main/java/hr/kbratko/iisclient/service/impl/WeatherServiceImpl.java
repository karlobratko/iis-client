package hr.kbratko.iisclient.service.impl;

import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import hr.kbratko.iisclient.repository.WeatherRepository;
import hr.kbratko.iisclient.service.WeatherService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

  private final WeatherRepository weatherRepository;

  @Override
  public List<City> getAllCities() {
    return weatherRepository.fetchAllCities();
  }

  @Override
  public CityWithWeather getWeatherByCityName(String name) {
    return weatherRepository.fetchCityWithWeatherByName(name).orElse(null);
  }

}

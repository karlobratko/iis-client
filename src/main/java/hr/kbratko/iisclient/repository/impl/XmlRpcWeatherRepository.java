package hr.kbratko.iisclient.repository.impl;

import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import hr.kbratko.iisclient.repository.WeatherRepository;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class XmlRpcWeatherRepository implements WeatherRepository {

  private XmlRpcClient xmlRpcClient() throws MalformedURLException, URISyntaxException {
    val config = new XmlRpcClientConfigImpl();

    config.setServerURL(new URI("http://localhost:7270").toURL());
    config.setContentLengthOptional(false);
    config.setEnabledForExtensions(true);

    val xmlRpcClient = new XmlRpcClient();
    xmlRpcClient.setConfig(config);

    return xmlRpcClient;
  }

  @Override
  public List<City> fetchAllCities() {
    val params = new Object[]{};
    Object[] response = new Object[0];
    try {
      response = (Object[]) xmlRpcClient().execute("Dhmz.getAllCities", params);
    } catch (MalformedURLException | URISyntaxException | XmlRpcException e) {
      throw new RuntimeException(e);
    }

    return Arrays.stream(response)
      .map(obj -> (City) obj)
      .toList();
  }

  @Override
  public Optional<CityWithWeather> fetchCityWithWeatherByName(String name) {
    val params = new Object[]{name};
    Object response = null;
    try {
      response = xmlRpcClient().execute("Dhmz.getCityByName", params);
    } catch (XmlRpcException | MalformedURLException | URISyntaxException e) {
      throw new RuntimeException(e);
    }

    return Objects.nonNull(response)
      ? Optional.of((CityWithWeather) response)
      : Optional.empty();
  }
}

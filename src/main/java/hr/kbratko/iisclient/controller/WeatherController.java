package hr.kbratko.iisclient.controller;

import hr.kbratko.iisclient.constants.ApplicationConstants;
import hr.kbratko.iisclient.service.WeatherService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(WeatherController.Mappings.requestMapping)
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;

  @GetMapping(Mappings.indexGetMapping)
  public String index(Model model) {
    model.addAttribute(ModelAttributes.citiesModelAttribute, weatherService.getAllCities());

    return Templates.indexTemplate;
  }

  @GetMapping(Mappings.detailsGetMapping)
  public String details(@PathVariable String name, Model model) {
    model.addAttribute(ModelAttributes.weatherModelAttribute, weatherService.getWeatherByCityName(name));

    return Templates.detailsTemplate;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Mappings {

    public static final String requestMapping = ApplicationConstants.applicationRequestMapping + "/weather";

    public static final String indexGetMapping = "";

    public static final String fullIndexGetMapping = requestMapping + indexGetMapping;

    public static final String detailsGetMapping = "/details/{name}";

    public static final String fullDetailsGetMapping = requestMapping + detailsGetMapping;

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Templates {
    public static final String indexTemplate = "weather/index";

    public static final String detailsTemplate = "weather/details";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ModelAttributes {

    public static final String citiesModelAttribute = "cities";

    public static final String weatherModelAttribute = "weather";

  }

}

package hr.kbratko.iisclient.controller;

import hr.kbratko.iisclient.constants.ApplicationConstants;
import hr.kbratko.iisclient.service.CountryService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CountryController.Mappings.requestMapping)
@RequiredArgsConstructor
public class CountryController {

  private final CountryService countryService;

  @GetMapping(Mappings.indexGetMapping)
  public String index(Model model) {
    model.addAttribute(ModelAttributes.countriesModelAttribute, countryService.getAllCountries());
    return Templates.indexTemplate;
  }

  @GetMapping(Mappings.detailsGetMapping)
  public String details(@PathVariable String name, Model model) {
    model.addAttribute(ModelAttributes.countryModelAttribute, countryService.getCountryByName(name));

    return Templates.detailsTemplate;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Mappings {

    public static final String requestMapping = ApplicationConstants.applicationRequestMapping + "/countries";

    public static final String indexGetMapping = "";

    public static final String fullIndexGetMapping = requestMapping + indexGetMapping;

    public static final String detailsGetMapping = "/details/{name}";

    public static final String fullDetailsGetMapping = requestMapping + detailsGetMapping;

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Templates {

    public static final String indexTemplate = "countries/index";

    public static final String detailsTemplate = "countries/details";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ModelAttributes {

    public static final String countriesModelAttribute = "countries";

    public static final String countryModelAttribute = "country";

  }

}
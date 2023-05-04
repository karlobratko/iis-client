package hr.kbratko.iisclient.service;

import hr.kbratko.iisclient.model.wsdl.Country;
import java.util.List;

public interface CountryService {

  List<Country> getAllCountries();

  Country getCountryByName(String name);

}

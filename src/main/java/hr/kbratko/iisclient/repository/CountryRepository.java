package hr.kbratko.iisclient.repository;

import hr.kbratko.iisclient.model.wsdl.Country;
import java.util.List;
import java.util.Optional;

public interface CountryRepository {

  List<Country> findAll();

  Optional<Country> findCountryByName(String name);

}

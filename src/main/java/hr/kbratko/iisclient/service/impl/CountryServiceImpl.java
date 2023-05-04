package hr.kbratko.iisclient.service.impl;

import hr.kbratko.iisclient.model.wsdl.Country;
import hr.kbratko.iisclient.repository.CountryRepository;
import hr.kbratko.iisclient.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;

  @Override
  public List<Country> getAllCountries() {
    return countryRepository.findAll();
  }

  public Country getCountryByName(String name) {
    return countryRepository.findCountryByName(name).orElse(null);
  }

}
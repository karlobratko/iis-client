package hr.kbratko.iisclient.repository.impl;

import hr.kbratko.iisclient.model.wsdl.Country;
import hr.kbratko.iisclient.model.wsdl.GetCountriesRequest;
import hr.kbratko.iisclient.model.wsdl.GetCountriesResponse;
import hr.kbratko.iisclient.model.wsdl.GetCountryRequest;
import hr.kbratko.iisclient.model.wsdl.GetCountryResponse;
import hr.kbratko.iisclient.repository.CountryRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.val;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapCountryRepository extends WebServiceGatewaySupport implements CountryRepository {

  @Override
  public List<Country> findAll() {
    val request = new GetCountriesRequest();

    val response = getWebServiceTemplate()
      .marshalSendAndReceive(
        "http://localhost:8082/ws",
        request,
        new SoapActionCallback("http://www.kbratko.hr/soapcountries/schema/getCountryRequest")
      );

    return ((GetCountriesResponse) response).getCountries().getCountry();
  }

  @Override
  public Optional<Country> findCountryByName(String name) {
    val request = GetCountryRequest.from(name);

    val response = getWebServiceTemplate()
      .marshalSendAndReceive(
        "http://localhost:8082/ws",
        request,
        new SoapActionCallback("http://www.kbratko.hr/soapcountries/schema/getCountryRequest")
      );

    return Objects.nonNull(response)
      ? Optional.of(((GetCountryResponse) response).getCountry())
      : Optional.empty();
  }

}
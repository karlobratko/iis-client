package hr.kbratko.iisclient.config;

import hr.kbratko.iisclient.model.wsdl.ObjectFactory;
import hr.kbratko.iisclient.repository.impl.SoapCountryRepository;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapCountriesConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller() {
    val marshaller = new Jaxb2Marshaller();
    marshaller.setClassesToBeBound(ObjectFactory.class);

    return marshaller;
  }

  @Bean
  public SoapCountryRepository soapCountryRepository(Jaxb2Marshaller marshaller) {
    val client = new SoapCountryRepository();
    client.setDefaultUri("http://localhost:8082/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}

package hr.kbratko.iisclient;

import hr.kbratko.iisclient.model.wsdl.Country;
import hr.kbratko.iisclient.service.CountryService;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IisClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(IisClientApplication.class, args);
  }

  @Bean
  CommandLineRunner lookup(CountryService quoteClient) {
    return args -> {
      String country = "Spain";

      if (args.length > 0) {
        country = args[0];
      }
      List<Country> response = quoteClient.getAllCountries();
      response.forEach(x -> System.out.println(x.getCapital()));
    };
  }

}

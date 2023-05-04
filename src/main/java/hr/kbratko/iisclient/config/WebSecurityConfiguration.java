package hr.kbratko.iisclient.config;

import hr.kbratko.iisclient.controller.HomeController;
import hr.kbratko.iisclient.controller.RecipeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf()
      .and()
      .authorizeHttpRequests()
      .requestMatchers("/recipes/**").authenticated()
      .requestMatchers("/webjars/**", "/resources/**", "/css/**").permitAll()
      .anyRequest().permitAll()
      .and()
      .formLogin()
      .loginPage(RecipeController.Mappings.fullLoginGetMapping).permitAll()
      .loginProcessingUrl(RecipeController.Mappings.fullLoginGetMapping).permitAll()
      .defaultSuccessUrl(RecipeController.Mappings.fullIndexGetMapping)
      .and()
      .logout()
      .logoutUrl(RecipeController.Mappings.fullLogoutPostMapping)
      .logoutSuccessUrl(HomeController.Mappings.fullIndexGetMapping)
      .and()
      .build();
  }

}


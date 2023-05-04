package hr.kbratko.iisclient.config;

import hr.kbratko.iisclient.converter.AuthenticationResponseDtoToAuthenticationConverter;
import hr.kbratko.iisclient.converter.CautionResponseDtoToCautionConverter;
import hr.kbratko.iisclient.converter.DietLabelResponseDtoToDietLabelConverter;
import hr.kbratko.iisclient.converter.FoodResponseDtoToFoodConverter;
import hr.kbratko.iisclient.converter.HealthLabelResponseDtoToHealthLabelConverter;
import hr.kbratko.iisclient.converter.IngredientResponseDtoToIngredientConverter;
import hr.kbratko.iisclient.converter.RecipeResponseDtoToRecipeConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry formatterRegistry) {
    val authConverter = new AuthenticationResponseDtoToAuthenticationConverter();
    val foodConverter = new FoodResponseDtoToFoodConverter();
    val dietLabelConverter = new DietLabelResponseDtoToDietLabelConverter();
    val healthLabelConverter = new HealthLabelResponseDtoToHealthLabelConverter();
    val cautionConverter = new CautionResponseDtoToCautionConverter();
    val ingredientConverter = new IngredientResponseDtoToIngredientConverter(foodConverter);
    val recipeConverter = new RecipeResponseDtoToRecipeConverter(cautionConverter, dietLabelConverter, healthLabelConverter, ingredientConverter);

    formatterRegistry.addConverter(authConverter);
    formatterRegistry.addConverter(foodConverter);
    formatterRegistry.addConverter(dietLabelConverter);
    formatterRegistry.addConverter(healthLabelConverter);
    formatterRegistry.addConverter(cautionConverter);
    formatterRegistry.addConverter(ingredientConverter);
    formatterRegistry.addConverter(recipeConverter);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
      .addResourceLocations(Constants.classpathResourceLocations);
    registry.addResourceHandler("/webjars/**")
      .addResourceLocations("/webjars/")
      .resourceChain(false);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    private static final String[] classpathResourceLocations = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

  }

}

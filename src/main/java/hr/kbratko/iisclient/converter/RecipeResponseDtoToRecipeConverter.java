package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.RecipeResponseDto;
import hr.kbratko.iisclient.model.Recipe;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeResponseDtoToRecipeConverter implements Converter<RecipeResponseDto, Recipe> {

  private final CautionResponseDtoToCautionConverter cautionConverter;

  private final DietLabelResponseDtoToDietLabelConverter dietLabelConverter;

  private final HealthLabelResponseDtoToHealthLabelConverter healthLabelConverter;

  private final IngredientResponseDtoToIngredientConverter ingredientConverter;

  @Override
  public Recipe convert(@NonNull RecipeResponseDto source) {
    return Recipe.builder()
      .id(source.id())
      .calories(source.calories())
      .source(source.source())
      .url(source.url())
      .title(source.title())
      .prepTime(source.prepTime())
      .cautions(source.cautions().stream().map(cautionConverter::convert).collect(Collectors.toUnmodifiableSet()))
      .dietLabels(source.dietLabels().stream().map(dietLabelConverter::convert).collect(Collectors.toUnmodifiableSet()))
      .healthLabels(source.healthLabels().stream().map(healthLabelConverter::convert).collect(Collectors.toUnmodifiableSet()))
      .ingredients(source.ingredients().stream().map(ingredientConverter::convert).collect(Collectors.toUnmodifiableSet()))
      .build();
  }

}

package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.IngredientResponseDto;
import hr.kbratko.iisclient.model.Ingredient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientResponseDtoToIngredientConverter implements Converter<IngredientResponseDto, Ingredient> {

  private final FoodResponseDtoToFoodConverter foodConverter;

  @Override
  public @NonNull Ingredient convert(@NonNull IngredientResponseDto source) {
    return Ingredient.builder()
      .id(source.id())
      .measure(source.measure())
      .quantity(source.quantity())
      .food(foodConverter.convert(source.food()))
      .build();
  }

}
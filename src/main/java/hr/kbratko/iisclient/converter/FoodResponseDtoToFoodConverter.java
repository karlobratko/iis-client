package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.FoodResponseDto;
import hr.kbratko.iisclient.model.Food;
import hr.kbratko.iisclient.model.FoodCategory;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FoodResponseDtoToFoodConverter implements Converter<FoodResponseDto, Food> {

  @Override
  public @NonNull Food convert(@NonNull FoodResponseDto source) {
    return Food.builder()
      .id(source.id())
      .name(source.name())
      .category(FoodCategory.valueOf(source.category()))
      .build();
  }

}

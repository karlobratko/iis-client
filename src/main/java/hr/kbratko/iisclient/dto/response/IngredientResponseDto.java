package hr.kbratko.iisclient.dto.response;

import java.math.BigDecimal;

public record IngredientResponseDto(
  Long id,
  FoodResponseDto food,
  BigDecimal quantity,
  String measure
) {
}

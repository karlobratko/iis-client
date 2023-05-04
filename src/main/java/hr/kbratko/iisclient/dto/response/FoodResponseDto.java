package hr.kbratko.iisclient.dto.response;

public record FoodResponseDto(
  Long id,
  String name,
  String category
) {
}

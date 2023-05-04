package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.DietLabelResponseDto;
import hr.kbratko.iisclient.model.DietLabel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DietLabelResponseDtoToDietLabelConverter implements Converter<DietLabelResponseDto, DietLabel> {

  @Override
  public @NonNull DietLabel convert(@NonNull DietLabelResponseDto source) {
    return DietLabel.builder()
      .id(source.id())
      .name(source.name())
      .build();
  }

}

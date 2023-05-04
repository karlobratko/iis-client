package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.HealthLabelResponseDto;
import hr.kbratko.iisclient.model.HealthLabel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HealthLabelResponseDtoToHealthLabelConverter implements Converter<HealthLabelResponseDto, HealthLabel> {

  @Override
  public @NonNull HealthLabel convert(@NonNull HealthLabelResponseDto source) {
    return HealthLabel.builder()
      .id(source.id())
      .name(source.name())
      .build();
  }

}

package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.CautionResponseDto;
import hr.kbratko.iisclient.model.Caution;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CautionResponseDtoToCautionConverter implements Converter<CautionResponseDto, Caution> {

  @Override
  public @NonNull Caution convert(@NonNull CautionResponseDto source) {
    return Caution.builder()
      .id(source.id())
      .name(source.name())
      .build();
  }

}

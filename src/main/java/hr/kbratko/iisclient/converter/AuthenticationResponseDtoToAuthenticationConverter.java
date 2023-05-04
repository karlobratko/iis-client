package hr.kbratko.iisclient.converter;

import hr.kbratko.iisclient.dto.response.AuthenticationResponseDto;
import hr.kbratko.iisclient.model.Authentication;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationResponseDtoToAuthenticationConverter implements Converter<AuthenticationResponseDto, Authentication> {

  @Override
  public Authentication convert(@NonNull AuthenticationResponseDto source) {
    return new Authentication(source.token());
  }

}

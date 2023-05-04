package hr.kbratko.iisclient.dto.request;

import lombok.Builder;

@Builder
public record LoginRequestDto(
  String username,
  String password
) {

  public static LoginRequestDto of(String username, String password) {
    return LoginRequestDto.builder()
      .username(username)
      .password(password)
      .build();
  }

}

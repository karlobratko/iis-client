package hr.kbratko.iisclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Authentication {

  private @NonNull String accessToken;

}

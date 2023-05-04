package hr.kbratko.iisclient.service.impl;

import hr.kbratko.iisclient.repository.RecipeRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service("authenticationProvider")
@RequiredArgsConstructor
public class RecipeRestApiAuthenticationService implements AuthenticationProvider {

  private final RecipeRepository recipeRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    val username = authentication.getName();
    val password = authentication.getCredentials().toString();

    val auth = recipeRepository.login(username, password);
    if (auth.isEmpty())
      throw new AuthenticationServiceException("Invalid credentials.");

    return new UsernamePasswordAuthenticationToken(auth.get().getAccessToken(), null, Collections.emptyList());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}

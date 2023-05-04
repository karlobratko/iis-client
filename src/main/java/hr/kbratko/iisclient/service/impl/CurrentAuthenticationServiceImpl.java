package hr.kbratko.iisclient.service.impl;

import hr.kbratko.iisclient.service.CurrentAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentAuthenticationServiceImpl implements CurrentAuthenticationService {

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

}

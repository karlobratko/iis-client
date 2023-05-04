package hr.kbratko.iisclient.service;

import org.springframework.security.core.Authentication;

public interface CurrentAuthenticationService {

  Authentication getAuthentication();

}

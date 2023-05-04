package hr.kbratko.iisclient.controller;

import hr.kbratko.iisclient.constants.ApplicationConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(HomeController.Mappings.requestMapping)
public class HomeController {

  @GetMapping(Mappings.indexGetMapping)
  public String index() {
    return Templates.indexTemplate;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Mappings {

    public static final String requestMapping = ApplicationConstants.applicationRequestMapping;

    public static final String indexGetMapping = "/";

    public static final String fullIndexGetMapping = requestMapping + indexGetMapping;

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Templates {

    public static final String indexTemplate = "home/index";

  }

}
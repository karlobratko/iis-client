package hr.kbratko.iisclient.controller;

import hr.kbratko.iisclient.constants.ApplicationConstants;
import hr.kbratko.iisclient.service.RecipeService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(RecipeController.Mappings.requestMapping)
@RequiredArgsConstructor
public class RecipeController {

  private final RecipeService recipeService;

  @GetMapping(Mappings.loginGetMapping)
  public String login() {
    return Templates.loginTemplate;
  }

  @GetMapping(Mappings.indexGetMapping)
  public String index(Model model) {
    model.addAttribute(ModelAttributes.recipesModelAttribute, recipeService.getAllRecipes());

    return Templates.indexTemplate;
  }

  @GetMapping(Mappings.detailsGetMapping)
  public String details(@PathVariable Long id, Model model) {
    model.addAttribute(ModelAttributes.recipeModelAttribute, recipeService.getRecipeById(id));

    return Templates.detailsTemplate;
  }

  @GetMapping(Mappings.createRecipeGetMapping)
  public String createRecipe() {
    return Templates.createTemplate;
  }

  @PostMapping(Mappings.createRecipeWithXmlAndValidateWithXsdPostMapping)
  public String createRecipeWithXmlAndValidateWithXsd(@RequestParam MultipartFile file) {
    recipeService.createRecipeWithXmlAndValidateWithXsd(file);

    return "redirect:/recipes";
  }

  @PostMapping(Mappings.createRecipeWithXmlAndValidateWithRngPostMapping)
  public String createRecipeWithXmlAndValidateWithRng(@RequestParam MultipartFile file) {
    recipeService.createRecipeWithXmlAndValidateWithRng(file);

    return "redirect:/recipes";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Mappings {

    public static final String requestMapping = ApplicationConstants.applicationRequestMapping + "/recipes";

    public static final String loginGetMapping = "/login";

    public static final String fullLoginGetMapping = requestMapping + loginGetMapping;

    public static final String indexGetMapping = "";

    public static final String fullIndexGetMapping = requestMapping + indexGetMapping;

    public static final String logoutPostMapping = "/logout";

    public static final String fullLogoutPostMapping = requestMapping + logoutPostMapping;

    public static final String detailsGetMapping = "/details/{id}";

    public static final String fullDetailsGetMapping = requestMapping + detailsGetMapping;

    public static final String createRecipeGetMapping = "/create";

    public static final String fullCreateRecipeGetMapping = requestMapping + createRecipeGetMapping;

    public static final String createRecipeWithXmlAndValidateWithXsdPostMapping = "/create-xml-validate-xsd";

    public static final String fullCreateRecipeWithXmlAndValidateWithXsdPostMapping = requestMapping + createRecipeWithXmlAndValidateWithXsdPostMapping;

    public static final String createRecipeWithXmlAndValidateWithRngPostMapping = "/create-xml-validate-rng";

    public static final String fullCreateRecipeWithXmlAndValidateWithRngPostMapping = requestMapping + createRecipeWithXmlAndValidateWithRngPostMapping;

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Templates {

    public static final String loginTemplate = "recipes/login";

    public static final String indexTemplate = "recipes/index";

    public static final String detailsTemplate = "recipes/details";

    public static final String createTemplate = "recipes/create";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ModelAttributes {

    public static final String recipesModelAttribute = "recipes";

    public static final String recipeModelAttribute = "recipe";

  }

}

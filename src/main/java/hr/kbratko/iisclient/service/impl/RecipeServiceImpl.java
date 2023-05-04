package hr.kbratko.iisclient.service.impl;

import hr.kbratko.iisclient.model.Recipe;
import hr.kbratko.iisclient.repository.RecipeRepository;
import hr.kbratko.iisclient.service.RecipeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  @Override
  public List<Recipe> getAllRecipes() {
    return recipeRepository.fetchAll();
  }

  @Override
  public Recipe getRecipeById(Long id) {
    return recipeRepository.fetchById(id).orElse(null);
  }

  @Override
  public Recipe createRecipeWithXmlAndValidateWithXsd(MultipartFile file) {
    return recipeRepository.saveXmlAndValidateWithXsd(file).orElse(null);
  }

  @Override
  public Recipe createRecipeWithXmlAndValidateWithRng(MultipartFile file) {
    return recipeRepository.saveXmlAndValidateWithRng(file).orElse(null);
  }

}

package hr.kbratko.iisclient.service;

import hr.kbratko.iisclient.model.Recipe;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface RecipeService {

  List<Recipe> getAllRecipes();

  Recipe getRecipeById(Long id);

  Recipe createRecipeWithXmlAndValidateWithXsd(MultipartFile file);

  Recipe createRecipeWithXmlAndValidateWithRng(MultipartFile file);

}

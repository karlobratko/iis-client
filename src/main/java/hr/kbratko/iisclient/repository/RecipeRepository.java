package hr.kbratko.iisclient.repository;

import hr.kbratko.iisclient.model.Authentication;
import hr.kbratko.iisclient.model.Recipe;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface RecipeRepository {

  Optional<Authentication> login(String username, String password);

  List<Recipe> fetchAll();

  Optional<Recipe> fetchById(Long id);

  Optional<Recipe> saveXmlAndValidateWithXsd(MultipartFile file);

  Optional<Recipe> saveXmlAndValidateWithRng(MultipartFile file);

}

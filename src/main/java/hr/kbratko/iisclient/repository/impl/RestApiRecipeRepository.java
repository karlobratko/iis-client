package hr.kbratko.iisclient.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.kbratko.iisclient.dto.request.LoginRequestDto;
import hr.kbratko.iisclient.dto.response.ApiResponse;
import hr.kbratko.iisclient.dto.response.AuthenticationResponseDto;
import hr.kbratko.iisclient.dto.response.RecipeResponseDto;
import hr.kbratko.iisclient.model.Authentication;
import hr.kbratko.iisclient.model.Recipe;
import hr.kbratko.iisclient.repository.RecipeRepository;
import hr.kbratko.iisclient.service.CurrentAuthenticationService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RestApiRecipeRepository implements RecipeRepository {

  private final ObjectMapper objectMapper;

  private final ConversionService conversionService;

  private final CurrentAuthenticationService currentAuthenticationService;

  private WebClient authWebClient() {
    return WebClient.builder()
      .baseUrl("http://localhost:8080/api/v1/user-management/auth")
      .build();
  }

  private WebClient recipesWebClient() {
    return WebClient.builder()
      .baseUrl("http://localhost:8080/api/v1/recipe-management/recipes")
      .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + currentAuthenticationService.getAuthentication().getName())
      .build();
  }

  @Override
  public Optional<Authentication> login(String username, String password) {
    return authWebClient().post()
      .uri("/login")
      .bodyValue(LoginRequestDto.of(username, password))
      .exchangeToMono(clientResponse ->
        clientResponse.statusCode().equals(HttpStatus.OK)
          ? clientResponse.bodyToMono(Object.class)
          : Mono.empty()
      )
      .blockOptional()
      .map(it ->
        conversionService.convert(
          objectMapper.convertValue(it, new TypeReference<ApiResponse<AuthenticationResponseDto>>() {
          }).data(),
          Authentication.class
        )
      );
  }

  @Override
  public List<Recipe> fetchAll() {
    return recipesWebClient().get()
      .retrieve()
      .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<RecipeResponseDto>>>() {
      })
      .blockOptional()
      .map(it ->
        it.data().stream()
          .map(recipe -> conversionService.convert(recipe, Recipe.class))
          .toList()
      )
      .orElse(Collections.emptyList());
  }

  @Override
  public Optional<Recipe> fetchById(Long id) {
    return recipesWebClient().get()
      .uri("/%d".formatted(id))
      .exchangeToMono(clientResponse ->
        clientResponse.statusCode().equals(HttpStatus.OK)
          ? clientResponse.bodyToMono(Object.class)
          : Mono.empty()
      )
      .blockOptional()
      .map(it ->
        conversionService.convert(
          objectMapper.convertValue(it, new TypeReference<ApiResponse<RecipeResponseDto>>() {
          }).data(),
          Recipe.class
        )
      );
  }

  @Override
  public Optional<Recipe> saveXmlAndValidateWithXsd(MultipartFile file) {
    val bodyBuilder = new MultipartBodyBuilder();
    bodyBuilder.part("file", file.getResource());
    return recipesWebClient().post()
      .uri("/xsd")
      .contentType(MediaType.MULTIPART_FORM_DATA)
      .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
      .exchangeToMono(clientResponse ->
        clientResponse.statusCode().equals(HttpStatus.OK)
          ? clientResponse.bodyToMono(Object.class)
          : Mono.empty()
      )
      .blockOptional()
      .map(it ->
        conversionService.convert(
          objectMapper.convertValue(it, new TypeReference<ApiResponse<RecipeResponseDto>>() {
          }).data(),
          Recipe.class
        )
      );
  }

  @Override
  public Optional<Recipe> saveXmlAndValidateWithRng(MultipartFile file) {
    val bodyBuilder = new MultipartBodyBuilder();
    bodyBuilder.part("file", file.getResource());
    return recipesWebClient().post()
      .uri("/rng")
      .contentType(MediaType.MULTIPART_FORM_DATA)
      .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
      .exchangeToMono(clientResponse ->
        clientResponse.statusCode().equals(HttpStatus.OK)
          ? clientResponse.bodyToMono(Object.class)
          : Mono.empty()
      )
      .blockOptional()
      .map(it ->
        conversionService.convert(
          objectMapper.convertValue(it, new TypeReference<ApiResponse<RecipeResponseDto>>() {
          }).data(),
          Recipe.class
        )
      );
  }

}

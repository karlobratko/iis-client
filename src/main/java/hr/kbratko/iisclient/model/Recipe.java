package hr.kbratko.iisclient.model;

import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true)
@ToString(doNotUseGetters = true)
public class Recipe {

  private @NonNull Long id;

  private @NonNull String title;

  private @NonNull String source;

  private @NonNull String url;

  private @NonNull BigDecimal calories;

  private @NonNull BigDecimal prepTime;

  @ToString.Exclude
  private Set<DietLabel> dietLabels;

  @ToString.Exclude
  private Set<HealthLabel> healthLabels;

  @ToString.Exclude
  private Set<Caution> cautions;

  @ToString.Exclude
  private Set<Ingredient> ingredients;

}

// -- # REPO
package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.CulinaryRecipes;

@Repository
public interface CulinaryRecipesRepo extends JpaRepository<CulinaryRecipes, Long> {}
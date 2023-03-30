package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.GoodsRating;
import pl.arkani.LZ_2022301_LX.model.Movie;

@Repository
public interface GoodsRatingRepo extends JpaRepository<GoodsRating, Long> {}

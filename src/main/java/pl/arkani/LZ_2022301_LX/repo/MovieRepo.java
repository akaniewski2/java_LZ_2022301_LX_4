package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.MisiaSays;
import pl.arkani.LZ_2022301_LX.model.Movie;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT s FROM Movie s ORDER BY category desc,id")
    List<Movie> findAll();
}

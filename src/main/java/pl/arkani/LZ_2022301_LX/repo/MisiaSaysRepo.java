package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.MisiaSays;
import pl.arkani.LZ_2022301_LX.model.Music;
import pl.arkani.LZ_2022301_LX.model.Test;

import java.util.List;

@Repository
public interface MisiaSaysRepo extends JpaRepository<MisiaSays, Long> {

    @Query(value = "SELECT s FROM MisiaSays s ORDER BY age")
    List<MisiaSays> findAll();
}

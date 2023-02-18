package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Movie;
import pl.arkani.LZ_2022301_LX.model.Music;
import pl.arkani.LZ_2022301_LX.model.TvRemote;

import java.util.List;

@Repository
public interface MusicRepo extends JpaRepository<Music, Long> {
    @Query(value = "SELECT s FROM Music s ORDER BY author nulls last,publishDate nulls last ,album")
    List<Music> findAll();

}

package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.TvChannel;
import pl.arkani.LZ_2022301_LX.model.TvRemote;

import java.util.List;

@Repository
public interface TvRemoteRepo extends JpaRepository<TvRemote,Integer> {

    @Query(value = "SELECT s FROM TvRemote s ORDER BY block,c_in_block")
    List<TvRemote> findAll();

    List<TvRemote> findByName(String n);

}

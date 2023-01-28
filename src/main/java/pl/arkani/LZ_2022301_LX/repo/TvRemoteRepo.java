package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.TvChannel;
import pl.arkani.LZ_2022301_LX.model.TvRemote;

import java.util.List;

@Repository
public interface TvRemoteRepo extends CrudRepository<TvRemote,Integer> {

    List<TvRemote> findByName(String n);

}

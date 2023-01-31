package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.TvChannel;

import java.util.List;

@Repository
public interface TvChannelRepo extends JpaRepository<TvChannel,Integer> {

    List<TvChannel> findByName(String n);



}

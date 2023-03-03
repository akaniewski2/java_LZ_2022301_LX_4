package pl.arkani.LZ_2022301_LX.repo;


import jdk.jfr.consumer.RecordedThread;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechPageRepo extends JpaRepository<TechPage,Long> {




    @Query(value = "SELECT s FROM TechPage s  where activeRow=1 order by orderRow,name")
    List<TechPage> findAll();

    TechPage findByName(String username) ;
}

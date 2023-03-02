package pl.arkani.LZ_2022301_LX.repo;


import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.TechPage;

import java.util.List;

@Repository
public interface TechPageRepo extends JpaRepository<TechPage,Long> {


    @Query(value = "SELECT s FROM TechPage s  where activeRow=1 order by orderRow,name")
    List<TechPage> findAll();


}

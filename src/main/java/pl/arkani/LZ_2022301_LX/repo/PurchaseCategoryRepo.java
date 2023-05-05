package pl.arkani.LZ_2022301_LX.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;
import pl.arkani.LZ_2022301_LX.model.TechPage;

import java.util.List;

@Repository
public interface PurchaseCategoryRepo extends JpaRepository<PurchaseCategory, Long>{
    @Transactional
    @Modifying
    @Query("update PurchaseCategory p set p.activeRec = 0 where p.id = ?1")
    void updateActiveById(long id);

    @Query(value = "SELECT s FROM PurchaseCategory s  where activeRec=1 order by orderr,name")
    List<PurchaseCategory> findAll();


    PurchaseCategory findByName(String name);
}

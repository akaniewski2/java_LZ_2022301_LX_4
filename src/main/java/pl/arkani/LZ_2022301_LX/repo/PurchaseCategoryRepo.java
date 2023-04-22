package pl.arkani.LZ_2022301_LX.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;

@Repository
public interface PurchaseCategoryRepo extends JpaRepository<PurchaseCategory, Long>{
    @Transactional
    @Modifying
    @Query("update PurchaseCategory p set p.activeRec = 0 where p.id = ?1")
    void updateActiveById(long id);


    PurchaseCategory findByName(String name);
}

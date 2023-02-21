package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.TvRemote;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
    @Query(value = "SELECT s FROM Purchase s where coalesce(s.purchased,'N')='N' ORDER BY category,name,coalesce(addDt,modDt)")
//    @Query(value = "SELECT s.*,FROM_UNIXTIME(addDt ),FROM_UNIXTIME(modDt ) FROM Purchase s where coalesce(s.purchased,'N')='N' ORDER BY category,name,coalesce(addDt,modDt)")
    List<Purchase> findAll();


    Optional<Purchase> findByName(String name) ;
}

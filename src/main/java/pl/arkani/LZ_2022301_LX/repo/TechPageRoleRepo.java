package pl.arkani.LZ_2022301_LX.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.TechPageRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechPageRoleRepo extends JpaRepository<TechPageRole,Long> {


//    private static final String QUERY = "select b from Branch b"
//            + " left join b.filial f"
//            + " where f.id = ?1 and b.id like CONCAT(?2, '%')";
//    @Query(QUERY)
//    List<Branch> findByFilialAndBranchLike(String filialId, String branchCode);
//

//    @Query(value = "SELECT s FROM TechPageRole s  where activeRow=1 order by orderRow,name")
//    List<TechPageRole> findAll();
//
//    TechPage findByName(String name) ;
//
//    @Query(value = "SELECT s FROM TechPageRole s  where activeRow=1 and name=?1 and roles like concat('%',?2,'%')  order by orderRow,name")
//    Optional<TechPage> findByNameAndRole(String name, String role) ;
//
//    @Query(value = "SELECT s FROM TechPageRole s  where activeRow=1 and roles like concat('%',?1,'%')  order by orderRow,name")
//    List<TechPage> findByRole(String role);

}

package pl.arkani.LZ_2022301_LX.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.TechPage;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechPageRepo extends JpaRepository<TechPage,Long> {


//    private static final String QUERY = "select b from Branch b"
//            + " left join b.filial f"
//            + " where f.id = ?1 and b.id like CONCAT(?2, '%')";
//    @Query(QUERY)
//    List<Branch> findByFilialAndBranchLike(String filialId, String branchCode);

//    @Query(SELECT  u.userName FROM  User u
//            INNER JOIN Area a ON a.idUser = u.idUser
//            WHERE
//            a.idArea = :idArea)
//    List<User> findByIdarea(@Param("idArea") Long idArea);
////
//@Query("select t from Test t join User u where u.username = :username")
//List<Test> findAllByUsername(@Param("username")String username);

    @Query(value = "SELECT s FROM TechPage s  where activeRow=1 order by orderRow,name")
    List<TechPage> findAll();

    @Query(value = "SELECT s FROM TechPage s  where activeRow=1 and name=?1")
    TechPage findByName(String name) ;


   @Query(value = """
                     SELECT p.* FROM tech_page p
                     inner join tech_page_tech_page_role w on p.id=w.page_Id
                     inner join tech_page_role r on r.id=w.role_Id
                     where p.active_Row=1 and p.name=?2 and r.method=?1 and  r.role = ?3
                     order by p.order_Row,p.name
           """,nativeQuery = true)
    Optional<TechPage> findByMethodAndNameAndRole( String Method, String name, String role) ;

   @Query(value = """
                    SELECT p.* FROM tech_page p
                    inner join tech_page_tech_page_role w on p.id=w.page_Id
                    inner join tech_page_role r on r.id=w.role_Id
                    where p.active_Row=1  and r.method=?1 and  r.role = ?2   
                    order by p.order_Row,p.name
           """,nativeQuery = true)
    List<TechPage> findByMethodAndRole(String Method,String role);

//    @Query(value = "SELECT s FROM TechPage s  where activeRow=1 and name=?1 and roles like concat('%',?2,'%')  order by orderRow,name")
//    Optional<TechPage> findByNameAndRole(String name, String role) ;
//
//    @Query(value = "SELECT s FROM TechPage s  where activeRow=1 and roles like concat('%',?1,'%')  order by orderRow,name")
//    List<TechPage> findByRole(String role);

}

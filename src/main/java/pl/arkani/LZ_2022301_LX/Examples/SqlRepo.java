package pl.arkani.LZ_2022301_LX.Examples;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SqlRepo {

    @PersistenceContext
    private   EntityManager em;


    @Transactional
    public  List SqlSelectAll(String sqlStatement) {
        //ad.link 3

        String q_tmp = sqlStatement;
        Query q = em.createNativeQuery(q_tmp);

        //System.out.println(q.getResultStream());
        List<Object[]> wynik = q.getResultList();

        //SqlSelectAll("test.KoronawirusZakazeni","*");
/*
        for (Object[] a:wynik){
            System.out.println(("wynik " + a[1] +
                    a[2] +
                    a[3] +
                    a[4] +
                    a[5] +
                    a[6] +
                    a[7]



            ));
        }
        */

        return q.getResultList();

    }
}
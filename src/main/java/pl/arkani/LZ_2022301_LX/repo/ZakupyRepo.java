package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Zakupy;


@Repository
public interface ZakupyRepo extends  JpaRepository<Zakupy,Long> {
}
//public interface Zakupy extends JpaRepository<Zakupy, Long> {}

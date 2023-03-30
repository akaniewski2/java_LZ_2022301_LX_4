package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

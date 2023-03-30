package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.ImportantDates;
import pl.arkani.LZ_2022301_LX.model.Test;

@Repository
public interface ImportantDatesRepo extends JpaRepository<ImportantDates, Long> {}

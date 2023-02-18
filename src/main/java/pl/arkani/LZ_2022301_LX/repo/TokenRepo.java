package pl.arkani.LZ_2022301_LX.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Token;


@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    //todo zmienic na optional
    Token findByValue(String value);

}


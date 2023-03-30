package pl.arkani.LZ_2022301_LX.JAVA_CODE_TEMPLATEST;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.<ClassNm>;

@Repository
public interface <ClassNmRepo> extends JpaRepository<<ClassNm>, Long> {}

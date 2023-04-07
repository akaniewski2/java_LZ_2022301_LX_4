package pl.arkani.LZ_2022301_LX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.arkani.LZ_2022301_LX.model.TvChannel;
import pl.arkani.LZ_2022301_LX.repo.TvChannelRepo;
//@ComponentScan(basePackages = "pl.arkani.LZ_202301_LX")
@SpringBootApplication
public class Lz2022301LxApplication {


	public static void main(String[] args) {
		SpringApplication.run(Lz2022301LxApplication.class, args);


	}


}

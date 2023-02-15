package pl.arkani.LZ_2022301_LX.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"pl.arkani.LZ_2022301_LX"})
public class SpringConfig
{
	@Bean
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource =  new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/arkani_1");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
}
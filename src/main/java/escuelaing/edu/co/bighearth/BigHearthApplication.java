package escuelaing.edu.co.bighearth;

import escuelaing.edu.co.bighearth.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("escuelaing.edu.co.bighearth.repositories")
@EntityScan("escuelaing.edu.co.bighearth.model")
public class BigHearthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigHearthApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/api/*" );

		return registrationBean;
	}
}

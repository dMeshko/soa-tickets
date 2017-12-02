package finki.ukim.mk.soatickets;

import finki.ukim.mk.soatickets.business.services.ISearchService;
import finki.ukim.mk.soatickets.business.services.implementation.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@ComponentScan
@SpringBootApplication
public class SoaTicketsApplication extends SpringBootServletInitializer {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Autowired
	private EntityManager entityManager;

	@Bean
	ISearchService eventsSearchService(){
		SearchService searchService = new SearchService(entityManager);
		searchService.initialize();
		return searchService;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SoaTicketsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SoaTicketsApplication.class, args);
	}
}
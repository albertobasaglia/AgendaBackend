package com.alberto.agenda;

import com.alberto.agenda.entity.TestEntity;
import com.alberto.agenda.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaApplication {
	private static final Logger log = LoggerFactory.getLogger(AgendaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(TestRepository repository) {
		return (args) -> {
			log.info("Listing entities");
			for(TestEntity testEntity: repository.findAll()) {
				log.info(testEntity.toString());
			}
			log.info("Finished listing entities");
		};
	}
}

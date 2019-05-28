package io.pivotal;

import io.pivotal.fortune.FortuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableCircuitBreaker
public class FortuneServiceApplication {

    Logger logger = LoggerFactory
            .getLogger(FortuneServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FortuneServiceApplication.class, args);
    }

    /**
     *
     * Logs the database contents on startup
     *
     * @param fortuneRepo
     * @return
     */
    @Bean
    CommandLineRunner logDatabase(FortuneRepository fortuneRepo) {
        return args -> {
            logger.info("Fortune Repo record count: {}", fortuneRepo.count());
            fortuneRepo.findAll().forEach(x -> logger.debug(x.toString()));
        };

    }

}
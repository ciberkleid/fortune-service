package io.pivotal;

import io.pivotal.fortune.Fortune;
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
@EnableDiscoveryClient
@EnableCircuitBreaker
public class FortuneServiceApplication {

    Logger logger = LoggerFactory
            .getLogger(FortuneServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FortuneServiceApplication.class, args);
    }

    /**
     *
     * Loads the database on startup
     *
     * @param fortuneRepo
     * @return
     */
    @Bean
    CommandLineRunner loadDatabase(FortuneRepository fortuneRepo) {
        return args -> {
            logger.debug("loading database..");
            fortuneRepo.save(new Fortune(1L, "Do what works."));
            fortuneRepo.save(new Fortune(2L, "Do the right thing."));
            fortuneRepo.save(new Fortune(3L, "Always be kind."));
            fortuneRepo.save(new Fortune(4L, "You learn from your mistakes... You will learn a lot today."));
            fortuneRepo.save(new Fortune(5L, "You can always find happiness at work on Friday."));
            fortuneRepo.save(new Fortune(6L, "You will be hungry again in one hour."));
            fortuneRepo.save(new Fortune(7L, "Today will be an awesome day!"));
            logger.info("Fortune Repo record count: {}", fortuneRepo.count());
            fortuneRepo.findAll().forEach(x -> logger.debug(x.toString()));
        };

    }

}

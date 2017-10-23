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
     * Loads the database on startup
     *
     * @param gr
     * @return
     */
    @Bean
    CommandLineRunner loadDatabase(FortuneRepository gr) {
        return args -> {
            logger.debug("loading database..");
            gr.save(new Fortune(1L, "You learn from your mistakes... You will learn a lot today."));
            gr.save(new Fortune(2L, "You can always find happiness at work on Friday."));
            gr.save(new Fortune(3L, "You will be hungry again in one hour."));
            gr.save(new Fortune(4L, "Today will be an awesome day!"));
            logger.debug("record count: {}", gr.count());
            gr.findAll().forEach(x -> logger.debug(x.toString()));
        };

    }

}
package smoke;

import io.pivotal.FortuneServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FortuneServiceApplication.class,
        properties = {"spring.application.name=fortune-service", "spring.cloud.discovery.enabled=false", "spring.cloud.service-registry.auto-registration.enabled=false", "eureka.client.enabled=false", "eureka.client.serviceUrl.registerWithEureka=false", "eureka.client.registerWithEureka=false", "eureka.client.fetchRegistry=false", "spring.cloud.circuit.breaker.enabled=false", "hystrix.stream.queue.enabled=false"}
)
public class DatabaseTests {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void testDefaultSettings() throws Exception {
        assertThat(this.template.queryForObject("SELECT COUNT(*) FROM fortune",
                Integer.class)).isEqualTo(7);
    }

}

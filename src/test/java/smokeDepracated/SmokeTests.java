package smokeDepracated;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmokeTests.class,
		properties = {"spring.application.name=fortune-service", "spring.cloud.discovery.enabled=false", "spring.cloud.service-registry.auto-registration.enabled=false", "eureka.client.enabled=false", "eureka.client.serviceUrl.registerWithEureka=false", "eureka.client.registerWithEureka=false", "eureka.client.fetchRegistry=false", "spring.cloud.circuit.breaker.enabled=false", "hystrix.stream.queue.enabled=false"},
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableAutoConfiguration
public class SmokeTests {

	@Value("${application.url}") String applicationUrl;

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void should_return_a_fortune() {
		ResponseEntity<String> response = this.restTemplate
				.getForEntity("http://" + this.applicationUrl + "/", String.class);

		BDDAssertions.then(response.getStatusCodeValue()).isEqualTo(200);

		// Filter out the known Hystrix fallback response
		BDDAssertions.then(response.getBody()).doesNotContain("The fortuneteller will be back soon.");
	}

}

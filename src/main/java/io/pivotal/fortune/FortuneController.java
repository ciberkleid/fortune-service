package io.pivotal.fortune;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {
	Logger logger = LoggerFactory
			.getLogger(FortuneController.class);
	
	private final FortuneService fortuneService;

	public FortuneController(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@RequestMapping({"/", "/fortune"})
	String getQuote(){
		logger.debug("Fetching fortune");
		return fortuneService.getFortune();
	}
		
	
}

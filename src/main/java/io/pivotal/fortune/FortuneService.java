package io.pivotal.fortune;

import java.util.Random;
import java.util.stream.LongStream;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FortuneService {

	Logger logger = LoggerFactory
			.getLogger(FortuneService.class);

	@Autowired
	FortuneRepository fortuneRepo;

	@HystrixCommand(fallbackMethod = "getDefaultFortune")
	public String getFortune(){

		Fortune fortune = null;
		long numFortunes = fortuneRepo.count();
		logger.debug("There are {} possible fortunes", numFortunes);

		Random random = new Random();
		LongStream randomIds = random.longs(1,1,numFortunes+1);
		long randomId = randomIds.findAny().getAsLong();

		logger.debug("Getting fortune with random id={}", randomId);
		fortune = fortuneRepo.findOne(randomId);
		logger.debug("Got {}", fortune.toString());

		return fortune.getText();
	}


	public String getDefaultFortune(){
		logger.debug("Default fortune used");
		return "The fortuneteller will be back soon.";
	}

}

package io.reflectoring.resilience4j.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringbootResilience4jApplication {

	@Autowired
	private RateLimiterExamplesRunner rateLimiterExamplesRunner;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootResilience4jApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runExamples() {
		rateLimiterExamplesRunner.run();
	}
}
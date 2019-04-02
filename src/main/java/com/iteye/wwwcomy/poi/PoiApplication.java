package com.iteye.wwwcomy.poi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PoiApplication {

	private static final Logger logger = LoggerFactory.getLogger(PoiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		logger.info("hello world, I have just started up");
	}
}

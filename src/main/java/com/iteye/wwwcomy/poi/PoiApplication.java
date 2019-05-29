package com.iteye.wwwcomy.poi;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.iteye.wwwcomy.poi.dao.ExcelReader;
import com.iteye.wwwcomy.poi.dao.WordWriter;

@SpringBootApplication
public class PoiApplication {

	private static final Logger logger = LoggerFactory.getLogger(PoiApplication.class);

	@Autowired
	private WordWriter writer;
	@Autowired
	private ExcelReader reader;

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws Exception {
		logger.info("PoiApplication Started...");
		try {
			List<Map<String, String>> organizations = reader.readToList();
			writer.writeToFile(organizations);
			logger.info("Processed all data successfully.");
		} catch (Exception e) {
			logger.error("Error happens, please ask author... {}", e.getMessage(), e);
		}
		logger.info("To close this window, Press Ctrl+C, auto-close in 30 seconds...");
		Thread.sleep(30 * 1000);
	}
}

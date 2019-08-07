package com.iteye.wwwcomy.poi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.iteye.wwwcomy.poi.dao.ExcelReaderFactory;
import com.iteye.wwwcomy.poi.service.ExcelDataCalculateService;
import com.iteye.wwwcomy.poi.service.ExcelToWordService;
import com.iteye.wwwcomy.poi.service.ILPService;

@SpringBootApplication
public class PoiApplication {

	private static final Logger logger = LoggerFactory.getLogger(PoiApplication.class);

	@Value(value = "${poi.excel.path}")
	public String excelPath;
	@Value(value = "${poi.task2.excel.path}")
	public String task2ExcelPath;

	private Map<String, ILPService> initMap;

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
	}

	@Bean
	public ExcelToWordService initExcelToWordService() throws Exception {
		ExcelToWordService excelToWordService = new ExcelToWordService();
		excelToWordService.setExcelDao(ExcelReaderFactory.getExcelReader(excelPath));
		return excelToWordService;
	}

	@Bean
	public ExcelDataCalculateService initExcelDataCalculateService() throws Exception {
		ExcelDataCalculateService service = new ExcelDataCalculateService();
		service.setExcelDao(ExcelReaderFactory.getExcelReader(task2ExcelPath));
		return service;
	}

	private String doAskForTaskId() {
		printTips();
		Scanner scanner = new Scanner(System.in);
		String taskId = scanner.next();
		while (!initMap.keySet().contains(taskId)) {
			System.out.println("Please choose a valid input!");
			printTips();
			taskId = scanner.next();
		}
		scanner.close();
		return taskId;
	}

	private static void printTips() {
		System.out.println("********** This is the START to choose a task ID **********");
		System.out.println("Input 1 for Excel To Word data transfer");
		System.out.println("Input 2 for Excel To Excel data population");
		System.out.println("Enter your Task Name: ");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void eventDispatch() throws Exception {
		initMap();
		String taskId = doAskForTaskId();
		initMap.get(taskId).doLoveLaoPoService();
		logger.info("To close this window, Press Ctrl+C, auto-close in 30 seconds...");
		Thread.sleep(30 * 1000);
	}

	private void initMap() throws Exception {
		initMap = new HashMap<String, ILPService>();
		initMap.put("1", initExcelToWordService());
		initMap.put("2", initExcelDataCalculateService());
	}
}

package com.iteye.wwwcomy.poi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteye.wwwcomy.poi.dao.ExcelReader;
import com.iteye.wwwcomy.poi.dao.WordWriter;

@Service
public class ExcelToWordService implements ILPService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WordWriter writer;
	@Autowired
	private ExcelReader reader;

	@Override
	public void doLoveLaoPoService() {
		logger.info("PoiApplication Started...");
		try {
			List<Map<String, String>> organizations = reader.readToList();
			writer.writeToFile(organizations);
			logger.info("Processed all data successfully.");
		} catch (Exception e) {
			logger.error("Error happens, please ask author... {}", e.getMessage(), e);
		}
	}

}

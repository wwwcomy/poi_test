package com.iteye.wwwcomy.poi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.iteye.wwwcomy.poi.dao.ExcelDao;
import com.iteye.wwwcomy.poi.dao.WordWriter;

public class ExcelDataCalculateService implements ILPService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ExcelDao excelDao;
	@Value(value = "${poi.excel.sheet.name}")
	public String SHEET_NUM;

	@Override
	public void doLoveLaoPoService() {

	}

}

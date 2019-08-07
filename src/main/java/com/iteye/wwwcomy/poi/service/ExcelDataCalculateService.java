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
	public String sheet1 = "表1";
	public String sheet2 = "表2";

	@Override
	public void doLoveLaoPoService() {
		List<Map<String, String>> sheet1Content = excelDao.readToList(sheet1);
		List<Map<String, String>> sheet2Content = excelDao.readToList(sheet2);
		for (Map<String, String> line : sheet1Content) {

		}
	}

	public void setExcelDao(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}

}

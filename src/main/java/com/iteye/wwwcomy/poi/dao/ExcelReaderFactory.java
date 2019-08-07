package com.iteye.wwwcomy.poi.dao;

public class ExcelReaderFactory {

	public static ExcelDao getExcelReader(String f) throws Exception {
		return new ExcelDao(f);
	}
}

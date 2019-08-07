package com.iteye.wwwcomy.poi.dao;

public class ExcelReaderFactory {

	public static ExcelReader getExcelReader(String f) throws Exception {
		return new ExcelReader(f);
	}
}

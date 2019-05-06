package com.iteye.wwwcomy.poi.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelReaderTest {

	@Autowired
	private ExcelReader reader;

	@Test
	public void canGetRowsAndCols() {
		System.out.println(reader.getWorkBook().getSheet(reader.SHEET_NUM).getLastRowNum());
		System.out.println(reader.getWorkBook().getSheet(reader.SHEET_NUM).getRow(2).getCell(0));
		System.out.println(reader.getWorkBook().getSheet(reader.SHEET_NUM).getRow(2).getLastCellNum());
		System.out.println(reader.readToList());
	}
	
	@Test
	public void canGetChart() {
		reader.readChart();
	}

}
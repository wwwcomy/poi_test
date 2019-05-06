package com.iteye.wwwcomy.poi.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.iteye.wwwcomy.poi.model.Organization;
import com.iteye.wwwcomy.poi.util.ObjectMapperUtil;

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
		System.out.println(ObjectMapperUtil.readValue(ObjectMapperUtil.writeValueAsString(reader.readToList()),
				new TypeReference<List<Organization>>() {
				}));
	}
	
	@Test
	public void canGetChart() {
		reader.readChart();
	}

}
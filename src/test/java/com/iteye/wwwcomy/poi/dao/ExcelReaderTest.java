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
	private ExcelDao reader;

	@Test
	public void canGetRowsAndCols() {
	}

	@Test
	public void canGetChart() {
		reader.readChart();
	}

}
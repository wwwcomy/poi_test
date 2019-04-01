package com.iteye.wwwcomy.poi.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordWriterTest {

	@Autowired
	private ExcelReader reader;
	@Autowired
	private WordWriter writer;

	@Test
	public void canWrite() throws Exception {
		List<Map<String, String>> result = reader.readToList();
		writer.writeToFile(result);
	}

}

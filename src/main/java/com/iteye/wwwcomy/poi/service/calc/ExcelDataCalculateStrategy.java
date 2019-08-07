package com.iteye.wwwcomy.poi.service.calc;

import java.util.List;
import java.util.Map;

public interface ExcelDataCalculateStrategy {

	public String getResult(Map<String, String> currentRowInSheet1, List<Map<String, String>> sheet2Content);
}

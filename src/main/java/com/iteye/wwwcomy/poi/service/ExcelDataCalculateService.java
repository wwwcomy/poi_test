package com.iteye.wwwcomy.poi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iteye.wwwcomy.poi.dao.ExcelDao;
import com.iteye.wwwcomy.poi.service.calc.ExcelDataCalculateStrategy;
import com.iteye.wwwcomy.poi.service.calc.ShiDianFangKuanCalcStrategy;
import com.iteye.wwwcomy.poi.service.calc.Vintage12CalcStrategy;
import com.iteye.wwwcomy.poi.service.calc.Vintage1CalcStrategy;
import com.iteye.wwwcomy.poi.service.calc.Vintage6CalcStrategy;
import com.iteye.wwwcomy.poi.service.calc.VintageCalcStrategy;

public class ExcelDataCalculateService implements ILPService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExcelDao excelDao;
	public String sheet1 = "表1";
	public String sheet2 = "表2";

	private Map<String, ExcelDataCalculateStrategy> typeAndStrategyRegistry = new HashMap<String, ExcelDataCalculateStrategy>();

	public ExcelDataCalculateService() {
		typeAndStrategyRegistry.put("vintage", new VintageCalcStrategy());
		typeAndStrategyRegistry.put("vintage1", new Vintage1CalcStrategy());
		typeAndStrategyRegistry.put("vintage6", new Vintage6CalcStrategy());
		typeAndStrategyRegistry.put("vintage12", new Vintage12CalcStrategy());
		typeAndStrategyRegistry.put("时点放款", new ShiDianFangKuanCalcStrategy());
	}

	@Override
	public void doLoveLaoPoService() {
		List<Map<String, String>> sheet1Content = excelDao.readToList(sheet1);
		List<Map<String, String>> sheet2Content = excelDao.readToList(sheet2);
		for (Map<String, String> line : sheet1Content) {
			if (line.get("助贷码").matches(".*\n.*")) {
				Log.info("Skip Line as it includes more than 1 助贷码");
				continue;
			}
			logger.info("line.get(\"类型\") --> {}",
					typeAndStrategyRegistry.get(line.get("类型")).getResult(line, sheet2Content));
		}
	}

	public void setExcelDao(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}

}

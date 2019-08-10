package com.iteye.wwwcomy.poi.service.calc;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * vintage6 <br>
 * 表1.助贷码=表2.union_code，表1.逾期时间=表2.kpi，表2.入账月份为19年，表2.mob=6，取MAX(表2.放款逾期率）
 * 
 * @author xingnliu
 *
 */
@Service
public class Vintage6CalcStrategy extends VintageCalcStrategy implements ExcelDataCalculateStrategy {

	protected boolean extraLogic(Map<String, String> rowSheet2) {
		return "6".equalsIgnoreCase(rowSheet2.get("mob"));
	}

}

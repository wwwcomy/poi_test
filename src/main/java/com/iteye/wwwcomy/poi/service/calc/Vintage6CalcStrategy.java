package com.iteye.wwwcomy.poi.service.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * vintage6 <br>
 * 表1.助贷码=表2.union_code，表1.逾期时间=表2.kpi，表2.入账月份为19年，表2.mob=6，取MAX(表2.放款逾期率）
 * 
 * @author xingnliu
 *
 */
@Service
public class Vintage6CalcStrategy implements ExcelDataCalculateStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getResult(Map<String, String> currentRowInSheet1, List<Map<String, String>> sheet2Content) {
		String unionCodeSheet1 = currentRowInSheet1.get("助贷码");
		String overdueSheet1 = currentRowInSheet1.get("逾期时间");
		List<Map<String, String>> filteredSheet2Result = filter(unionCodeSheet1, overdueSheet1, sheet2Content);
		double maxLoanOverduePercentage = getMaxLoanOverduePercentage(filteredSheet2Result);
		return String.valueOf(maxLoanOverduePercentage);
	}

	private double getMaxLoanOverduePercentage(List<Map<String, String>> filteredSheet2Result) {
		double maxPercentage = 0.0;
		for (Map<String, String> row : filteredSheet2Result) {
			double tmpLoanOverduePercentage = Double.parseDouble(row.get("放款逾期率"));
			if (tmpLoanOverduePercentage > maxPercentage) {
				maxPercentage = tmpLoanOverduePercentage;
			}
		}
		return maxPercentage;
	}

	private List<Map<String, String>> filter(String unionCodeSheet1, String overdueSheet1,
			List<Map<String, String>> sheet2Content) {
//		logger.info("unionCodeSheet1 as {}, overdueSheet1 as {}", unionCodeSheet1, overdueSheet1);
		if (StringUtils.isBlank(unionCodeSheet1) || StringUtils.isBlank(overdueSheet1)) {
			logger.error("unionCodeSheet1 or overdueSheet1 is blank, returning empty result");
			return Collections.emptyList();
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (Map<String, String> rowSheet2 : sheet2Content) {
			if (unionCodeSheet1.equalsIgnoreCase(rowSheet2.get("union_code"))
					&& overdueSheet1.equalsIgnoreCase(rowSheet2.get("kpi")) && rowSheet2.get("入账月份").endsWith("/19")
					&& "6".equalsIgnoreCase(rowSheet2.get("mob"))) {
				result.add(rowSheet2);
			}
		}
		return result;
	}

}

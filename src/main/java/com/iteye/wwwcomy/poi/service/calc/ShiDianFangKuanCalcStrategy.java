package com.iteye.wwwcomy.poi.service.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * * 时点放款 <br>
 * 表1.助贷码=表2.union_code，表1.逾期时间=表2.kpi，表2.入账月份为19年，
 * 分母：取所有表2.mob=0的表2.放款金额相加，分子：每一个月份分别取表2.mob最大的那行的表2.逾期余额的值，相加，取分子/分母 *
 * 
 * @author xingnliu *
 */
@Service
public class ShiDianFangKuanCalcStrategy implements ExcelDataCalculateStrategy {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getResult(Map<String, String> currentRowInSheet1, List<Map<String, String>> sheet2Content) {
		String unionCodeSheet1 = currentRowInSheet1.get("助贷码");
		String overdueSheet1 = currentRowInSheet1.get("逾期时间");
		List<Map<String, String>> filteredSheet2Result = filter(unionCodeSheet1, overdueSheet1, sheet2Content);
		double maxLoanOverduePercentage = getCaculateResult(filteredSheet2Result);
		return String.valueOf(maxLoanOverduePercentage);
	}

	private double getCaculateResult(List<Map<String, String>> filteredSheet2Result) {
		double denominator = filteredSheet2Result.stream().filter(row -> "0".equalsIgnoreCase(row.get("mob")))
				.mapToDouble(row -> Double.valueOf(row.get("放款金额"))).sum();
		double numerator = 0.0;
		Map<Object, List<Map<String, String>>> groupedMap = filteredSheet2Result.stream()
				.collect(Collectors.groupingBy(o -> getMonth(o)));
		for (Object eachMonth : groupedMap.keySet()) {
			List<Map<String, String>> monthDataList = groupedMap.get(eachMonth);
			Optional<Map<String, String>> maxItem = monthDataList.stream()
					.max(Comparator.comparingInt(o -> Integer.valueOf(o.get("mob"))));
			numerator += Double.valueOf(maxItem.get().get("逾期余额"));
		}
		return numerator / denominator;
	}

	private String getMonth(Map<String, String> inputRow) {
		String rawMonth = inputRow.get("入账月份");
		return rawMonth;
	}

	private List<Map<String, String>> filter(String unionCodeSheet1, String overdueSheet1,
			List<Map<String, String>> sheet2Content) {
		if (StringUtils.isBlank(unionCodeSheet1) || StringUtils.isBlank(overdueSheet1)) {
			logger.error("unionCodeSheet1 or overdueSheet1 is blank, returning empty result");
			return Collections.emptyList();
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (Map<String, String> rowSheet2 : sheet2Content) {
			if (unionCodeSheet1.trim().equalsIgnoreCase(rowSheet2.get("union_code").trim())
					&& overdueSheet1.trim().equalsIgnoreCase(rowSheet2.get("kpi").trim())
					&& rowSheet2.get("入账月份").endsWith("/19")) {
				result.add(rowSheet2);
			}
		}
		return result;
	}
}
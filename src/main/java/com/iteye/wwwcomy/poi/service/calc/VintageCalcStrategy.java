package com.iteye.wwwcomy.poi.service.calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iteye.wwwcomy.poi.exception.InvalidParameterException;

/**
 * vintage <br>
 * 表1.助贷码=表2.union_code，表1.逾期时间=表2.kpi，表2.入账月份为19年，取MAX(表2.放款逾期率）
 * 
 * @author xingnliu
 *
 */
@Service
public class VintageCalcStrategy implements ExcelDataCalculateStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getResult(Map<String, String> currentRowInSheet1, List<Map<String, String>> sheet2Content) {
		String unionCodeSheet1 = currentRowInSheet1.get("助贷码");
		List<String> unionCodeListSheet1 = Arrays.asList(unionCodeSheet1.split("\n"));
		String overdueSheet1 = currentRowInSheet1.get("逾期时间");
		List<Map<String, String>> filteredSheet2Result = filter(unionCodeListSheet1, overdueSheet1, sheet2Content);
		double maxLoanOverduePercentage = getMaxLoanOverduePercentage(filteredSheet2Result);
		return String.valueOf(maxLoanOverduePercentage);
	}

	private double getMaxLoanOverduePercentage(List<Map<String, String>> filteredSheet2Result) {
		double maxPercentage = 0.0;
		Map<Object, List<Map<String, String>>> groupedMap = filteredSheet2Result.stream()
				.collect(Collectors.groupingBy(o -> o.get("mob") + "_" + getMonth(o)));
		for (Object k : groupedMap.keySet()) {
			List<Map<String, String>> v = groupedMap.get(k);
			double current = v.stream().mapToDouble(o -> Double.valueOf(o.get("逾期余额"))).reduce((a, b) -> a + b)
					.getAsDouble();
			double whole = v.stream().mapToDouble(o -> Double.valueOf(o.get("放款金额"))).reduce((a, b) -> a + b)
					.getAsDouble();
			if (current / whole > maxPercentage) {
				maxPercentage = current / whole;
			}
		}
		return maxPercentage;
	}

	private String getMonth(Map<String, String> inputRow) {
		String rawMonth = inputRow.get("入账月份");
		try {
			Date d = new SimpleDateFormat("M/d/yy").parse(rawMonth);
			return new SimpleDateFormat("yyyy-MM").format(d);
		} catch (ParseException e) {
			throw new InvalidParameterException("The input date format is not \"m/d/yy\", please check:" + rawMonth);
		}
	}

	protected List<Map<String, String>> filter(List<String> unionCodeListSheet1, String overdueSheet1,
			List<Map<String, String>> sheet2Content) {
		if (CollectionUtils.isEmpty(unionCodeListSheet1) || StringUtils.isBlank(overdueSheet1)) {
			logger.error("unionCodeSheet1 or overdueSheet1 is blank, returning empty result");
			return Collections.emptyList();
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (Map<String, String> rowSheet2 : sheet2Content) {
			if (unionCodeListSheet1.contains(rowSheet2.get("union_code"))
					&& overdueSheet1.equalsIgnoreCase(rowSheet2.get("kpi")) && rowSheet2.get("入账月份").endsWith("/19")
					&& extraLogic(rowSheet2)) {
				result.add(rowSheet2);
			}
		}
		return result;
	}

	protected boolean extraLogic(Map<String, String> rowSheet2) {
		return true;
	}

}

package com.iteye.wwwcomy.poi.service.calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iteye.wwwcomy.poi.exception.InvalidParameterException;

/**
 * 时点放款 <br>
 * 表1.助贷码=表2.union_code，表1.逾期时间=表2.kpi，表2.入账月份为19年，
 * 分母：取所有表2.mob=0的表2.放款金额相加，分子：每一个月份分别取表2.mob最大的那行的表2.逾期余额的值，相加，取分子/分母
 * 
 * @author xingnliu
 */
@Service
public class ShiDianFangKuanCalcStrategy implements ExcelDataCalculateStrategy {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getResult(Map<String, String> currentRowInSheet1, List<Map<String, String>> sheet2Content) {
		String unionCodeSheet1 = currentRowInSheet1.get("助贷码");
		List<String> unionCodeListSheet1 = Arrays.asList(unionCodeSheet1.split("\n"));
		String overdueSheet1 = currentRowInSheet1.get("逾期时间");
		List<Map<String, String>> filteredSheet2Result = filter(unionCodeListSheet1, overdueSheet1, sheet2Content);
		double maxLoanOverduePercentage = getCaculateResult(filteredSheet2Result);
		if (Double.isNaN(maxLoanOverduePercentage)) {
			return "";
		}
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
			numerator += monthDataList.stream().filter(o -> o.get("mob").equalsIgnoreCase(maxItem.get().get("mob")))
					.mapToDouble(row -> Double.valueOf(row.get("逾期余额"))).sum();
		}
		return numerator / denominator;
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

	protected List<Map<String, String>> filter(List<String> unionCodeSheet1, String overdueSheet1,
			List<Map<String, String>> sheet2Content) {
		if (CollectionUtils.isEmpty(unionCodeSheet1) || StringUtils.isBlank(overdueSheet1)) {
			logger.error("unionCodeSheet1 or overdueSheet1 is blank, returning empty result");
			return Collections.emptyList();
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (Map<String, String> rowSheet2 : sheet2Content) {
			if (unionCodeSheet1.contains(rowSheet2.get("union_code"))
					&& overdueSheet1.equalsIgnoreCase(rowSheet2.get("kpi")) && rowSheet2.get("入账月份").endsWith("/19")) {
				result.add(rowSheet2);
			}
		}
		return result;
	}
}
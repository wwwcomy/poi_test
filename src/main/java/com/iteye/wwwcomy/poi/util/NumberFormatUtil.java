package com.iteye.wwwcomy.poi.util;

import org.apache.commons.lang3.StringUtils;

public class NumberFormatUtil {

	private static final String YI = "亿";

	public static String formatAsHundredMillion(double input) {
		double n = (double) input / 100000000;
		return String.format("%.2f", n) + YI;
	}

	public static String formatAsHundredMillion(String input) {
		if (StringUtils.isBlank(input)) {
			return "0.00" + YI;
		}
		return formatAsHundredMillion(Double.valueOf(input));
	}
}

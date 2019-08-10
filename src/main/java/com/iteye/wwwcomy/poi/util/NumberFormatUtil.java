package com.iteye.wwwcomy.poi.util;

import java.text.DecimalFormat;

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

	/**
	 * for format pattern, please refer to
	 * https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
	 * 
	 * @param input
	 * @return
	 */
	public static String formatAsTwoDigitPercentage(double input) {
		DecimalFormat df = (DecimalFormat) DecimalFormat.getPercentInstance();
		df.applyPattern("#0.00%");
		return String.valueOf(df.format(input));
	}

	public static String formatAsTwoDigitPercentage(String input) {
		if (StringUtils.isBlank(input)) {
			return "N/A";
		}
		return formatAsTwoDigitPercentage(Double.valueOf(input));
	}

}

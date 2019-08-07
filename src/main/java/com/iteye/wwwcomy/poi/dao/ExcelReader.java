package com.iteye.wwwcomy.poi.dao;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;

import com.iteye.wwwcomy.poi.util.NumberFormatUtil;

public class ExcelReader {

	private String excelFilePath;
	private XSSFWorkbook workbook;
	public static final String SHEET_CHART = "图";

	public ExcelReader(String f) throws Exception {
		this.excelFilePath = f;
		// 实例化excel 文件的FileInputStream 对象
		FileInputStream fis = new FileInputStream(excelFilePath);
		// 实例化excel 文件的XSSFWorkbook 对象
		workbook = new XSSFWorkbook(fis);
	}

	public XSSFWorkbook getWorkBook() {
		return this.workbook;
	}

	public String readExcel(String sheetName, int rownum, int colNum) {
		// 实例化ExcelWSheet 对象，指定excel 文件中的sheet 名称，后续用于sheet 中行、列和单元格的操作
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFCell cell = sheet.getRow(rownum).getCell(colNum);
		cell.getRawValue();
		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(cell);
	}

	public List<Map<String, String>> readToList(String sheetNum) {
		XSSFSheet sheet = workbook.getSheet(sheetNum);
		List<String> headers = new ArrayList<String>();
		XSSFRow firstRow = sheet.getRow(0);
		DataFormatter dataFormatter = new DataFormatter();
		for (int i = 0; i < firstRow.getLastCellNum(); i++) {
			headers.add(dataFormatter.formatCellValue(firstRow.getCell(i)));
		}
		List<Map<String, String>> result = new ArrayList<>();
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			XSSFRow currentRow = sheet.getRow(i);
			if (null == currentRow) {
				break;
			}
			Map<String, String> singleRowMap = new HashMap<String, String>();
			if (StringUtils.isEmpty(dataFormatter.formatCellValue(currentRow.getCell(0)))) {
				break;
			}
			for (int j = 0; j < currentRow.getLastCellNum(); j++) {
				if (currentRow.getCell(j) != null && currentRow.getCell(j).getCellStyle() != null && "0\\.00,,\"亿\""
						.equalsIgnoreCase(currentRow.getCell(j).getCellStyle().getDataFormatString())) {
					singleRowMap.put(headers.get(j), NumberFormatUtil
							.formatAsHundredMillion(dataFormatter.formatCellValue(currentRow.getCell(j))));
				} else {
					singleRowMap.put(headers.get(j), dataFormatter.formatCellValue(currentRow.getCell(j)));
				}
			}
			result.add(singleRowMap);
		}
		return result;
	}

	/**
	 * 
	 */
	public void readChart() {
		XSSFSheet sheet = workbook.getSheet(SHEET_CHART);
		// HSSFChart
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		List<XSSFChart> charts = drawing.getCharts();
		for (XSSFChart chart : charts) {
			List<CTValAx> ctValAxList = chart.getCTChart().getPlotArea().getValAxList();
			for (CTValAx val : ctValAxList) {
				System.out.println(val);
			}
		}
	}
}

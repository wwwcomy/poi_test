package com.iteye.wwwcomy.poi.dao;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.springframework.stereotype.Repository;

@Repository
public class ChartWriter {
	public void draw() throws IOException {
		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);
		double[][] data = new double[][] { { 2.98, 17.17, 10.72, 6.19 }, { 2.95, 19.66, 28.60, 32.57 } };
		String[] rowKeys = { "放款", "本金余额" };
		String[] columnKeys = { "aug 18", "sep 18", "oct 18", "nov 18" };
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);

		JFreeChart lineChartObject = ChartFactory.createBarChart("", "时间", "单位:亿", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */
		File lineChart = new File("LineChart.jpg");
		ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);

	}
}

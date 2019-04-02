package com.iteye.wwwcomy.poi.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.deepoove.poi.XWPFTemplate;
import com.iteye.wwwcomy.authservice.exception.SysInternalException;
import com.iteye.wwwcomy.poi.util.IOUtil;

@Repository
public class WordWriter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value(value = "${poi.output.folder}")
	private String outputFolder;
	@Value(value = "${poi.input.template.path}")
	private String templatePath;

	@PostConstruct
	public void init() {
		File folder = new File(outputFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
		if (!folder.isDirectory()) {
			throw new SysInternalException("The output folder configured is not correct:" + outputFolder);
		}
	}

	public void writeToFile(List<Map<String, String>> organizations) throws Exception {
		for (int i = 0; i < organizations.size(); i++) {
			Map<String, String> organization = organizations.get(i);
			String unionloanName = organization.get("unionloanname");
			logger.info("Handling UnionloanName: {}", unionloanName);
			XWPFTemplate template = null;
			FileOutputStream out = null;
			try {
				template = XWPFTemplate.compile(templatePath).render(organization);
				out = new FileOutputStream(outputFolder + "/" + unionloanName + ".docx");
				template.write(out);
				out.flush();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				IOUtil.closeQuietly(out);
				IOUtil.closeQuietly(template);
			}
		}
	}
}

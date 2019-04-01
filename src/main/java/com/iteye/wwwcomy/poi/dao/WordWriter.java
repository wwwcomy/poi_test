package com.iteye.wwwcomy.poi.dao;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.iteye.wwwcomy.authservice.exception.SysInternalException;
import com.iteye.wwwcomy.poi.model.Organization;

@Repository
public class WordWriter {

	@Value(value = "${poi.output.folder}")
	private String outputFolder;

	@PostConstruct
	public void init() {
		File folder = new File(outputFolder);
		if (!folder.exists() || folder.isDirectory()) {
			throw new SysInternalException("The output folder configured is not correct:" + outputFolder);
		}
	}

	public void writeAsString(List<Organization> organizations) throws Exception {
	}
}

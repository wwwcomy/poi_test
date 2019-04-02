package com.iteye.wwwcomy.poi.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deepoove.poi.XWPFTemplate;

public class IOUtil {
	private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

	public static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public static void closeQuietly(XWPFTemplate template) {
		if (template != null) {
			try {
				template.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}

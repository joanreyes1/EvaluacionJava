package com.cmc.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil {
	private final static Logger LOGGER = LogManager.getLogger(DateUtil.class);

	public static Date convertir(String fecha) throws ParseException {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");
		try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			LOGGER.error("fecha incorrecta" + e);
		}
		return date;

	}

}

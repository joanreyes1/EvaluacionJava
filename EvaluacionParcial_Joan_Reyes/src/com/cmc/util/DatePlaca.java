package com.cmc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePlaca {
	public Date convertir(String fecha) throws ParseException {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");
		try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			System.out.println("Fecha incorrecta" + e);
		}
		return date;

	}
}

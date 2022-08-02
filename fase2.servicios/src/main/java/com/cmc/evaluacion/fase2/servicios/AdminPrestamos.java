package com.cmc.evaluacion.fase2.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.commons.util.DateUtil;
import com.cmc.commons.util.TipoPrestamo;
import com.cmc.evaluacion.fase2.commons.EvaluacionException;
import com.cmc.evaluacion.fase2.entidades.Cartera;
import com.cmc.evaluacion.fase2.entidades.Prestamo;

public class AdminPrestamos {
	private static final Logger LOGGER = LogManager.getLogger(AdminPrestamos.class);

	public void colocarPrestamos(String ruta, Cartera cartera) {
		File archivo = new File(ruta);
		FileReader fileReader = null;
		BufferedReader br = null;
		String linea = null;
		try {
			fileReader = new FileReader(archivo);
			br = new BufferedReader(fileReader);
			while ((linea = br.readLine()) != null) {
				LOGGER.trace("linea: " + linea);
				armarPrestamo(linea, cartera);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("No se encuentra el archivo ", e);
			throw new EvaluacionException("No se encontro el archivo: " + ruta);
		} catch (IOException e) {
			LOGGER.error("Error al leer el archivo ", e);
			throw new EvaluacionException("Error a leer el archivo: " + ruta);
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error al cerrar los Readers", e);
			}

		}

	}

	public void armarPrestamo(String linea, Cartera cartera) {
		String letra = null;
		Prestamo prestamo = null;
		String[] partes = new String[4];
		try {
			partes = linea.split("-");
			letra = partes[1].substring(0, 1);
			if (letra.equals(TipoPrestamo.HIPOTECARIO)) {
				letra = "H";
			} else if (letra.equals(TipoPrestamo.QUIROGRAFARIO)) {
				letra = "Q";
			} else {
				letra = "O";
			}
			prestamo = new Prestamo(partes[1], partes[0]);
			prestamo.setTipo(letra);
			prestamo.setFecha(DateUtil.convertir(partes[2]));
			prestamo.setMonto(Double.parseDouble(partes[3]));
			cartera.colocarPrestamo(prestamo);
		} catch (Exception e) {
			LOGGER.error("Error al armar el prestamo", e);
		}
	}
}

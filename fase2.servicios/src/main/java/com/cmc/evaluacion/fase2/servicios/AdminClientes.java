package com.cmc.evaluacion.fase2.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.evaluacion.fase2.commons.EvaluacionException;
import com.cmc.evaluacion.fase2.entidades.Cartera;
import com.cmc.evaluacion.fase2.entidades.Cliente;

public class AdminClientes {

	private static Logger logger = LogManager.getLogger(AdminClientes.class);

	public static Cartera armarCartera(String rutaArchivo) {
		Cartera cartera = new Cartera();
		File file = new File(rutaArchivo);
		FileReader fileReader = null;
		BufferedReader br = null;
		String linea = null;
		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			while ((linea = br.readLine()) != null) {
				logger.trace("linea: " + linea);
				armarCliente(linea, cartera);

			}
			return cartera;
		} catch (FileNotFoundException e) {
			logger.error("No se puedo abri el archivo" + e);
			throw new EvaluacionException("Error al abrir archivo" + rutaArchivo);
		} catch (IOException e) {
			logger.error("Error al leer Archivo" + e);
			throw new EvaluacionException("Error al leer Archivo" + rutaArchivo);

		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("Error al leer los readers");
			}
		}

	}

	public static void armarCliente(String linea, Cartera cartera) {
		Cliente cliente = null;
		String[] partes = new String[3];
		try {
			partes = linea.split(",");
			cliente = new Cliente(partes[0], partes[1], partes[2]);
			cartera.agregarCliente(cliente);
		} catch (Exception e) {
			logger.error("error al armar cliente en la línea" + e);
		}

	}
}

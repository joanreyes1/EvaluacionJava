package com.cmc.entidades;

import java.util.Date;

import com.cmc.util.DatePlaca;

public class Placa {
	private String placa;
	private Date fecha;

	public Placa(String placa, Date fecha) {
		super();
		this.placa = placa;
		this.fecha = fecha;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Placa [placa=" + placa + ", fecha=" + fecha + "]";
	}
	

	public static void validaPlaca(String placa, String FechaHora) {
		
	}

}

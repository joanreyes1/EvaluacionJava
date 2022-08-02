package com.cmc.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/clientes")
public class ServiciosCliente {
	
	@Path("/consulta")
	@GET
	public String metodo1(){
		return "Saludando desde el rest web service";
	}
}

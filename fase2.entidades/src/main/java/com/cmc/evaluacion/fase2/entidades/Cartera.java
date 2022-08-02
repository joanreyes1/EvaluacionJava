package com.cmc.evaluacion.fase2.entidades;

import java.util.ArrayList;
import java.util.HashMap;

public class Cartera {
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private HashMap<String, ArrayList<Pago>> pagos = new HashMap<String, ArrayList<Pago>>();

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public String toString() {
		return "Cartera [clientes=" + clientes + "]";
	}

	public boolean agregarCliente(Cliente cliente) {
		Cliente clienteObtenido = buscarCliente(cliente.getCedula());
		if (clienteObtenido == null) {
			clientes.add(cliente);
			return true;
		}
		return false;
	}

	public Cliente buscarCliente(String cedula) {
		for (Cliente cliente : clientes) {
			if (cliente.getCedula().equals(cedula))
				return cliente;
		}
		return null;
	}

	public void colocarPrestamo(Prestamo prestamo) {
		Cliente obtenerCliente = buscarCliente(prestamo.getCedulaCliente());
		if (obtenerCliente != null) {
			obtenerCliente.agregarPrestamo(prestamo);
		}
	}
	
	public void agregarPago(Pago pago) {
		ArrayList<Pago> pagosP = null;
		if (pagos.get(pago.getNumeroPrestamo()) == null) {
			pagosP = new ArrayList<Pago>();
			pagosP.add(pago);
			pagos.put(pago.getNumeroPrestamo(), pagosP);
		} else {
			pagosP = pagos.get(pago.getNumeroPrestamo());
			pagosP.add(pago);
		}
	}
	
	public ArrayList<Pago> consultarPagos(String codigo) {
		ArrayList<Pago> pagosP = null;
		if (pagos.get(codigo) != null) {
			return pagos.get(codigo);
		} else {
			return pagosP = new ArrayList<Pago>();
		}
	}
	
	public ArrayList<Balance> calcularBalances() {
		ArrayList<Balance> balances = new ArrayList<Balance>();
		ArrayList<Pago> pagosD= null;
		double sumaPrestamos = 0;
		double sumaPagos = 0;
		Balance balance = null;
		for (Cliente cliente : clientes) {
			balance = new Balance();
			ArrayList<Prestamo> prestamos = cliente.getPrestamos();
			
			for (Prestamo prestamo : prestamos) {
				sumaPrestamos+=prestamo.getMonto();
				pagosD = pagos.get(prestamo.getNumero());
				if (pagosD != null) {
					for (Pago pago : pagosD) {
						sumaPagos+= pago.getMonto();
					}
				}
				
			}
			balance.setNumeroCedula(cliente.getCedula());
			balance.setValorPrestamo(sumaPrestamos);
			balance.setValorPagado(sumaPagos);
			balance.setSaldo(sumaPrestamos-sumaPagos);
			
			balances.add(balance);
		}
		
		return balances;

	}

}

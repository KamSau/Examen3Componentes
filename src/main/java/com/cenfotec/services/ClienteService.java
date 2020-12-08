package com.cenfotec.services;

import java.util.List;
import java.util.Optional;

import com.cenfotec.entites.Cliente;

public interface ClienteService {

	public List<Cliente> getAllClientes(int limit);

	public List<Cliente> getAllClientes();

	public List<Cliente> getAllClientes(String buscar);

	public Cliente createCliente(String nombre, String apellido1, String apellido2, String direccionCobro,
			String direccionVive, String numTarjeta, int mesVencimientoTarjeta, int annoVencimientoTarjeta);

	public Cliente save(Cliente cliente);

	public Optional<Cliente> getCliente(int id);

	public boolean deleteCliente(int id);

	public Cliente updateCliente(int id, String nombre, String apellido1, String apellido2, String direccionCobro,
			String direccionVive, String numTarjeta, int mesVencimientoTarjeta, int annoVencimientoTarjeta);

	public Optional<Cliente> updateCliente(int id, Cliente editar);

}

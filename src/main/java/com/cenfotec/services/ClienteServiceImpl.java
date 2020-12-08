package com.cenfotec.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.entites.Cliente;
import com.cenfotec.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	@Override
	public List<Cliente> getAllClientes(int count) {
		return this.clienteRepo.findAll().stream().limit(count).collect(Collectors.toList());
	}

	@Override
	public List<Cliente> getAllClientes() {
		return this.clienteRepo.findAll().stream().collect(Collectors.toList());
	}

	@Override
	public List<Cliente> getAllClientes(String buscar) {
		return this.clienteRepo.findAll().stream()
				.filter(c -> (c.getApellido1().toLowerCase().contains(buscar.toLowerCase())
						|| c.getApellido2().toLowerCase().contains(buscar.toLowerCase())
						|| c.getDireccionCobro().toLowerCase().contains(buscar.toLowerCase())))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Cliente> getCliente(int id) {
		return this.clienteRepo.findById(id);
	}

	@Override
	public Cliente createCliente(String nombre, String apellido1, String apellido2, String direccionCobro,
			String direccionVive, String numTarjeta, int mesVencimientoTarjeta, int annoVencimientoTarjeta) {

		Cliente cliente = new Cliente();

		cliente.setNombre(nombre);
		cliente.setApellido1(apellido1);
		cliente.setApellido2(apellido2);
		cliente.setDireccionCobro(direccionCobro);
		cliente.setDireccionVive(direccionVive);
		cliente.setNumTarjeta(numTarjeta);
		cliente.setMesVencimientoTarjeta(mesVencimientoTarjeta);
		cliente.setAnnoVencimientoTarjeta(annoVencimientoTarjeta);

		return this.clienteRepo.save(cliente);
	}

	@Override
	public Cliente updateCliente(int id, String nombre, String apellido1, String apellido2, String direccionCobro,
			String direccionVive, String numTarjeta, int mesVencimientoTarjeta, int annoVencimientoTarjeta) {

		Cliente cliente = clienteRepo.getById(id);

		cliente.setNombre(nombre);
		cliente.setApellido1(apellido1);
		cliente.setApellido2(apellido2);
		cliente.setDireccionCobro(direccionCobro);
		cliente.setDireccionVive(direccionVive);
		cliente.setNumTarjeta(numTarjeta);
		cliente.setMesVencimientoTarjeta(mesVencimientoTarjeta);
		cliente.setAnnoVencimientoTarjeta(annoVencimientoTarjeta);

		return this.clienteRepo.save(cliente);
	}

	@Override
	public Optional<Cliente> updateCliente(int id, Cliente editar) {

		Cliente cliente = clienteRepo.getById(id);
		if (cliente != null) {
			cliente.setNombre(editar.getNombre());
			cliente.setApellido1(editar.getApellido1());
			cliente.setApellido2(editar.getApellido2());
			cliente.setDireccionCobro(editar.getDireccionCobro());
			cliente.setDireccionVive(editar.getDireccionVive());
			cliente.setNumTarjeta(editar.getNumTarjeta());
			cliente.setMesVencimientoTarjeta(editar.getMesVencimientoTarjeta());
			cliente.setAnnoVencimientoTarjeta(editar.getAnnoVencimientoTarjeta());
			this.clienteRepo.save(cliente);
		}
		return getCliente(id);
	}

	@Override
	public boolean deleteCliente(int id) {
		Optional<Cliente> clienteO = this.getCliente(id);
		if (clienteO.isPresent() && clienteO.get().getOrdenes().isEmpty()) {
			this.clienteRepo.delete(clienteO.get());
		} else {
			return false;
		}
		clienteO = this.getCliente(id);
		return clienteO.isEmpty();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return this.clienteRepo.save(cliente);
	}

}

package com.cenfotec.graphql.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.entites.Cliente;
import com.cenfotec.services.ClienteServiceImpl;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class ClienteQuery implements GraphQLQueryResolver {
	@Autowired
	private ClienteServiceImpl clienteService;

	public List<Cliente> getClientes(int count) {
		return this.clienteService.getAllClientes(count);
	}

	public List<Cliente> getClientes() {
		return this.clienteService.getAllClientes();
	}

	public Optional<Cliente> getCliente(int id) {
		return this.clienteService.getCliente(id);
	}

	public List<Cliente> getClientesB(String buscar) {
		return this.clienteService.getAllClientes(buscar);
	}
}

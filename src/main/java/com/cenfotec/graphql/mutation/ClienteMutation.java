package com.cenfotec.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.entites.Cliente;
import com.cenfotec.services.ClienteServiceImpl;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class ClienteMutation implements GraphQLMutationResolver {
	@Autowired
	private ClienteServiceImpl clienteService;

	public Cliente createCliente(String nombre, String apellido1, String apellido2, String direccionCobro,
			String direccionVive, String numTarjeta, int mesVencimientoTarjeta, int annoVencimientoTarjeta) {
		return this.clienteService.createCliente(nombre, apellido1, apellido2, direccionCobro, direccionVive,
				numTarjeta, mesVencimientoTarjeta, annoVencimientoTarjeta);
	}

	public Cliente updateCliente(int id, String nombre, String apellido1, String apellido2, String direccionCobro,
			String direccionVive, String numTarjeta, int mesVencimientoTarjeta, int annoVencimientoTarjeta) {
		return this.clienteService.updateCliente(id, nombre, apellido1, apellido2, direccionCobro, direccionVive,
				numTarjeta, mesVencimientoTarjeta, annoVencimientoTarjeta);
	}

	public boolean deleteCliente(int id) {
		return this.clienteService.deleteCliente(id);
	}

}

package com.cenfotec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.entites.Cliente;
import com.cenfotec.services.ClienteServiceImpl;

@RestController
@RequestMapping({ "/api/v1/clientes" })
public class ClienteController {
	@Autowired
	private ClienteServiceImpl clienteService;

	@GetMapping
	public List<Cliente> findAll(@RequestParam(required = false) String buscar) {
		if (buscar != null) {
			return clienteService.getAllClientes(buscar);
		} else {
			return clienteService.getAllClientes();
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Cliente> findById(@PathVariable int id) {
		Optional<Cliente> cliente = clienteService.getCliente(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable("id") int id, @RequestBody Cliente editar) {
		Optional<Cliente> cliente = clienteService.updateCliente(id, editar);
		if (cliente.isPresent()) {
			return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		Optional<Cliente> cliente = clienteService.getCliente(id);
		if (cliente.isPresent()) {
			if (clienteService.deleteCliente(id))
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

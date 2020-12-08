package com.cenfotec.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.cenfotec.entites.Orden;
import com.cenfotec.services.OrdenServiceImpl;

@RestController
@RequestMapping({ "/api/v1/ordenes" })
public class OrderController {
	@Autowired
	private OrdenServiceImpl ordenService;

	@GetMapping
	public List<Orden> findAll(@RequestParam(required = false) String producto) {
		if (producto != null) {
			return ordenService.getAllOrdenes().stream().filter(c -> c.getProducto().equalsIgnoreCase(producto))
					.collect(Collectors.toList());
		} else {
			return ordenService.getAllOrdenes();
		}
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Orden> findById(@PathVariable int id) {
		Optional<Orden> orden = ordenService.getOrden(id);
		if (orden.isPresent()) {
			return new ResponseEntity<Orden>(orden.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Orden> create(@RequestBody Orden orden) {
		Orden ord = ordenService.save(orden);
		if (ord != null) {
			return new ResponseEntity<Orden>(ord, HttpStatus.OK);
		} else {
			return new ResponseEntity<Orden>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Orden> update(@PathVariable("id") int id, @RequestBody Orden editar) {
		Optional<Orden> orden = ordenService.updateOrden(id, editar);
		if (orden.isPresent()) {
			return new ResponseEntity<Orden>(orden.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		Optional<Orden> orden = ordenService.getOrden(id);
		if (orden.isPresent()) {
			if (ordenService.deleteOrden(id))
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

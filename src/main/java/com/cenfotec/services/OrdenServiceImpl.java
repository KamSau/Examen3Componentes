package com.cenfotec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.entites.Cliente;
import com.cenfotec.entites.Orden;
import com.cenfotec.repositories.ClienteRepository;
import com.cenfotec.repositories.OrdenRepository;
import com.cenfotec.response.PriceCalculator;

@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	OrdenRepository ordenRepo;
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	PriceCalculator priceCalc;

	@Override
	public List<Orden> getAllOrdenes(int limit) {
		List<Orden> all = this.ordenRepo.findAll().stream().limit(limit).map(s -> {
			s.setTotal(priceCalc.calculatePrice(s.getProducto(), s.getCantidad()));
			s.setClienteId(s.getCliente().getId());
			return s;
		}).collect(Collectors.toList());
		all.forEach(c -> priceCalc.calculatePrice(c.getProducto(), c.getCantidad()));
		return all;
	}

	@Override
	public List<Orden> getAllOrdenes() {
		return this.ordenRepo.findAll().stream().map(s -> {
			s.setTotal(priceCalc.calculatePrice(s.getProducto(), s.getCantidad()));
			s.setClienteId(s.getCliente().getId());
			return s;
		}).collect(Collectors.toList());
	}

	@Override
	public Orden save(Orden orden) {

		ArrayList<String> items = new ArrayList<String>();
		items.add("tasa");
		items.add("camiseta");
		items.add("almohadon");
		items.add("vaso");

		if (!items.contains(orden.getProducto().toLowerCase())) {
			return null;
		}

		if (orden.getCliente() == null) {
			Cliente cliente = clienteRepo.getById(orden.getClienteId());
			if (cliente != null) {
				orden.setCliente(cliente);
			} else {
				return null;
			}
		}
		return this.ordenRepo.save(orden);
	}

	@Override
	public Optional<Orden> getOrden(int id) {
		Optional<Orden> orden = this.ordenRepo.findById(id);
		if (orden.isPresent()) {
			orden.get().setTotal(priceCalc.calculatePrice(orden.get().getProducto(), orden.get().getCantidad()));
			orden.get().setClienteId(orden.get().getCliente().getId());
		}
		return orden;
	}

	@Override
	public boolean deleteOrden(int id) {
		this.ordenRepo.deleteById(id);
		if (this.getOrden(id).isPresent()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Optional<Orden> updateOrden(int id, Orden editar) {
		Orden orden = ordenRepo.getById(id);
		if (orden != null) {
			orden.setCantidad(editar.getCantidad());
			orden.setProducto(editar.getProducto());
			orden.setPath(editar.getPath());
			this.save(orden);
		}
		return getOrden(id);
	}

}

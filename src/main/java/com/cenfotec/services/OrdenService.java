package com.cenfotec.services;

import java.util.List;
import java.util.Optional;

import com.cenfotec.entites.Orden;

public interface OrdenService {

	public List<Orden> getAllOrdenes(int limit);

	public List<Orden> getAllOrdenes();

	public Orden save(Orden orden);

	public Optional<Orden> getOrden(int id);

	public boolean deleteOrden(int id);

	public Optional<Orden> updateOrden(int id, Orden editar);

}

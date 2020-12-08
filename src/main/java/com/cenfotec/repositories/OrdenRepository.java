package com.cenfotec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.entites.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
	public Orden getById(int id);
}
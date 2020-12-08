package com.cenfotec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.entites.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public Cliente getById(int id);
}
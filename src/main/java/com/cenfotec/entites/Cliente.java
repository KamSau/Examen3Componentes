package com.cenfotec.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "apellido1", nullable = false)
	private String apellido1;
	@Column(name = "apellido2", nullable = false)
	private String apellido2;
	@Column(name = "direccionCobro", nullable = false)
	private String direccionCobro;
	@Column(name = "direccionVive", nullable = false)
	private String direccionVive;
	@Column(name = "numTarjeta", nullable = false)
	private String numTarjeta;
	@Column(name = "mesVencimientoTarjeta", nullable = false)
	private int mesVencimientoTarjeta;
	@Column(name = "annoVencimientoTarjeta", nullable = false)
	private int annoVencimientoTarjeta;
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	private List<Orden> ordenes;

}

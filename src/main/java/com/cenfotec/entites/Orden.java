package com.cenfotec.entites;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Orden implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "producto", nullable = false)
	private String producto;
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	@Column(name = "path", nullable = false)
	private String path;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clienteId")
	private Cliente cliente;

	@Transient
	private int clienteId;
	@Transient
	private BigDecimal total;
}

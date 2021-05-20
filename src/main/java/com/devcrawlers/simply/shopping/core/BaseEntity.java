package com.devcrawlers.simply.shopping.core;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_generator")
	@SequenceGenerator(name = "common_generator", sequenceName = "common_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

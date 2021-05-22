package com.devcrawlers.simply.shopping.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devcrawlers.simply.shopping.core.BaseEntity;

import lombok.Data;

@Entity
@Data
@Table(name = "buyer")
public class Buyer extends BaseEntity implements Serializable {

	@Column(name = "name")
	private String name;
	
	@Column(name = "status")
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

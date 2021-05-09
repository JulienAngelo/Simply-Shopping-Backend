package com.devcrawlers.simply.shopping.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.devcrawlers.simply.shopping.core.BaseEntity;
import com.devcrawlers.simply.shopping.enums.UserRoles;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 0000000000001;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "name", length = 20)
	private UserRoles name;

	public UserRoles getName() {
		return name;
	}

	public void setName(UserRoles name) {
		this.name = name;
	}
}
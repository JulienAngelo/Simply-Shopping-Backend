package com.devcrawlers.simply.shopping.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.devcrawlers.simply.shopping.core.BaseEntity;
import com.devcrawlers.simply.shopping.enums.CommonStatus;

import lombok.Data;


/**
 * Attributes Domain
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                            MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Entity
@Data
@Table(name = "attributes")
public class Attributes extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 0000000000001;
	
	@Column(name = "name")
	private String name;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private CommonStatus status;
	
	@Column(name = "created_user")
	private String createdUser;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "modified_user")
	private String modifiedUser;
	
	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonStatus getStatus() {
		return status;
	}

	public void setStatus(CommonStatus status) {
		this.status = status;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}

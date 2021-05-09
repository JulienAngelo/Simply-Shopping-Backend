package com.devcrawlers.simply.shopping.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.devcrawlers.simply.shopping.base.MessagePropertyBase;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Common Add Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   02-08-2020          					MenukaJ     Created
 *    
 ********************************************************************************************************
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CommonRequestResource extends MessagePropertyBase{
	
	private String id;
	
	@NotBlank(message = COMMON_NOT_NULL)
	@Size(max = 70, message = COMMON_NAME_SIZE)
	private String name;
	
	@NotBlank(message = COMMON_NOT_NULL)
	@Pattern(regexp = "ACTIVE|INACTIVE", message = COMMON_STATUS_PATTERN)
	private String status;
	
	private String tenantId;

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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
	
	
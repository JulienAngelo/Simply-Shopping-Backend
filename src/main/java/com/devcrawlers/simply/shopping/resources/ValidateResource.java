package com.devcrawlers.simply.shopping.resources;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ValidateResource {
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("version")
	private String version;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("status")
	private String status;

	@JsonProperty("time")
	private Timestamp time;
	
	@JsonProperty("attributesId")
	private String attributesId;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("branchName")
	private String branchName;
	
	@JsonProperty("productId")
	private String productId;
	
	/******************************/
	
	

	public String getBranchName() {
		return branchName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAttributesId() {
		return attributesId;
	}

	public void setAttributesId(String attributesId) {
		this.attributesId = attributesId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

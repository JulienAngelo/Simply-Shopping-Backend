package com.devcrawlers.simply.shopping.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MessageResponseResource {

	@JsonProperty("message")
	private String message;

	@JsonProperty("details")
	private String details;

	@JsonProperty("value")
	private String value;

	@JsonProperty("response")
	private Object response;

	public MessageResponseResource() {
		super();
	}
	
	public MessageResponseResource(String message) {
		super();
		this.message = message;
	}

	public MessageResponseResource(String message, String value) {
		super();
		this.message = message;
		this.value = value;
	}
	
	public MessageResponseResource(String message, Object response) {
		super();
		this.message = message;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

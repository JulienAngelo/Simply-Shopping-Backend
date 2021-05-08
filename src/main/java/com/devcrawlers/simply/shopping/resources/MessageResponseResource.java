package com.devcrawlers.simply.shopping.resources;

public class MessageResponseResource {
	private String message;

	public MessageResponseResource(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

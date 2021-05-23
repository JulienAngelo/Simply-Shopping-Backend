package com.devcrawlers.simply.shopping.resources;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderAddResource {
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String buyersId;

	@Valid
	private List<OrderItemAddResource> itemList;
	
	public List<OrderItemAddResource> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItemAddResource> itemList) {
		this.itemList = itemList;
	}

	public String getBuyersId() {
		return buyersId;
	}

	public void setBuyersId(String buyersId) {
		this.buyersId = buyersId;
	}
	
}

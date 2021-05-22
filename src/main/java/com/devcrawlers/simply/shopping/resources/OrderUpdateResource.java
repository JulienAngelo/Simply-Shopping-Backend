package com.devcrawlers.simply.shopping.resources;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderUpdateResource {

	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String buyersId;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String serviceChargeRate;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String serviceCharge;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String vatChargeRate;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String vatCharge;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String netAmount;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|YES|NO",message="{common.flag.pattern}")
	private String deliveryFlag;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ACTIVE|INACTIVE",message="{common-status.pattern}")
	private String status;
	
	@Valid
	private List<OrderItemUpdateResource> itemList;

	public String getBuyersId() {
		return buyersId;
	}

	public void setBuyersId(String buyersId) {
		this.buyersId = buyersId;
	}

	public String getServiceChargeRate() {
		return serviceChargeRate;
	}

	public void setServiceChargeRate(String serviceChargeRate) {
		this.serviceChargeRate = serviceChargeRate;
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getVatChargeRate() {
		return vatChargeRate;
	}

	public void setVatChargeRate(String vatChargeRate) {
		this.vatChargeRate = vatChargeRate;
	}

	public String getVatCharge() {
		return vatCharge;
	}

	public void setVatCharge(String vatCharge) {
		this.vatCharge = vatCharge;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	
	public String getDeliveryFlag() {
		return deliveryFlag;
	}

	public void setDeliveryFlag(String deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItemUpdateResource> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItemUpdateResource> itemList) {
		this.itemList = itemList;
	}
	
}

package com.devcrawlers.simply.shopping.resources;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DeliveyInfoRequestResource {
	
	@NotBlank(message = "{common.not-null}")
	private String customerName;
	
	@NotBlank(message = "{common.not-null}")
	private String addressLine1;
	
	@NotBlank(message = "{common.not-null}")
	private String addressLine2;
	
	//@NotBlank(message = "{common.not-null}")
	private String addressLine3;
	
	@NotBlank(message = "{common.not-null}")
	private String province;
	
	@NotBlank(message = "{common.not-null}")
	private String shippingCode;
	
	@NotBlank(message = "{common.not-null}")
	private String country;
	
	@NotBlank(message = "{common.not-null}")
	private String contactNo;
	
	private String email;
	
	@Valid
	private List<DeliveyItemsResources> items;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<DeliveyItemsResources> getItems() {
		return items;
	}

	public void setItems(List<DeliveyItemsResources> items) {
		this.items = items;
	}

}

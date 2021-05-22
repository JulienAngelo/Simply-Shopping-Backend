package com.devcrawlers.simply.shopping.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ItemUpdateResource {

	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")	
    private String categorysId;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String brandsId;
	
	@NotBlank(message = "{common.not-null}")
	@Size(max = 70, message = "{common-name.size}")
	private String name;
	
	@Size(max = 255, message = "{description.size}")
	private String description;
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String quantity;
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String attributeValueId1;
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String attributeValueId2;
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String attributeValueId3;
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String attributeValueId4;
	
	private String url1;
	
	private String url2;
	
	private String url3;
	
	private String url4;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String price;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String discount;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ACTIVE|INACTIVE",message="{common-status.pattern}")
	private String status;

	public String getCategorysId() {
		return categorysId;
	}

	public void setCategorysId(String categorysId) {
		this.categorysId = categorysId;
	}

	public String getBrandsId() {
		return brandsId;
	}

	public void setBrandsId(String brandsId) {
		this.brandsId = brandsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAttributeValueId1() {
		return attributeValueId1;
	}

	public void setAttributeValueId1(String attributeValueId1) {
		this.attributeValueId1 = attributeValueId1;
	}

	public String getAttributeValueId2() {
		return attributeValueId2;
	}

	public void setAttributeValueId2(String attributeValueId2) {
		this.attributeValueId2 = attributeValueId2;
	}

	public String getAttributeValueId3() {
		return attributeValueId3;
	}

	public void setAttributeValueId3(String attributeValueId3) {
		this.attributeValueId3 = attributeValueId3;
	}

	public String getAttributeValueId4() {
		return attributeValueId4;
	}

	public void setAttributeValueId4(String attributeValueId4) {
		this.attributeValueId4 = attributeValueId4;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public String getUrl4() {
		return url4;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

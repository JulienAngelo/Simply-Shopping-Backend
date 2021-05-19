package com.devcrawlers.simply.shopping.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ItemAddResource {

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
	
	private String image1;
	
	private String image2;
	
	private String image3;
	
	private String image4;
	
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

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
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

package com.devcrawlers.simply.shopping.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.devcrawlers.simply.shopping.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


/**
 * Item Domain
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Entity
@Data
@Table(name = "item")
public class Item extends BaseEntity implements Serializable {
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@Transient
    private Long categorysId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;
	
	@Transient
    private Long brandsId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_1", nullable = true)
	private AttributeValue attributeValue1;
	
	@Transient
    private Long attributeValueId1;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_2", nullable = true)
	private AttributeValue attributeValue2;
	
	@Transient
    private Long attributeValueId2;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_3", nullable = true)
	private AttributeValue attributeValue3;
	
	@Transient
    private Long attributeValueId3;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_4", nullable = true)
	private AttributeValue attributeValue4;
	
	@Transient
    private Long attributeValueId4;
	
	@Column(name = "image1")
	private String image1;
	
	@Column(name = "image2")
	private String image2;
	
	@Column(name = "image3")
	private String image3;
	
	@Column(name = "image4")
	private String image4;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "discount")
	private BigDecimal discount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_user")
	private String createdUser;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "modified_user")
	private String modifiedUser;
	
	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getCategorysId() {
		return categorysId;
	}

	public void setCategorysId(Long categorysId) {
		this.categorysId = categorysId;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Long getBrandsId() {
		return brandsId;
	}

	public void setBrandsId(Long brandsId) {
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public AttributeValue getAttributeValue1() {
		return attributeValue1;
	}

	public void setAttributeValue1(AttributeValue attributeValue1) {
		this.attributeValue1 = attributeValue1;
	}

	public Long getAttributeValueId1() {
		return attributeValueId1;
	}

	public void setAttributeValueId1(Long attributeValueId1) {
		this.attributeValueId1 = attributeValueId1;
	}

	public AttributeValue getAttributeValue2() {
		return attributeValue2;
	}

	public void setAttributeValue2(AttributeValue attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}

	public Long getAttributeValueId2() {
		return attributeValueId2;
	}

	public void setAttributeValueId2(Long attributeValueId2) {
		this.attributeValueId2 = attributeValueId2;
	}

	public AttributeValue getAttributeValue3() {
		return attributeValue3;
	}

	public void setAttributeValue3(AttributeValue attributeValue3) {
		this.attributeValue3 = attributeValue3;
	}

	public Long getAttributeValueId3() {
		return attributeValueId3;
	}

	public void setAttributeValueId3(Long attributeValueId3) {
		this.attributeValueId3 = attributeValueId3;
	}

	public AttributeValue getAttributeValue4() {
		return attributeValue4;
	}

	public void setAttributeValue4(AttributeValue attributeValue4) {
		this.attributeValue4 = attributeValue4;
	}

	public Long getAttributeValueId4() {
		return attributeValueId4;
	}

	public void setAttributeValueId4(Long attributeValueId4) {
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
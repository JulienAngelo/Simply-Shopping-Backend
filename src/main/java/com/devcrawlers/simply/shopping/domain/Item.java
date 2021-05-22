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
	
	private static final long serialVersionUID = -4530733821780008289L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@Transient
    private Long categorysId;
	
	@Transient
    private String categorysName;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;
	
	@Transient
    private Long brandsId;
	
	@Transient
    private String brandsName;
	
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
	
	@Transient
    private String attributeValueId1Name;
	
	@Transient
    private Long attribute1Id;
	
	@Transient
    private String attribute1Name;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_2", nullable = true)
	private AttributeValue attributeValue2;
	
	@Transient
    private Long attributeValueId2;
	
	@Transient
    private String attributeValueId2Name;
	
	@Transient
    private Long attribute2Id;
	
	@Transient
    private String attribute2Name;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_3", nullable = true)
	private AttributeValue attributeValue3;
	
	@Transient
    private Long attributeValueId3;
	
	@Transient
    private String attributeValueId3Name;
	
	@Transient
    private Long attribute3Id;
	
	@Transient
    private String attribute3Name;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "attribute_value_id_4", nullable = true)
	private AttributeValue attributeValue4;
	
	@Transient
    private Long attributeValueId4;
	
	@Transient
    private String attributeValueId4Name;
	
	@Transient
    private Long attribute4Id;
	
	@Transient
    private String attribute4Name;
	
	@Column(name = "image1")
	private String url1;
	
	@Column(name = "image2")
	private String url2;
	
	@Column(name = "image3")
	private String url3;
	
	@Column(name = "image4")
	private String url4;
	
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
		if(category != null) {
			return category.getId();
		} else {
			return null;
		}
	}

	public String getCategorysName() {
		if(category != null) {
			return category.getName();
		} else {
			return null;
		}
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Long getBrandsId() {
		if(brand != null) {
			return brand.getId();
		} else {
			return null;
		}
	}

	public String getBrandsName() {
		if(brand != null) {
			return brand.getName();
		} else {
			return null;
		}
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
		if(attributeValue1 != null) {
			return attributeValue1.getId();
		} else {
			return null;
		}
	}
	
	public String getAttributeValueId1Name() {
		if(attributeValue1 != null) {
			return attributeValue1.getName();
		} else {
			return null;
		}
	}

	public Long getAttribute1Id() {
		if(attributeValue1 != null) {
			return attributeValue1.getAttributes().getId();
		} else {
			return null;
		}
	}

	public String getAttribute1Name() {
		if(attributeValue1 != null) {
			return attributeValue1.getAttributes().getName();
		} else {
			return null;
		}
	}

	public AttributeValue getAttributeValue2() {
		return attributeValue2;
	}

	public void setAttributeValue2(AttributeValue attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}

	public Long getAttributeValueId2() {
		if(attributeValue2 != null) {
			return attributeValue2.getId();
		} else {
			return null;
		}
	}
	
	public String getAttributeValueId2Name() {
		if(attributeValue2 != null) {
			return attributeValue2.getName();
		} else {
			return null;
		}
	}
	
	public Long getAttribute2Id() {
		if(attributeValue2 != null) {
			return attributeValue2.getAttributes().getId();
		} else {
			return null;
		}
	}

	public String getAttribute2Name() {
		if(attributeValue2 != null) {
			return attributeValue2.getAttributes().getName();
		} else {
			return null;
		}
	}

	public AttributeValue getAttributeValue3() {
		return attributeValue3;
	}

	public void setAttributeValue3(AttributeValue attributeValue3) {
		this.attributeValue3 = attributeValue3;
	}

	public Long getAttributeValueId3() {
		if(attributeValue3 != null) {
			return attributeValue3.getId();
		} else {
			return null;
		}
	}
	
	public String getAttributeValueId3Name() {
		if(attributeValue3 != null) {
			return attributeValue3.getName();
		} else {
			return null;
		}
	}
	
	public Long getAttribute3Id() {
		if(attributeValue3 != null) {
			return attributeValue3.getAttributes().getId();
		} else {
			return null;
		}
	}

	public String getAttribute3Name() {
		if(attributeValue3 != null) {
			return attributeValue3.getAttributes().getName();
		} else {
			return null;
		}
	}

	public AttributeValue getAttributeValue4() {
		return attributeValue4;
	}

	public void setAttributeValue4(AttributeValue attributeValue4) {
		this.attributeValue4 = attributeValue4;
	}

	public Long getAttributeValueId4() {
		if(attributeValue4 != null) {
			return attributeValue4.getId();
		} else {
			return null;
		}
	}
	
	public String getAttributeValueId4Name() {
		if(attributeValue4 != null) {
			return attributeValue4.getName();
		} else {
			return null;
		}
	}

	public Long getAttribute4Id() {
		if(attributeValue4 != null) {
			return attributeValue4.getAttributes().getId();
		} else {
			return null;
		}
	}

	public String getAttribute4Name() {
		if(attributeValue4 != null) {
			return attributeValue4.getAttributes().getName();
		} else {
			return null;
		}
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
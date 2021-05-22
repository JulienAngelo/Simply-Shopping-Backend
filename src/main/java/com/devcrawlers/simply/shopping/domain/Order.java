package com.devcrawlers.simply.shopping.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Order Domain
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
@Table(name = "orders")
public class Order extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7306355683311747688L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "buyer_id", nullable = false)
	private Buyer buyer;
	
	@Transient
    private Long buyersId;
	
	@Column(name = "service_charge_rate")
	private BigDecimal serviceChargeRate;
	
	@Column(name = "service_charge")
	private BigDecimal serviceCharge;
	
	@Column(name = "vat_charge_rate")
	private BigDecimal vatChargeRate;
	
	@Column(name = "vat_charge")
	private BigDecimal vatCharge;
	
	@Column(name = "net_amount")
	private BigDecimal netAmount;
	
	@Column(name = "paid_status")
	private String paidStatus;
	
	@Column(name = "payment_ref_no")
	private String paymentRefNo;
	
	@Column(name = "payment_date")
	private Timestamp paymentDate;
	
	@Column(name = "delivery_flag")
	private String deliveryFlag;
	
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
	
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<OrderItem> itemList;

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Long getBuyersId() {
		if(buyer != null) {
			return buyer.getId();
		} else {
			return null;
		}
	}

	public BigDecimal getServiceChargeRate() {
		return serviceChargeRate;
	}

	public void setServiceChargeRate(BigDecimal serviceChargeRate) {
		this.serviceChargeRate = serviceChargeRate;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public BigDecimal getVatChargeRate() {
		return vatChargeRate;
	}

	public void setVatChargeRate(BigDecimal vatChargeRate) {
		this.vatChargeRate = vatChargeRate;
	}

	public BigDecimal getVatCharge() {
		return vatCharge;
	}

	public void setVatCharge(BigDecimal vatCharge) {
		this.vatCharge = vatCharge;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public String getPaymentRefNo() {
		return paymentRefNo;
	}

	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
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

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
	
}

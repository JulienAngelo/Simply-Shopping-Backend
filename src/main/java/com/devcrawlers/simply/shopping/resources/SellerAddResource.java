package com.devcrawlers.simply.shopping.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SellerAddResource {

	@NotBlank(message = "common.not-null")
	@Size(max = 40, message = "common-name.size")
	private String firstName;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 40, message = "common-name.size")
	private String lastName;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 80, message = "common-name.size")
	private String fullName;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 40, message = "common-name.size")
	private String addresLine1;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 40, message = "common-name.size")
	private String addresLine2;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 40, message = "common-name.size")
	private String addresLine3;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 80, message = "common-name.size")
	private String email;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 15, message = "common-name.size")
	private String mobile;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 15, message = "common-name.size")
	private String landline;
	
	@NotBlank(message = "common.not-null")
	@Pattern(regexp = "^$|[0-9]+", message = "common-numeric.pattern")
	private String userId;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 12, message = "common-name.size")
	private String nic;
	
	@NotBlank(message = "common.not-null")
	@Size(max = 20, message = "common-name.size")
	private String dob;
	
	@NotBlank(message = "common.not-null")
	@Pattern(regexp = "ACTIVE|INACTIVE", message = "common-status.pattern")
	private String status;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddresLine1() {
		return addresLine1;
	}

	public void setAddresLine1(String addresLine1) {
		this.addresLine1 = addresLine1;
	}

	public String getAddresLine2() {
		return addresLine2;
	}

	public void setAddresLine2(String addresLine2) {
		this.addresLine2 = addresLine2;
	}

	public String getAddresLine3() {
		return addresLine3;
	}

	public void setAddresLine3(String addresLine3) {
		this.addresLine3 = addresLine3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

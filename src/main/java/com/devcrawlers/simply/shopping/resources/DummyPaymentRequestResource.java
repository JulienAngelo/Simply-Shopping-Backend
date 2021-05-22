package com.devcrawlers.simply.shopping.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DummyPaymentRequestResource {
	
	@NotBlank(message = "{common.not-null}")
	@Size(min = 16, max = 16, message = "{card-num.size}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String cardNumber;
	
	@NotBlank(message = "{common.not-null}")
	@Size(min = 3, max = 3, message = "{card-cv.size}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String cvCode;
	
	@NotBlank(message = "{common.not-null}")
	@Size(min = 2, max = 2, message = "{card-year.size}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String year;
	
	@NotBlank(message = "{common.not-null}")
	@Size(min = 2, max = 2, message = "{card-month.size}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String month;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String payment;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvCode() {
		return cvCode;
	}

	public void setCvCode(String cvCode) {
		this.cvCode = cvCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}
package com.idan.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer{
	
	private long customerId;
	private String customerName;
	private String customerPassword;
	private String customerEmail;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long id) {
		this.customerId = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String password) {
		this.customerPassword = password;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Customer() {
	}
	public Customer(String customerName, String customerPassword, String customerEmail) {
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
	}
	public Customer(long customerId, String customerName, String customerPassword, String customerEmail) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", customerEmail=" + customerEmail + "]";
	}
	
	public String toJson() {
		String JsonStr = "{ \"customerId\": "+customerId+", \"customerName\": \""+customerName+"\", \"customerPassword\":\""+customerPassword+"\", \"customerEmail\":\"" + customerEmail + "\" }";
		return JsonStr;
	}
	
	
	

}

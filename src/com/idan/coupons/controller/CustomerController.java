package com.idan.coupons.controller;

import java.util.ArrayList;
import java.util.List;

import com.idan.coupons.beans.Company;
import com.idan.coupons.beans.Customer;
//import com.idan.coupons.dao.CompanyDao;
//import com.idan.coupons.dao.CouponDao;
import com.idan.coupons.dao.CustomerDao;
import com.idan.coupons.enums.ErrorType;
import com.idan.coupons.enums.InputErrorType;
import com.idan.coupons.exceptions.ApplicationException;
import com.idan.coupons.utils.DateUtils;
import com.idan.coupons.utils.ValidationUtils;

public class CustomerController {
	
//	private CouponDao couponDao;
//	private CompanyDao companyDao;
	private CustomerDao customerDao;
	
	public CustomerController(){
//		this.couponDao=new CouponDao();
//		this.companyDao=new CompanyDao();
		this.customerDao=new CustomerDao();
	}
	
	public void createCustomer(Customer customer) throws ApplicationException {
		
		validateCreateCustomer(customer);
		
		//If we didn't catch any exception, we call the 'createCoupon' method.
		this.customerDao.createCustomer(customer);
	}
	
	public void removeCustomerByCustomerID(Long customerID) throws ApplicationException {
		if(customerID==null) {
			throw new ApplicationException(ErrorType.BAD_INPUT, DateUtils.getCurrentDateAndTime()
					+"  Bad input inserted, null value.");
		}
//		this.couponDao.removeCustomerPurchasesByCustomerID(customerID);
		this.customerDao.removeCustomerByCustomerID(customerID);
		
	}
	
	public void updateCustomer(Customer customer) throws ApplicationException {
		
		validateUpdateCustomer(customer);
		
		//If we didn't catch any exception, we call the 'createCoupon' method.
		this.customerDao.updateCustomer(customer);
		
	}
	
	public Customer getCustomerByCustomerId(Long customerID) throws ApplicationException {
		if(customerID==null) {
			throw new ApplicationException(ErrorType.BAD_INPUT, DateUtils.getCurrentDateAndTime()
					+"  Bad input inserted, null value.");
		}
		Customer customer = this.customerDao.getCustomerByCustomerId(customerID);
		if (customer == null) {
			throw new ApplicationException(ErrorType.NO_RETURN_OBJECT, DateUtils.getCurrentDateAndTime()
					+" No customer with ID" + customerID + ".");
		}
		
		return customer;
	}
	
	public List<Customer> getCustomersByCustomerName(String customerName) throws ApplicationException {
		if (!ValidationUtils.isValidNameFormat(customerName)) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, DateUtils.getCurrentDateAndTime()
					+" Not valid name format" + customerName + ".");
		}
		
		List<Customer> customers = customerDao.getCustomersByCustomerName(customerName);
		
		if (customers.isEmpty()) {
			throw new ApplicationException(ErrorType.NO_RETURN_OBJECT, DateUtils.getCurrentDateAndTime()
					+" No customer with name" + customerName + ".");
		}
		
		return customers;
	}

	public Customer getCustomerByCustomerEmail(String customerEmail) throws ApplicationException {
		if (!ValidationUtils.isValidEmailFormat(customerEmail)) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, DateUtils.getCurrentDateAndTime()
					+" Not valid name format" + customerEmail + ".");
		}
		
		Customer customer = customerDao.getCustomerByCustomerEmail(customerEmail);
		
		if (customer == null) {
			throw new ApplicationException(ErrorType.NO_RETURN_OBJECT, DateUtils.getCurrentDateAndTime()
					+" No customer with name" + customerEmail + ".");
		}
		
		return customer;
	}

	public List<Customer> getAllCustomers() throws ApplicationException{
		
		List<Customer> customers = this.customerDao.getAllCustomers();
		
		if(customers.isEmpty()) {
			throw new ApplicationException(ErrorType.NO_RETURN_OBJECT, DateUtils.getCurrentDateAndTime()
					+" No customers in data base.");
		}
		
		return customers;
		
	}

	public Customer login (String customerEmail, String customerPassword) throws ApplicationException {
		
		validateCustomer(new Customer("Valid Name", customerPassword, customerEmail));
		
		return this.customerDao.login(customerEmail, customerPassword);
	}

	private void validateUpdateCustomer(Customer customer) throws ApplicationException {

		validateCustomer(customer);
		
		if (this.customerDao.isCustomerEmailExistForUpdate(customer.getCustomerId(), customer.getCustomerEmail())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS, DateUtils.getCurrentDateAndTime()
					+" Update customer has failed."
					+"\nThe user attempted to update a customer using an Email that is already in use."
					+"\nCustomer Name="+customer.getCustomerName());
		}
		
	}

	private void validateCreateCustomer(Customer customer) throws ApplicationException {

		validateCustomer(customer);
		
		if (this.customerDao.isCustomerExistByEmail(customer.getCustomerEmail())) {
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS, DateUtils.getCurrentDateAndTime()
					+" Create customer has failed."
					+"\nThe user attempted to create a new customer using an Email that is already in use."
					+"\nCustomer Email="+customer.getCustomerEmail());
		}
		
	}

	private void validateCustomer(Customer customer) throws ApplicationException {

		List<InputErrorType> errorTypes = new ArrayList<>();
		
		if(!ValidationUtils.isValidNameFormat(customer.getCustomerName())) {
			errorTypes.add(InputErrorType.INVALID_NAME);
		}
		
		if(!ValidationUtils.isValidPasswordFormat(customer.getCustomerPassword())) {
			errorTypes.add(InputErrorType.INVALID_PASSWORD);
		}
		if(!ValidationUtils.isValidEmailFormat(customer.getCustomerEmail())) {
			errorTypes.add(InputErrorType.INVALID_EMAIL);
		}
		
		if(!errorTypes.isEmpty()) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, DateUtils.getCurrentDateAndTime()
					+" Validating customer input has failed."
					+"\nOne or more of the fields are incorrect.", errorTypes);
		}
		
	}

}

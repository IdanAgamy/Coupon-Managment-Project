package com.idan.coupons.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.idan.coupons.beans.Customer;
import com.idan.coupons.controller.CustomerController;
import com.idan.coupons.enums.ErrorType;
import com.idan.coupons.exceptions.ApplicationException;
import com.idan.coupons.utils.CookieUtil;
import com.idan.coupons.utils.DateUtils;
import com.idan.coupons.utils.ValidationUtils;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {

	private CustomerController customerController;

	public CustomerApi() {
		customerController = new CustomerController();
	}

	/**
	 * Getting list of all customers in DB.
	 * @return List collection of all the customers in the customer table.
	 * @throws ApplicationException
	 */
	@GET
	//http://localhost:8080/CouponPhaseTwo/rest/customers
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerController.getAllCustomers();
	}

	/**
	 * Getting information of a customer.
	 * @param request - an HttpServletRequest object, for validating use.
	 * @param customerId - a long parameter represent the ID of the requested customer.
	 * @return Customer object of the requested customer.
	 * @throws ApplicationException
	 */
	@GET
	@Path("/{customerId}")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/2
	public Customer getUser(@Context HttpServletRequest request, @PathParam("customerId") long customerId) throws ApplicationException{
		// Will update the company in the DB only if the changes are made by the admin or the same customer.
		ValidationUtils.ValidateUser(request, customerId);
		return customerController.getCustomerByCustomerId(customerId);
	}

	/**
	 * Getting information of a customer by name.
	 * @param customerName - a String parameter represent the name of the requested customer.
	 * @return List of customers object of the requested customer name.
	 * @throws ApplicationException
	 */
	@GET
	@Path("/byCustomerName")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/byCustomerName?customerName=patrick
	public List<Customer> getCustomerByName(@QueryParam("customerName") String customerName) throws ApplicationException{
		//System.out.println(customerController.getCustomersByCustomerName(customerName));
		return customerController.getCustomersByCustomerName(customerName);
	}

	/**
	 * Getting information of a customer by e-mail.
	 * @param customerEmail - a String parameter represent the e-mail of the requested customer.
	 * @return Customer object of the requested customer.
	 * @throws ApplicationException
	 */
	@GET
	@Path("/{customerEmail}/byCustomerEmail")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/Picard@EnterpriseD/byCustomerEmail
	public Customer getCustomerByEmail(@PathParam("customerEmail") String customerEmail) throws ApplicationException{
		//System.out.println(customerController.getCustomerByCustomerEmail(customerEmail));
		return customerController.getCustomerByCustomerEmail(customerEmail);
	}

	/**
	 * Creating a customer in the DB.
	 * @param customer - the customer as a Customer object to add to the DB.
	 * @throws ApplicationException
	 */
	@POST
	//http://localhost:8080/CouponPhaseTwo/rest/customers/
	public void createCustomer(Customer customer) throws ApplicationException{
		System.out.println(customer);
		customerController.createCustomer(customer);
	}

	/**
	 * Updating a customer in the customer table. All the fields will be updated according to the ID of the customer object.
	 * @param request - an HttpServletRequest object, for validating use.
	 * @param customer - the customer as a Customer object to be updated in the DB.
	 * @throws ApplicationException
	 */
	@PUT
	//http://localhost:8080/CouponPhaseTwo/rest/customers/
	public void updateUser (@Context HttpServletRequest request, Customer customer) throws ApplicationException{
		// Will update the customer in the DB only if the changes are made by the admin or the same customer.
		Long customerID = customer.getCustomerId();
		ValidationUtils.ValidateUser(request, customerID);
		System.out.println(customer);
		customerController.updateCustomer(customer);
	}

	/**
	 * Removing customer from customer table.
	 * @param request - an HttpServletRequest object, for validating use.
	 * @param customerId - a long parameter represent the ID of the requested customer.
	 * @throws ApplicationException
	 */
	@DELETE
	@Path("/{customerId}")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/2
	public void removeUser(@Context HttpServletRequest request, @PathParam("customerId") long customerId) throws ApplicationException{
		// Will update the customer in the DB only if the changes are made by the admin or the same customer.
		ValidationUtils.ValidateUser(request, customerId);
		System.out.println(customerId);
		customerController.removeCustomerByCustomerID(customerId);

	}

	/* Json object
	 * DON'T FORGET: add header for JSon!!!!!!!!!!!!!!!!!one!!!!1!
	 {
		"customerId": 1,
			"customerName": "lord ainz",
			"customerPassword":"asdf1234",
			"customerEmail":"a@b"
	}
	 */


}

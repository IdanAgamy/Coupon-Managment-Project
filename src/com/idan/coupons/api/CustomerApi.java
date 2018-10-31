package com.idan.coupons.api;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.idan.coupons.beans.Customer;
import com.idan.coupons.controller.CustomerController;
import com.idan.coupons.exceptions.ApplicationException;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {
	
	private CustomerController customerController;
	
	public CustomerApi() {
		customerController = new CustomerController();
	}
	
	@GET
	//http://localhost:8080/CouponPhaseTwo/rest/customers
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerController.getAllCustomers();
	}
	
	@GET
	@Path("/{customerId}")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/2
	public Customer getUser(@PathParam("customerId") long customerId) throws ApplicationException, JSONException{
		//System.out.println(customerController.getCustomerByCustomerId(customerId));
		return customerController.getCustomerByCustomerId(customerId);
	}
	
	@GET
	@Path("/{customerName}/byName")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/patrick/byName
	public List<Customer> getCustomerByName(@PathParam("customerName") String customerName) throws ApplicationException{
		//System.out.println(customerController.getCustomersByCustomerName(customerName));
		return customerController.getCustomersByCustomerName(customerName);
	}
	

	@GET
	@Path("/{customerEmail}/byEmail")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/Picard@EnterpriseD/byEmail
	public Customer getCustomerByEmail(@PathParam("customerEmail") String customerEmail) throws ApplicationException{
		//System.out.println(customerController.getCustomerByCustomerEmail(customerEmail));
		return customerController.getCustomerByCustomerEmail(customerEmail);
	}

	@POST
	//http://localhost:8080/CouponPhaseTwo/rest/customers/
	public void createCustomer(Customer customer) throws ApplicationException{
		System.out.println(customer);
		customerController.createCustomer(customer);
	}
	
	@PUT
	//http://localhost:8080/CouponPhaseTwo/rest/customers/
	public void updateUser (Customer customer) throws ApplicationException{
		System.out.println(customer);
		customerController.updateCustomer(customer);
	}
	
	@DELETE
	@Path("/{customerId}")
	//http://localhost:8080/CouponPhaseTwo/rest/customers/2
	public void removeUser(@PathParam("customerId") long customerId) throws ApplicationException{
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

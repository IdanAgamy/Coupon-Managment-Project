package com.idan.coupons.api;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.idan.coupons.beans.Company;
import com.idan.coupons.beans.Customer;
import com.idan.coupons.beans.UserLoginInfo;
import com.idan.coupons.controller.CompanyController;
import com.idan.coupons.controller.CustomerController;
import com.idan.coupons.enums.UserType;
import com.idan.coupons.exceptions.ApplicationException;
import com.idan.coupons.utils.CookieUtil;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginApi {
	
	/**
	 * method for debugging purpose only
	 * TODO - delete method
	 */
	@GET
	public void hhhyy(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.println(c.getName() + " " + c.getValue());

			} 
		}
		
		HttpSession session = request.getSession(false);
		System.out.println(session);
	}
	
	/**
	 * logging in to the web site.
	 * @param request - an HttpServletRequest object, for creating session and cookies
	 * @param response - an HttpServletResponse object, for setting the response to the client.
	 * @param userLoginInfo - UserLoginInfo object with the login parameters
	 * @return - response with the status for the client
	 * @throws ApplicationException
	 */
	@POST
	public Response login(@Context HttpServletRequest request, @Context HttpServletResponse response, UserLoginInfo userLoginInfo) throws ApplicationException {
		System.out.println(userLoginInfo);
		
		if (userLoginInfo != null) {
			// Validating the admin login.
			if(userLoginInfo.getUserType() == UserType.ADMIN && userLoginInfo.getName().equals("admin") && userLoginInfo.getPassword().equals("1234") && userLoginInfo.getEmail().equals("admin@coupons")) {
				request.getSession();
				// Adding cookies for admin.
				response.addCookie(new Cookie("userType",UserType.ADMIN.name()));
				return Response.status(200).build();
			}
			// Validating a company login.
			if(userLoginInfo.getUserType() == UserType.COMPANY) {
				CompanyController companyConroller = new CompanyController();
				Company company = companyConroller.login(userLoginInfo.getName(), userLoginInfo.getPassword());
				if(company != null) {
					request.getSession();					
					List<Cookie> loginCookies = CookieUtil.loginCookies(company);
					response = CookieUtil.addCookies(response, loginCookies);	
					return Response.status(200).build();
				}
				return Response.status(401).build();
			}
			// Validating a customer login.
			if(userLoginInfo.getUserType() == UserType.CUSTOMER) {
				CustomerController customerController = new CustomerController();
				Customer customer = customerController.login(userLoginInfo.getEmail(), userLoginInfo.getPassword());
				if(customer != null) {
					request.getSession();	
					List<Cookie> loginCookies = CookieUtil.loginCookies(customer);
					response = CookieUtil.addCookies(response, loginCookies);
					return Response.status(200).build();
				}
				return Response.status(401).build();
			}
		}
		return Response.status(401).build();
		
	}
	
	

	
	
	/*
{
"name":"patric",
			"userType": "CUSTOMER",
			"password":"asdf1234",
			"email":"a@b"
	}
	 */
	

}

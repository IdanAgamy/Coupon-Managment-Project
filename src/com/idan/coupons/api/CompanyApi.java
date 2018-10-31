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

import com.idan.coupons.beans.Company;
import com.idan.coupons.controller.CompanyController;
import com.idan.coupons.exceptions.ApplicationException;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyApi {
	
	private CompanyController companyController;
	
	public CompanyApi() {
		this.companyController = new CompanyController();
	}
	
	@GET
	//http://localhost:8080/CouponPhaseTwo/rest/companies/
	public List<Company> getAllCompanies() throws ApplicationException {
		List<Company> companies = companyController.getAllCompanies();
//		System.out.println(companies);
		return companies;
	}


	@GET
	@Path("/{companyId}")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/5
	public Company getUser(@PathParam("companyId") long companyId) throws ApplicationException{
//		System.out.println(companyController.getCompanyByComapnyId(companyId));
		return companyController.getCompanyByComapnyId(companyId);
	}
	
	
	@GET
	@Path("/byName")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/coca-cola/byName
	public Company getCompanyByName(@QueryParam("companyName") String companyName) throws ApplicationException{
//		System.out.println(companyController.getCompanyByComapnyName(companyName));
		return companyController.getCompanyByComapnyName(companyName);
	}
	

	@GET
	@Path("/acc/byEmail")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/HP@gmail.com/byEmail
	public Company getCompanyByEmail(@QueryParam("companyEmail") String companyEmail) throws ApplicationException{
//		System.out.println(companyController.getCompanyByComapnyEmail(companyEmail));
		return companyController.getCompanyByComapnyEmail(companyEmail);
	}
	
	@POST
	public void createCompany(Company company) throws ApplicationException{
		System.out.println(company);
		companyController.createCompany(company);
	}
	
	@PUT
	public void updateUser (Company company) throws ApplicationException{
		
		System.out.println(company);
		companyController.updateCompany(company);
	}
	
	@DELETE
	@Path("/{companyId}")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/10
	public void removeUser(@PathParam("companyId") long companyId) throws ApplicationException{
		
		System.out.println(companyId);
		companyController.removeCompanyByCompanyID(companyId);
	}
	
	private boolean isAccessToChange(@Context HttpServletRequest request, long companyId) {
		String userType = (String) request.getAttribute("userType");
		long userID = Long.parseLong((String) request.getAttribute("userID"));
		
		
		return false;
	}
	
	
	/*
	 * Content-Type      application/json
	 *  Json object
	 {
		"companyName": "abba",
		"companyPassword": "aa555456",
		"companyEmail": "a@b",
		"companyId":?
	}
	 */

}

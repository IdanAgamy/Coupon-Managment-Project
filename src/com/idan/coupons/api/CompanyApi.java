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
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.idan.coupons.beans.Company;
import com.idan.coupons.controller.CompanyController;
//import com.idan.coupons.enums.ErrorType;
import com.idan.coupons.exceptions.ApplicationException;
//import com.idan.coupons.utils.CookieUtil;
//import com.idan.coupons.utils.DateUtils;
import com.idan.coupons.utils.ValidationUtils;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyApi {

	private CompanyController companyController;

	public CompanyApi() {
		this.companyController = new CompanyController();
	}

	/**
	 * Getting list of all companies from DB.
	 * @return List collection of all the companies in the company table
	 * @throws ApplicationException
	 */
	@GET
	//http://localhost:8080/CouponPhaseTwo/rest/companies/
	public List<Company> getAllCompanies() throws ApplicationException {
		List<Company> companies = companyController.getAllCompanies();
		//		System.out.println(companies);
		return companies;
	}

	/**
	 * Getting information of a company.
	 * @param companyId - a long parameter represent the ID of the requested company.
	 * @return Company object of the requested company.
	 * @throws ApplicationException
	 */
	@GET
	@Path("/{companyId}")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/5
	public Company getCompanyByComapnyId(@PathParam("companyId") long companyId) throws ApplicationException{
		//		System.out.println(companyController.getCompanyByComapnyId(companyId));
		return companyController.getCompanyByComapnyId(companyId);
	}

	/**
	 * Getting information of a company by name.
	 * @param companyName - a String parameter represent the name of the requested company.
	 * @return Company object of the requested company.
	 * @throws ApplicationException
	 */
	@GET
	@Path("/{companyName}/byCompanyName")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/coca cola/byCompanyName
	public Company getCompanyByName(@PathParam("companyName") String companyName) throws ApplicationException{
		//		System.out.println(companyController.getCompanyByComapnyName(companyName));
		return companyController.getCompanyByComapnyName(companyName);
	}

	/**
	 * Getting information of a company by Email.
	 * @param companyName - a String parameter represent the e-mail of the requested company.
	 * @return Company object of the requested company.
	 * @throws ApplicationException
	 */
	@GET
	@Path("/{companyEmail}/byCompanyEmail")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/HP@gmail.com/byCompanyEmail
	public Company getCompanyByEmail(@PathParam("companyEmail") String companyEmail) throws ApplicationException{
		//		System.out.println(companyController.getCompanyByComapnyEmail(companyEmail));
		return companyController.getCompanyByComapnyEmail(companyEmail);
	}

	/**
	 * Creating a company in the DB.
	 * @param company - the company as a Company object to add to the DB.
	 * @throws ApplicationException
	 */
	@POST
	public void createCompany(Company company) throws ApplicationException{
		if (company != null) {
			System.out.println(company);
			companyController.createCompany(company);
		}
	}

	/**
	 * Updating a company in the company table. All the fields will be updated according to the ID of the Company object.
	 * @param request - an HttpServletRequest object, for validating use.
	 * @param company - the company as a Company object to be updated in the DB.
	 * @throws ApplicationException
	 */
	@PUT
	public void updateUser (@Context HttpServletRequest request, Company company) throws ApplicationException{
		// Will update the company in the DB only if the changes are made by the admin or the same company.
		ValidationUtils.ValidateUser(request, company.getCompanyId());
		//			System.out.println(company);
		companyController.updateCompany(company);
	}

	/**
	 * Removing company from company table.
	 * @param request - an HttpServletRequest object, for validating use.
	 * @param companyId - a long parameter represent the ID of the requested company.
	 * @throws ApplicationException
	 */
	@DELETE
	@Path("/{companyId}")
	//http://localhost:8080/CouponPhaseTwo/rest/companies/10
	public void removeUser(@Context HttpServletRequest request, @PathParam("companyId") long companyId) throws ApplicationException{

		// Will update the company in the DB only if the changes are made by the admin or the same company.
		ValidationUtils.ValidateUser(request, companyId);
		System.out.println(companyId);
		companyController.removeCompanyByCompanyID(companyId);
	}




	/* For debugging use only:
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

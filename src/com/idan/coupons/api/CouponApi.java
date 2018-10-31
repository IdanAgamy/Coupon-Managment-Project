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

import com.idan.coupons.beans.Coupon;
import com.idan.coupons.controller.CouponController;
import com.idan.coupons.enums.CouponType;
import com.idan.coupons.enums.OrderType;
import com.idan.coupons.exceptions.ApplicationException;

@Path("/coupons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CouponApi {
	

	CouponController couponController;
	
	public CouponApi() {
		super();
		this.couponController = new CouponController();
	}
	
	
	@GET
	//http://localhost:8080/CouponPhaseTwo/rest/coupons
	public List<Coupon> getAllCoupons() throws ApplicationException {
		List<Coupon> coupons = couponController.getAllCoupons();
		//System.out.println(coupons);
		return coupons;
	}
	
	@GET
	@Path("/{couponId}")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/5
	public Coupon getCouponByCouponId(@PathParam("couponId") long couponId) throws ApplicationException {
		//System.out.println(couponId);
		//System.out.println(couponController.getCouponByCouponId(couponId));
		return couponController.getCouponByCouponId(couponId);
	}
	
	
	@POST
	//http://localhost:8080/CouponPhaseTwo/rest/coupons
	public void createCoupon(Coupon coupon) throws ApplicationException {
//		System.out.println(coupon);
		couponController.createCoupon(coupon);
	}
	
	@PUT
	//http://localhost:8080/CouponPhaseTwo/rest/coupons
	public void updateCoupon(Coupon coupon) throws ApplicationException {
//		System.out.println(coupon);
		couponController.updateCoupon(coupon);
	}
	
	@DELETE
	@Path("/{couponId}")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/?
	public void removeCouponByID(@PathParam("couponId") long couponId) throws ApplicationException {
		System.out.println(couponId);
		couponController.removeCouponByID(couponId);
	}
	
	@GET
	@Path("/{couponType}/byType")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/Food/byType
	public List<Coupon> getCompanyByType(@PathParam("couponType") CouponType couponType) throws ApplicationException{
		//System.out.println(couponController.getCouponByType(couponType));
		return couponController.getCouponByType(couponType);
	}

	@GET
	@Path("/{orderType}/priceByOrderType")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/ASCENDING/priceByOrderType
	public List<Coupon> getCouponInOrderByPrice(@PathParam("orderType") OrderType orderType) throws ApplicationException{
//		System.out.println(couponController.getCouponInOrderByPrice(orderType));
		return couponController.getCouponInOrderByPrice(orderType);
	}
	
	@GET
	@Path("/upToPrice")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/upToPrice?price=200
	public List<Coupon> getCouponsUpToPrice(@QueryParam("price") double prise) throws ApplicationException{
//		System.out.println(couponController.getCouponsUpToPrice(price));
		return couponController.getCouponsUpToPrice(prise);
	}

	@GET
	@Path("/upToEndDate")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/upToEndDate?endDate=2019-12-12
	public List<Coupon> getCouponsUpToEndDate(@QueryParam("endDate") String endDate) throws ApplicationException{
//		System.out.println(couponController.getCouponsUpToEndDate(endDate));
		return couponController.getCouponsUpToEndDate(endDate);
	}
	
	@GET
	@Path("/byCompanyID")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/byCompanyID?companyID=4
	public List<Coupon> getCouponsByCompanyID(@QueryParam("companyID") Long companyID) throws ApplicationException{
//		System.out.println(couponController.getCouponsByCompanyID(companyID));
		return couponController.getCouponsByCompanyID(companyID);
	}

	@POST
	@Path("/purchasedCoupons")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/purchasedCoupons
	public void getCouponsByCustomerID() throws ApplicationException{
		//TODO- implement getting customerID from cookie
		Long customerID = 2L;
		System.out.println(couponController.getCouponsByCustomerID(customerID));
	}
	
	@POST
	@Path("/{couponId}/buyCoupon")
	//http://localhost:8080/CouponPhaseTwo/rest/coupons/3/buyCoupon
	public void buyCoupon(@Context HttpServletRequest request, @PathParam("couponId") Long couponID) throws ApplicationException{
		
		Long customerID = 3L;
		couponController.buyCoupon(customerID, couponID);
	}

	
	
	/*
	 * Content-Type      application/json
	 *  Json object
	 {
		"couponId": ?,
		"couponTitle": "a",
		"couponStartDate": "2000-10-10",
		"couponEndDate": "2000-10-11",
		"couponAmount": 2,
		"couponType": "Food",
		"couponMessage": "a",
		"couponPrice": 2.2,
		"couponImage": "a",
		"companyID": 4
	}
	 */
	

}

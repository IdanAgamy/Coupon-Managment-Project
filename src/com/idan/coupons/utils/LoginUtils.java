package com.idan.coupons.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginUtils {

	public static boolean isDefultAccess(HttpServletRequest req, HttpSession session, String pageRequest) {
		return session != null || pageRequest.endsWith("/login") || 
							 (pageRequest.endsWith("/coupons") &&  req.getMethod().equals("GET")) ||
							 pageRequest.endsWith("/byCouponType")||
							 pageRequest.endsWith("/upToPrice")||
							 pageRequest.endsWith("/upToEndDate")||
							 pageRequest.endsWith("/byCompanyID")||
							 (pageRequest.endsWith("/customers") &&  req.getMethod().equals("POST") ) || 
							 (pageRequest.endsWith("/companies") && (req.getMethod().equals("GET") || req.getMethod().equals("POST"))) ||
							 pageRequest.endsWith("/byCompanyName");
	}

}

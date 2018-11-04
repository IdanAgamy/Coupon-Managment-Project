package com.idan.coupons.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idan.coupons.beans.Company;
import com.idan.coupons.beans.Customer;
import com.idan.coupons.enums.ErrorType;
import com.idan.coupons.enums.UserType;
import com.idan.coupons.exceptions.ApplicationException;

public class CookieUtil {

	public static List<Cookie> loginCookies(Company company) {

		List<Cookie> loginCookies = new ArrayList<>();

		loginCookies.add(new Cookie("userType",UserType.COMPANY.name()));
		loginCookies.add(new Cookie("userID",Long.toString(company.getCompanyId())));
		loginCookies.add(new Cookie("userName",company.getCompanyName()));
		loginCookies.add(new Cookie("userEmail",company.getCompanyEmail()));

		return loginCookies;
	}

	public static List<Cookie> loginCookies(Customer customer) {

		List<Cookie> loginCookies = new ArrayList<>();

		loginCookies.add(new Cookie("userType",UserType.CUSTOMER.name()));
		loginCookies.add(new Cookie("userID",Long.toString(customer.getCustomerId())));
		loginCookies.add(new Cookie("userName",customer.getCustomerName()));
		loginCookies.add(new Cookie("userEmail",customer.getCustomerEmail()));

		return loginCookies;
	}

	public static HttpServletResponse addCookies(HttpServletResponse response, List<Cookie> loginCookies) {
		for(Cookie c:loginCookies) {
			response.addCookie(c);
		}
		return response;
	}

	

}

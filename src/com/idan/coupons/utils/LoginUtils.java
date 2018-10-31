//package com.idan.coupons.utils;
//
//import com.idan.coupons.beans.UserLoginInfo;
//import com.idan.coupons.controller.CompanyController;
//import com.idan.coupons.controller.CustomerController;
//import com.idan.coupons.enums.UserType;
//import com.idan.coupons.exceptions.ApplicationException;
//
//public class LoginUtils {
//
//	public static boolean login(UserLoginInfo userLoginInfo) throws ApplicationException {
//
//		if(userLoginInfo.getUserType()==UserType.COMPANY) {
//			CompanyController companyController = new CompanyController();
//			return companyController.login(userLoginInfo.getName(), userLoginInfo.getPassword());
//		}
//		
//		if(userLoginInfo.getUserType()==UserType.CUSTOMER) {
//			CustomerController customerController = new CustomerController();
//			return customerController.login(userLoginInfo.getEmail(), userLoginInfo.getPassword());
//		}
//		
//		return false;
//	}
//
//}

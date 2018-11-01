package com.idan.coupons.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.idan.coupons.beans.ApplicationError;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ApplicationError error;
		 exception.printStackTrace();
		if (exception instanceof ApplicationException) {
			ApplicationException  applicationException = (ApplicationException) exception;
			error = new ApplicationError(applicationException.getType().getNumber(),applicationException.getType().name(), exception.getMessage());
			if (error.getErrorCode() == 603) {
				error.setInputErrorTypes(applicationException.getTypes()); 
			}
			return Response.status(error.getErrorCode()).entity(error).build();
		}
		
		//TODO implement logger.
		error = new ApplicationError(601, "General error",exception.getMessage());
		return Response.status(error.getErrorCode()).entity(error).build();
	}

}

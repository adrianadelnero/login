/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.authentication.business.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aromano
 */
@ControllerAdvice
public class GlobalRestExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Object> handleException(Exception e) {
		LOGGER.info("handleException {} {}", e.getClass(), e.getMessage());
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}

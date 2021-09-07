/**
 * Bongsus
 */
package com.bongsus.www.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description : 에러 컨트롤러<br>
 * Date : 2021. 9. 7.<br>
 * History :<br>
 * - 2021. 9. 7.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

//@Controller
public class CustomErrorController implements ErrorController {
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());
			
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404error";
			} else {
				return "error/error";
			}
		}
		
		return "error/error";
		
	}

}

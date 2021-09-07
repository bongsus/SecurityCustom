/**
 * Bongsus
 */
package com.bongsus.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description :<br>
 * Date : 2021. 9. 7.<br>
 * History :<br>
 * - 2021. 9. 7.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	
	public ForbiddenException(String message) {
        super(message);
    }

}

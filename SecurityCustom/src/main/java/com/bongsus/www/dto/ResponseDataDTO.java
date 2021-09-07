/**
 * Bongsus
 */
package com.bongsus.www.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description :<br>
 * Date : 2021. 9. 7.<br>
 * History :<br>
 * - 2021. 9. 7.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@Getter
@Setter
@ToString
public class ResponseDataDTO {
	
	private String code;
	private String message;
	private Object item;

}

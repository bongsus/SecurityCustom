/**
 * Bongsus
 */
package com.bongsus.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

/**
 * Description : 메인 컨트롤러<br>
 * Date : 2021. 9. 6.<br>
 * History :<br>
 * - 2021. 9. 6.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@Controller
@AllArgsConstructor
public class MainController {
	
	/**
	 * 메인
	 * 
	 * @return	메인 화면
	 */
	@GetMapping("/")
	public String index() {
		
		return "index";
		
	}
	
	/**
	 * 관리자
	 *  
	 * @return	관리자 화면
	 */
	@GetMapping("/admin")
	public void dispAdmin() {}

}

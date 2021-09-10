/**
 * Bongsus
 */
package com.bongsus.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/viewTest")
	public void viewTest() {
		
	}
	
	@PostMapping("/viewTest")
	public String doViewTest(@RequestParam String pageNum, Model model) {
		
		if ("2".equals(pageNum)) {
			List<String> list = new ArrayList<String>();
			list.add("one");
			list.add("two");
			list.add("three");
			list.add("four");
			model.addAttribute("list", list);
			
			return "/viewTest2";
		} else if ("1".equals(pageNum)) {
			String str = "1번 페이지 데이터 입니다.";
			model.addAttribute("str", str);
			
			return "/viewTest1";
		} else {
			return "/viewTest";
		}
		
		
	}

}

/**
 * Bongsus
 */
package com.bongsus.www.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bongsus.www.domain.ResponseDataCode;
import com.bongsus.www.dto.ResponseDataDTO;
import com.bongsus.www.util.YamlRead;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Description : 로그인 성공 핸들러<br>
 * Date : 2021. 9. 7.<br>
 * History :<br>
 * - 2021. 9. 7.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private YamlRead yamlRead;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();	//JSON 변경용
    	
    	ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    	responseDataDTO.setCode(yamlRead.getSuccess());
    	
    	String prevPage = request.getSession().getAttribute("prevPage").toString();	//이전 페이지 가져오기
    	 
    	Map<String, String> items = new HashMap<String,String>();	
    	items.put("url", prevPage);	// 이전 페이지 저장
    	responseDataDTO.setItem(items);
    	
    	response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
    }

}

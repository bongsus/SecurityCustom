/**
 * Bongsus
 */
package com.bongsus.www.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.bongsus.www.dto.ResponseDataDTO;
import com.bongsus.www.util.YamlRead;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Description :<br>
 * Date : 2021. 9. 7.<br>
 * History :<br>
 * - 2021. 9. 7.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private YamlRead yamlRead;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		ObjectMapper mapper = new ObjectMapper();	//JSON 변경용
    	
    	ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    	responseDataDTO.setCode(yamlRead.getError());
    	responseDataDTO.setMessage("아이디 혹은 비밀번호가 일치하지 않습니다.");
    	
    	response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
	
	}

}

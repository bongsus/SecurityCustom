package com.bongsus.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description : 롤 종류 정의<br>
 * Date : 2021. 9. 6.<br>
 * History :<br>
 * - 2021. 9. 6.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@AllArgsConstructor
@Getter
public enum Role {
	
	ADMIN("ROLE_ADMIN"),
	MEMBER("ROLE_MEMBER");
	
	private String value;

}

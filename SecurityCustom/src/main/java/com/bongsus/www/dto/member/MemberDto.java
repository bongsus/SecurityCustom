package com.bongsus.www.dto.member;

import java.time.LocalDateTime;

import com.bongsus.www.domain.entity.MemberEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Description : 회원 데이터 전송 객체<br>
 * Date : 2021. 9. 6.<br>
 * History :<br>
 * - 2021. 9. 6.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
	
	private Long id;
	private String email;
	private String password;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public MemberEntity toEntity() {
		return MemberEntity.builder().id(id).email(email).password(password).build();
	}
	
	@Builder
	public MemberDto(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

}

package com.bongsus.www.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bongsus.www.domain.Role;
import com.bongsus.www.domain.entity.MemberEntity;
import com.bongsus.www.domain.repository.MemberRepository;
import com.bongsus.www.dto.member.MemberDto;

import lombok.AllArgsConstructor;

/**
 * Description : 회원 서비스<br>
 * Date : 2021. 9. 6.<br>
 * History :<br>
 * - 2021. 9. 6.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
	
	private MemberRepository memberRepository;
	
	/**
	 * 회원가입
	 *  
	 * @param memberDto		회원정보
	 * @return				회원id
	 */
	@Transactional
	public Long joinUser(MemberDto memberDto) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		// 비밀번화 암호화
		
		return memberRepository.save(memberDto.toEntity()).getId();
		
	}

	/**
	 * 회원 상세정보 조회
	 */
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
		MemberEntity userEntity = userEntityWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (("bongsus@gmail.com").equals(userEmail)) {		// 권한 부여
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
		
	}

}

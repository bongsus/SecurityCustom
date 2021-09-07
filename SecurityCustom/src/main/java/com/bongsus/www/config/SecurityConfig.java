package com.bongsus.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bongsus.www.handler.CustomAuthenticationFailureHandler;
import com.bongsus.www.handler.CustomAuthenticationSuccessHandler;
import com.bongsus.www.service.member.MemberService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity		// Spring Security 설정 클래스 선언
@AllArgsConstructor		// 모든 필드값 포함 생성자 자동 생성
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// WebSecurityConfigurerAdapter 는 WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	

	private MemberService memberService;
	
	@Bean				// Service에서 사용할 수 있도록 Bean 등록
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();			
		// Spring Security에서 제공하는 비밀번호 암호화 객체
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// FilterChainProxy를 생성하는 filter
		
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");		
		// Security 무시 경로 설정 (일반적으로 resources/static directory)
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http 요청에 대한 웹 기반 보안 구성 설정
		
		http.authorizeRequests()	// 페이지 권한 설정
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/myinfo").hasRole("MEMBER")
				.antMatchers("/**").permitAll()
			.and()					// login 설정
				.formLogin()				// 1) form 기반 login	2) /login 접근 시 Spring Security에서 제공하는 login form을 사용할 수 있음
				.loginPage("/user/login")	// 1) custom login form을 사용할 경우	2) form action 값과 loginPage parameter 경로는 일치해야 함
				//.defaultSuccessUrl("/user/loginResult")
				.successHandler(customAuthenticationSuccessHandler)		// login 성공 handler
				.failureHandler(customAuthenticationFailureHandler)		// login 실패 handler
				.permitAll()
			.and()					// logout 설정
				.logout()						// /logout 접근 시 http Session 제거
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))		// custom logout 경로 설정 
				.logoutSuccessUrl("/user/logoutResult")
				.invalidateHttpSession(true)	// http session 초기화
				//.deleteCookies("COOKVAL")		// logout 시 특정 cookie 값 제거
			.and()					// 예외처리
				.exceptionHandling().accessDeniedPage("/user/denied");	// 접근 권한이 없을 시, login 페이지로 이동
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(memberService)		// login 인증처리를 위해 필요한 정보를 가져옴
			.passwordEncoder(passwordEncoder());	// 비밀번호 암호화
		
	}
	
}

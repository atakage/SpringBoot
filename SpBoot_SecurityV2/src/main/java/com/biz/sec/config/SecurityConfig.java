package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;

/*
 * spring security의 customizing 첫 번째 단계
 * security-context.xml에 설정했던 옵션들을 지정하는 클래스
 */



@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	private final SecurityUserServiceImpl sUserService;
	private final PasswordEncoder passwordEncoder;
	
	
	/*
	 * spring security는 연동 정책이 자체의 view template를 사용한다는 전제하에 모든 프로젝트가 진행되고 있음
	 * 1. form(입력)을 열기 위한 URL할 때 security는 csrf token을 함께 전송해서 다음에 POST로 값을 보낼 때 검증하는 절차를 수행하도록 되어 있음
	 *  jsp, thymeleaf에는 csrf token을 수신하여 input tag로 설정하는 기능이 자동으로 수행됨 submit을 했을 때 csrf token을 함께 전송해서 security가 올바른 요청인지 검증하는 절차가 수행됨
	 *  react, angular같은 별도의 view를 사용하여 연동할 때는 csrf를 처리할 수 있는 방법이 거의 없다
	 *  
	 *  따라서 react 등을 사용할 때는 csrf token을 검증하는 절차를 생략하고 사용할 수밖에 없음
	 *  .csrf().disable()을 적용하는 것
	 *  
	 *  이 정책을 적용하면 jsp, thymeleaf에서 csrf token 관련 코드에서 오류가 발생하여 정상적인 view가 보이지 않게 됨
	 *  
	 *  2. URL을 localhost:8080/admin과 같이 요청할 때 admin 요청에 대하여 ROLE을 적용시켜두면 login이 되어있지 않을 경우 미리 지정한 login form template이 보여지게 됨
	 *  이런 경우 react 등에서는 서버에 어떤 반응이 있는지를 전혀 인지할 수 없음 그러기 때문에 이 기능 또한 사용하지 않겠다고 선언
	 *  
	 *  react 등과 연동할 때는 모든 컨트롤러가 RestController이기 때문에 의미가 없는 기능이 됨
	 *  
	 *  
	 *  3. 기본 template를 사용할 때 jsp, sec, authority:, thymeleaf:sec  같은 library tag에서  로그인한 사용자 정보를 조회하는 코드가 있는데
	 *  이 코드들은 실제 security와 연동된 httpSession을 사용 
	 *  이 기능 또한 사용하지 않도록 정책설정을 함 spring과 react 등이 연동할 때는 jwt보안 기능을 사용
	 *  이 jwt 보안기능이 httpSession을 사용하게 되면 http 프로토콜 차원에서 문제를 일으킬 수 있음
	 * 	(오히려 jwt의 보안기능이 약화되는 현상)
	 * 
	 *  그래서 react와 연동할 때는 session을 사용하지 않도록 설정
	 *  session을 사용하지 않으면 기본 template에서는 사용자의 로그인 상태, ROLE 상태 등을 인지할 수 있는 방법이 없음
	 *  react와 기본 template를 연동할 때는 session 기능을 열어두고 react에서는 보안과 관련된 접근을 금지하고 보안이 필요없는 부분만 접근하도록 하여야 함
	 *  
	 * 
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		
		// react와 연동할 때 security 설정
		// 1. post 전송할 때 Token 연동하지 않음
		httpSecurity
		.csrf().disable()
		
		//2. 권한이 없을 때 자동으로 login form이 뜨는 것을 금지
		.httpBasic().disable().sessionManagement()
		
		
		//3. session을 사용한 security 정지 정책 실행
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		httpSecurity.authorizeRequests().antMatchers("/hello").permitAll().antMatchers("/login").permitAll().
		antMatchers("/admin").hasRole("ADMIN").antMatchers("/user/login").permitAll().antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests().and().formLogin().loginPage("/user/login").
		failureUrl("/user/login?error").usernameParameter("username").passwordParameter("password").and().logout().logoutSuccessUrl("/");
		
		
	}
	
	
	
	
	
	
	
	
	/*
	 * <bean><bean>을 대신하는 java 설정
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		
		auth.userDetailsService(sUserService).passwordEncoder(sUserService.getPasswordEncoder());
	}




//	@Bean
//	public PasswordEncoder passwordEncoder() {	
//		return new BCryptPasswordEncoder();
//	}
	
	/*
	 * html template 파일에서 sec:tag를 사용할 때
	 * 값, 설정, 함수 등을 사용할 수 있도록 객체를 전달하는 용도로 사용
	 * 
	 *  thymeleaf-extras-springsecurity5에서는 Security 설정된 Config에서 자동 주입이 됨
	 *  특히 Config 클래스를 WebSecurityConfigureAdapter 상속받아서 작성할 경우 굳이 bean으로 설정하지 않아도 됨
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}








	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(web);
		
		// security에서 검사하지 않을 요청
		web.ignoring().antMatchers("/static/css/**", "/static/js/**").antMatchers("/static/images/**").antMatchers("/static/music/**");
	}
	
	

}

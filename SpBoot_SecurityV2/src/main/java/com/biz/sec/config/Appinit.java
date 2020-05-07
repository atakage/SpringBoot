package com.biz.sec.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.biz.sec.repository.BBsDao;
import com.biz.sec.repository.UserDao;
import com.biz.sec.repository.UserRoleDao;

import lombok.RequiredArgsConstructor;



/*
 * CommandLineRunner 인터페이스를 상속받은 클래스
 * spring boot에서만 사용할 수 있는 특별한 클래스
 * 
 * 프로젝트가 시작되는 시점에 어떤 코드를 자동으로 실행하고 싶을 때 작성하는 클래스
 */


@RequiredArgsConstructor
@Component
public class Appinit implements CommandLineRunner{

	
	
	private final UserDao uDao;
	private final UserRoleDao urDao;
	private final BBsDao bDao;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		return;
		/*
		 * Optional 클래스로 vo클래스를 wrapping 했을 때
		 * vo에 담긴 데이터가 있으면 isPresent()가 true
		 * 
		 * 데이터가 있으면 다음 명령을 실행하지 않고 return해서 끝내라 
		 */
//		Optional<UserVO> vo = uDao.findByUsername("callor");
//		if(vo.isPresent())return;
//		
//		
//		UserVO userVO = UserVO.builder().username("callor").password("12345").build();
//		
//		uDao.save(userVO);
//		
//		UserRole uRole = UserRole.builder().username(userVO.getUsername()).roleName("ADMIN").build();
//		urDao.save(uRole);
//		uRole = UserRole.builder().username(userVO.getUsername()).roleName("USER").build();
//		urDao.save(uRole);
	}

}

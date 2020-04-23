package com.biz.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.hello.domain.UserVO;

@Service
public class UserService {

	
	public List<UserVO> getUserList(){
		
		List<UserVO> userList = new ArrayList<>();
		
		
		userList.add(UserVO.builder().username("홍길동").password("1234").email("callor@callor.com").phone("010-111-1111").address("서울특별시").build());

		userList.add(UserVO.builder().username("이몽룡").password("1234").email("lee@callor.com").phone("010-222-1111").address("남원시").build());
		
		userList.add(UserVO.builder().username("성춘향").password("1234").email("seong@callor.com").phone("010-333-1111").address("익산시").build());
		
		
		
		return userList;
	}
	
}

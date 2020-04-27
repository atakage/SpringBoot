package com.biz.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.jpa.domain.UserVO;
import com.biz.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userDao;
	
	
	/*
	 * JpaRepository를 사용할 경우
	 * insert와 update는 save() method를 같이 사용하여 수행 
	 */
	public UserVO save(UserVO userVO) {
		
		/*
		 * repository.save(VO)
		 * JPA 환경에서는 JpaRepository를 extends 함으로서
		 * 자동으로 기본 CRUD method를 사용할 수 있음
		 */
		UserVO vo = userDao.save(userVO);
		return vo;
	}


	public List<UserVO> selectAll() {
		
		List<UserVO> userList = userDao.findAll();
		
		return userList;
	}


	/*
	 * findById를 실행하고 나면 UserVO를 받을 수 있음
	 * findById를 실행했을 때 결과값이 없으면 null을 return
	 *  
	 * 이런 상황이 되면
	 * NullPointerException이 발생할 수 있음
	 * Optional로 VO객체를 감싸게 되면
	 * NullPointerException을 회피할 수 있음
	 * 
	 * Java 1.8에서 도입된 Wrapper Class
	 * 
	 * 필요한 VO 객체를 추출하려면 
	 * optVO.get() method를 사용하여야 함
	 */
	public Optional<UserVO> findById(long userId) {
		// TODO Auto-generated method stub
		
		Optional<UserVO> userVO = userDao.findById(userId);
		return userVO;
	}


	public void delete(long userId) {
		// TODO Auto-generated method stub
		
		userDao.deleteById(userId);
		
	}


	
}



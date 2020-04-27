package com.biz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.jpa.domain.UserVO;

/*
 * jpa에서 DB CRUD를 쉽게 구현하기 위해서 JpaRepository를 상속받고
 * Generic에 VO와 ID의 type을 지정
 */
public interface UserRepository extends JpaRepository<UserVO, Long>{
	
	

}

package com.biz.sec.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_users", schema = "spBoot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserVO implements UserDetails{
	
	
	
	/*
	 * 
	 * JPA의 Entity를 선언할 때 id 컬럼(필드변수)은 반드시 class type으로 선언
	 * 그렇지 않으면 JPA의 자동 method가 작동되지 않음
	 * int => Integer
	 * long => Long
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 64)
	private String username;
	
	// 설정하지 않으면 length = 255로 자동 설정
	private String password;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	//DB에 컬럼으로 생성하지 않음
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	private String email;
	private String phone;
	private String address;

	
	/*
	 * JPA에서 1:N의 관계를 설정하는 부분
	 * 
	 * fetch type LAZY
	 * 두 테이블을 join 했을 때 master table의 데이터를 select한 후 
	 * 바로 slave table을 select 하지 않고 slave table의 데이터가 필요한 시점에 sELECT를 수행하도록 하는 지연 옵ㅁ션
	 */
	@OneToMany(mappedBy = "userVO", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;
	
	
	
}

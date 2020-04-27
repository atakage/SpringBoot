package com.biz.jpa.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.jpa.domain.BookVO;

public interface BookDao extends JpaRepository<BookVO, Long>{

}

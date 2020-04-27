package com.biz.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.jpa.domain.BookVO;
import com.biz.jpa.persistence.BookDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookDao bookDao;
	
	public BookVO save(BookVO bookVO) {
		// TODO Auto-generated method stub
		
		return bookDao.save(bookVO);
	}

	public List<BookVO> selectAll() {
		// TODO Auto-generated method stub
		
		return bookDao.findAll();
		
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
		bookDao.deleteById(id);
		
	}

	public Optional<BookVO> findById(Long valueOf) {
		// TODO Auto-generated method stub
		
		
		
		return bookDao.findById(valueOf);
	}
	
	/*
	 * spring-data 패키지의 Pagable, Page, PageRequest 클래스를 사용하여
	 * pagination을 구현
	 */
	public Page<BookVO> getPageList(Pageable page) {
		
		int pageNum = 0;
		if(page.getPageNumber() == 0) {
				pageNum = 0;
		}else {
			pageNum = page.getPageNumber() -1;
			page = PageRequest.of(pageNum, 10);
		}
		
		return bookDao.findAll(page);
		
	}

	
	
	
}

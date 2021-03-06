package com.biz.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.repository.BBsDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BBsService {

	private final BBsDao bDao;
	
	
	/*
	 * 게시판 데이터를 Pageable과 연동하여 pagination을 하기 위한 데이터 return
	 */
	public Page<BBsVO> getPageBbsList(Pageable pageable) {
		
		
		// 몇 페이지를 요청했는가
		int page = 0;
		if(pageable.getPageNumber() != 0) {
			page = pageable.getPageNumber()-1;
		}
			
		
		// 요청한 페이지에서 10개의 데이터를 추출하기 위한 설정값을 만듦
		pageable = PageRequest.of(page,10);
		
		log.debug("pageable:" + pageable.toString());

		// 페이지를 가져오기 위한 설정 객체(pageable)을 dao에게 전달하여 한 페이지의 데이터만 가져오도록 지시
		Page<BBsVO> pageBBsList = bDao.findAll(pageable);
		
		return pageBBsList;
	}


	
	/*
	 * 게시판 전체 데이터를 추출하여 return
	 */
	public List<BBsVO> getBbsList() {
		// TODO Auto-generated method stub
		
		List<BBsVO> bbsList = bDao.findAll();
		return bbsList;
	}



	public BBsVO insert(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		return bDao.save(bbsVO);
	}



	public Optional<BBsVO> findById(long id) {
		// TODO Auto-generated method stub
		
		Optional<BBsVO> bbsVO = bDao.findById(id);

		
		return bbsVO;
	}
	
	
	
}

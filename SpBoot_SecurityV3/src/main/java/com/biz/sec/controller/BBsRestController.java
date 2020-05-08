package com.biz.sec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.service.BBsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/*
 * RestController는 return type에 관계없이 모든 값을 json 객체형으로 web에 되돌림
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/bbs/api")
public class BBsRestController {

	private final BBsService bService;
	
	@RequestMapping(value="/json", method=RequestMethod.GET)
	@CrossOrigin(origins ="http://localhost:3000")
	public List<BBsVO> getBBsList(){
		
		List<BBsVO> bbsList = bService.getBbsList();
		
		return bbsList;
		
	}
	
	
	
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	@CrossOrigin(origins ="http://localhost:3000")
	public BBsVO getDetail(@RequestParam("bbsid") String bbsid){
		
		//List<BBsVO> bbsList = bService.getBbsList();
		
		log.debug("게시판아이디: " + bbsid);
		
		long id=0;
		
		try {
		
			id = Long.valueOf(bbsid);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
		Optional<BBsVO> optBBsVO = bService.findById(id);
		
		log.debug("ddddd"+ optBBsVO.get().toString());
		
		return optBBsVO.get();
		
	}
	
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@CrossOrigin(origins ="http://localhost:3000")
	public BBsVO insert(BBsVO bbsVO) {
	
			log.debug(bbsVO.toString());
			
			bbsVO = bService.insert(bbsVO);
		
		return bbsVO;
		
	}
	

	
}

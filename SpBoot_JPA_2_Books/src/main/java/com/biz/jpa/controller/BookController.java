package com.biz.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.jpa.domain.BookVO;
import com.biz.jpa.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/book")
@Controller
public class BookController {

	
	private final BookService bookService;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String home(Model model) {
		
		List<BookVO> bookList = bookService.selectAll();
		
		
		model.addAttribute("bookList", bookList);
		
		return "home";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		
		BookVO bookVO = new BookVO();
		model.addAttribute("bookVO",bookVO);
		
		return "insert_update";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(BookVO bookVO) {
		
	
		BookVO vo = bookService.save(bookVO);
		
		
		
		return "redirect:/book";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(String id) { // requestparam 안붙여도?
		
	
		bookService.delete(Long.valueOf(id));
		
		
		
		return "redirect:/book";
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String id, Model model) {
		
		Optional<BookVO> vo = bookService.findById(Long.valueOf(id));
		
		
		model.addAttribute("bookVO", vo);
		
		return "insert_update";
		
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(BookVO bookVO) {
		
		
		BookVO vo = bookService.save(bookVO);
		
		
		
		return "redirect:/book";
		
	}
	
	
	
	
	@RequestMapping(value="/pagelist", method=RequestMethod.GET)
	public String getPageList(@PageableDefault Pageable page, Model model) {
		
		Page<BookVO> bookList = bookService.getPageList(page);
		
		model.addAttribute("bookList", bookList);
		
		
		return "booklist";
	}
	
}

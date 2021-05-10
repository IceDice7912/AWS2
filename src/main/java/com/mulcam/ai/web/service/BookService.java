package com.mulcam.ai.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.BookDAO;
import com.mulcam.ai.web.vo.BookVO;

@Service
public class BookService {
	@Autowired
	BookDAO bookDAO;

	public List<BookVO> bookList() {
		return bookDAO.bookList();
	}

	public List<BookVO> selectbookisbn(String isbn) {
		return bookDAO.selectbookisbn(isbn);
	}

	public List<BookVO> topicBook() {
		return bookDAO.topicBook();
	}
	
	public List<BookVO> recommandBook() {
		return bookDAO.recommandBook();
	}

	public List<BookVO> todayBook() {
		return bookDAO.todayBook();
	}

}

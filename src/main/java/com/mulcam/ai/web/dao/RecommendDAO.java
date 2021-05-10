package com.mulcam.ai.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import com.mulcam.ai.web.vo.ProductListVO;
import com.mulcam.ai.web.vo.RecommendVO;

@Mapper
@Repository("recommendDAO")
public interface RecommendDAO {

	public String findCategory(String title);

	public ArrayList<RecommendVO> recommendBook(String isbn);

	public List<RecommendVO> searchBook(String title);	
	

}

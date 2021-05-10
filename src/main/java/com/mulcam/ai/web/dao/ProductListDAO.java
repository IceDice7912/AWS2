package com.mulcam.ai.web.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mulcam.ai.web.vo.ProductListVO;


@Mapper
@Repository("ProductListDAO")
public interface ProductListDAO {

	public ArrayList<ProductListVO> getAll(int end);

	public ArrayList<ProductListVO> getCategoryList(String category);

}

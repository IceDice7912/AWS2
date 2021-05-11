package com.mulcam.ai.web.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.mulcam.ai.web.vo.OrderVO;
import com.mulcam.ai.web.vo.StyleVO;

@Mapper
@Repository("orderDAO")
public interface OrderDAO {

	public void insert(OrderVO orderVO);

	public ArrayList<StyleVO> getMyStyle(String id);

}

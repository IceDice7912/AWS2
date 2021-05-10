package com.mulcam.ai.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.mulcam.ai.web.vo.OrderVO;

@Mapper
@Repository("orderDAO")
public interface OrderDAO {

	public void insert(OrderVO orderVO);

}

package com.gura.spring.shop.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl implements ShopDao{
	
	@Autowired
	private SqlSession session;
	
	//입금
	@Override
	public void deposit(String id, int money) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("money", money);
		session.update("shop.deposit", param);
	}

	//인출
	@Override
	public void withDraw(String id, int money) {
		// TODO Auto-generated method stub
		
	}

	//포인트 적립
	@Override
	public void addPoint(String id, int point) {
		// TODO Auto-generated method stub
		
	}

	//배달 요청
	@Override
	public void deliveryRequest() {
		// TODO Auto-generated method stub
		
	}

}

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
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("money", money);
		session.update("shop.withDraw", param);
	}

	//포인트 적립
	@Override
	public void addPoint(String id, int point) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("point", point);
		session.update("shop.addPoint", param);
	}

	//배달 요청
	@Override
	public void deliveryRequest() {
		System.out.println("배송 요청을 했습니다.");
	}

}

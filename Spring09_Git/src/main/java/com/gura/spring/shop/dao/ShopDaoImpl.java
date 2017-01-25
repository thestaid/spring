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
		// 트랜잭션을 관리하는 블럭에 custom Exception을 발생시켜
		// 종류별로 Exception을 핸들링 할수있다,
		
		//특정 조건에서 발생한다는 가정
		throw new OopsException("오늘 눈이 와서 배송 불가");
		//System.out.println("배송 요청을 했습니다.");
	}

}

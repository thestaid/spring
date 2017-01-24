package com.gura.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gura.spring.shop.dao.ShopDao;

@Component
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;
	
	//입금 작업 메소드
	@Override
	public void deposit(String id, int money) {
		// TODO Auto-generated method stub
		
	}
	
	//구입 작업 메소드
	@Override
	public void buy(String id, int price) {
		// TODO Auto-generated method stub
		
	}

}

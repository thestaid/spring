package com.gura.spring.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.shop.service.ShopService;

@Controller
public class ShopController {
	
	//의존 객체 주입 받기
	@Autowired
	private ShopService shopService;
	
	//ModelAndView를 인자로 선언하면 알아서 new해서 사용할수 있다.
	@RequestMapping("/shop/index")
	public ModelAndView index(ModelAndView mView){
		List<String> info=new ArrayList<String>();
		info.add("구정 특별 SALE");
		info.add("10%의 특별 보너스 적립");
		info.add("기회 놓치지 마세요!");
		//모델에 info라는 키값 담기
		mView.addObject("info", info);
		//view 페이지의 정보를 담아서
		mView.setViewName("shop/index");
		//리턴
		return mView;
	}
}

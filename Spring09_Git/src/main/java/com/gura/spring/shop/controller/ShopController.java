package com.gura.spring.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/shop/depositform")
	public String depositForm(){
		
		return "shop/depositform";
	}
	
	@RequestMapping("/shop/deposit")
	public String deposit(@RequestParam String id, @RequestParam int cash){
		//shopService 객체를 이용해서 입금 작업을 한다.
		shopService.deposit(id, cash);
		return "redirect:/shop/index.do";
	}
	
	//상품 목록 보기 요청 처리
	@RequestMapping("/shop/list")
	public String list(){
		
		return "shop/list";
	}
	
	//상품 구입 요청 처리
	@RequestMapping("/shop/buy")
	public ModelAndView buy(@RequestParam String id, @RequestParam int price){
		//서비스 객체를 이용해서 상품 구입 처리하기
		shopService.buy(id, price);
		ModelAndView mView=new ModelAndView();
		mView.addObject("price", price);
		mView.setViewName("shop/buyresult");
		return mView;
	}
}

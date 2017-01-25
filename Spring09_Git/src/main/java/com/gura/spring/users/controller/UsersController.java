package com.gura.spring.users.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.users.dto.UsersDto;
import com.gura.spring.users.service.UsersService;

@Controller
public class UsersController {
	//의존 객체 주입 되도록
	@Autowired
	private UsersService usersService;
	
	// "/users/private/delete.do" 개인정보 삭제 요청 처리
	@RequestMapping("/users/private/delete")
	public ModelAndView authdelete(HttpSession session){
		//세션에 저장된 아이디 값을 읽어온다.
		String id=(String) session.getAttribute("id");
		//서비스를 이용해서 DB에서 회원정보를 삭제하고
		usersService.delete(id);
		//세션에 로그인 정보를 삭제하고
		session.invalidate();
		ModelAndView  mView=new ModelAndView();
		mView.addObject("msg", "탈퇴되었습니다.");
		mView.addObject("redirectURi", session.getServletContext().getContextPath());
		mView.setViewName("users/alert");
		return mView;
	}
	
	// "/users/private/update.do" 개인정보 수정 요청 처리
	@RequestMapping("/users/private/update")
	public ModelAndView authupdate(HttpServletRequest request,@ModelAttribute UsersDto dto){
		usersService.update(dto);
		ModelAndView mView=new ModelAndView();
		mView.addObject("msg", dto.getId()+"님 회원 정보 수정했습니다.");
		String path=request.getContextPath()+"/users/private/info.do";
		mView.addObject("redirectUri", path);
		mView.setViewName("users/alert");
		return mView;
	}
	
	// "/users/private/updateform.do" 개인정보 수정 폼 요청 처리
	@RequestMapping("/users/private/updateform")
	public ModelAndView authupdateForm(HttpSession session){
		//1. 세션에서 아이디 정보를 읽어온다.
		String id=(String)session.getAttribute("id");
		//2. 수정할 회원의 정보를 담고 있는 ModelAndView 객체를 얻어온다.
		ModelAndView mView=usersService.getData(id);
		//3. forward 이동할 정보를 담아서
		mView.setViewName("users/private/updateform");
		//4. 리턴해준다.
		return mView;
	}
	
	// "/users/private/info.do" 개인정보 보기 요청 처리
	@RequestMapping("/users/private/info")
	public ModelAndView authinfo(HttpSession session){
		//1. 세션에 저장된 id 정보를 읽어온다.
		String id=(String)session.getAttribute("id");
		//2.UserDto가 담긴 ModelAndView 객체를 리턴 받는다.
		ModelAndView mView=usersService.getData(id);
		//3. forward 경로를 담는다.
		mView.setViewName("users/private/info");
		//4. ModelAndView 객체를 리턴해준다.
		return mView;
	}
	
	// "/users/signout.do" 요청 처리
	@RequestMapping("/users/signout")
	public ModelAndView signout(HttpSession session){
		//세션 초기화
		session.invalidate();
		ModelAndView mView=new ModelAndView();
		mView.addObject("msg", "로그 아웃 되었습니다.");
		mView.addObject("redirectUri", session.getServletContext().getContextPath());
		mView.setViewName("users/alert");
		return mView;
	}
	
	// "/users/signin.do" 로그인 요청 처리
	@RequestMapping("/users/signin")
	public ModelAndView signin(HttpSession session, @ModelAttribute UsersDto dto,@RequestParam String uri){
		//아이디 비밀번호가 유효한지 여부를 확인 한다.
		boolean isValid=usersService.isValid(dto);
		ModelAndView mView=new ModelAndView();
		if(isValid){//아이디 비번이 맞으면
			//로그인 처리를 해준다.
			session.setAttribute("id", dto.getId());
			mView.addObject("msg", dto.getId()+"님이 로그인 되었습니다.");
			mView.addObject("redirectUri", uri);
		}else{
			//아이디 혹은 비밀번호가 틀리다는 정보를 응답한다.
			mView.addObject("msg", "아이디 혹은 비밀번호가 틀려요");
			String location=session.getServletContext().getContextPath()+
					"/users/signin_form.do?uri="+uri;
			mView.addObject("redirectUri", location);
		}
		// alert창으로 보낸다.
		mView.setViewName("users/alert");
		return mView;
	}
	
	// "users/signin_form.do" 로그인 폼 요청 처리
	@RequestMapping("/users/signin_form")
	public String signin_form(HttpSession session){
		//세션 초기화
		session.invalidate();
		//뷰페이지로 forward 이동
		return "users/signin_form";
	}
	
	// "/users/signup.do" 요청처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(HttpServletRequest request, @ModelAttribute UsersDto dto){
		//서비스를 이용해서 회원 정보를 저장한다.
		usersService.insert(dto);
		ModelAndView mView=new ModelAndView();
		mView.addObject("msg", dto.getId()+"회원님 가입되었습니다.");
		mView.addObject("redirectUri", request.getContextPath());
		mView.setViewName("users/alert");
		return mView;
	}
	
	// ajax "/users/checkid.do 요청 처리
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map=usersService.canUseId(inputId);
		//json 문자열로 응답
		return map;
	}
	
	@RequestMapping("/users/signup_form")
	public String signup_form(){
		return "users/signup_form";
	}
	
	
}

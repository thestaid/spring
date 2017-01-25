package com.gura.spring.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class LoginAspect {
	
	//"execution(* auth*(..))" : auth로 시작하는 모든 메소드
	//"execution(* com.gura.spring.users.controller.UsersController.auth*(..))" : 위의 내용 세분화
	//다른 것도 나열 하려면 ||문자로 해서 나열 가능
	@Around("execution(* auth*(..))")
	public Object loginCheck(ProceedingJoinPoint joinPoint)
		throws Throwable{
		//컨트롤러에 aop를 적용 했을때 HttpServletRequest 참조값 얻어오기
		ServletRequestAttributes attrs=(ServletRequestAttributes)
				RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request=attrs.getRequest();
		//세션객체의 참조값 얻어오기
		HttpSession session=request.getSession();
		if(session.getAttribute("id")==null){//로그인 안되어 있을때 아래의 작업을 수행한다.		
			//원래 요청 uri 정보 얻어오기
			String uri=request.getRequestURI();
			String path="/users/signin_form.do?uri="+uri;	
			//리턴타입을 확인 해준다.(ModelAndView)
			ModelAndView mView=new ModelAndView();
			mView.setViewName("redirect:"+path);
			/*
			 * joinPoint.proceed()메소드를 호출하지 않고 바로 리턴하면
			 * aop가 적용된 메소드안에 있는 작업이 수행되지 않는다.
			 * 여기서 리턴 해주는 참조값이 aop가 적용된 메소드에 리턴된다.
			 * 그러므로 여기서 리턴해주는 data type과 aop가 적용된 메소드의
			 * 리턴 type은 반드시 같아야 한다.
			 */
			return mView;
		}else{//로그인 했을 때는 원래 작업을 수행하면 된다.
			//여기가 수행되면 aop가 적용된 메소드가 정상 수행된다.
			return joinPoint.proceed();
		}
		//차이점 !!! : joinPoint.proceed()가 수행되느냐 안되느냐의 문제이다!! 중요!!!
	}
}

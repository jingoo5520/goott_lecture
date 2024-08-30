package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.domain.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	/* method 생략가능 */
	@RequestMapping(value="/doAct1", method = RequestMethod.GET)
	public String doAction1 () {
		System.out.println("doAction1 호출");
		return "doAction1";
	}
	
	@RequestMapping(value="/doAct2", method = RequestMethod.GET)
	public void doAction2 () {
		System.out.println("doAction2 호출");
	}
	
	@RequestMapping(value="/doAct3", method = RequestMethod.GET)
	public String doAction3 (Model model) {
		logger.info("doAction3가 호출됨");
		
		String name = "kjg";
		
		model.addAttribute("name", name); // model 객체에 바인딩
		
		return "doAction3";
	}
	
	@RequestMapping(value="/doAct4", method = RequestMethod.GET)
	public ModelAndView doAction4 () {
		logger.info("doAction4가 호출됨");
	
		String name = "kjg";
		ModelAndView mav = new ModelAndView();
	
		mav.addObject("name", name); // ModelAndView 객체에 바인딩
		mav.setViewName("doAction4");
		
		return mav;
	}
	
	@RequestMapping(value="/doAct5", method = RequestMethod.GET)
	public void doAction5 (Model model) {
		System.out.println("doAction5 호출");
		
		Student stu = new Student("24001", "홍길동");
		 model.addAttribute("stu", stu);
		
		// 바인딩하는 객체의 이름을 지정하지 않는 경우
		// 자동으로 클래스명 앞글자르 소문자로 바꿔서 바인딩
//		model.addAttribute(stu);
	}
}

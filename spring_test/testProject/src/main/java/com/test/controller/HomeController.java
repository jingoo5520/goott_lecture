package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String home(
			@RequestParam("stuNo") String stuNo,
            @RequestParam("stuName") String stuName,
			Model model) {
		
		
		Student stu1 = new Student(stuNo, stuName);
		model.addAttribute("stu", stu1);
		
		return "home";
	}
	
	@RequestMapping()
	public String loginForm() {
		return "loginForm";
	}
	
	
	
}

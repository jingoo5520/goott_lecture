package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.Student;

@Controller
@RequestMapping("/student/*") // /student/* 의 모든 url 에 대해 mapping
public class StudentController {

	@RequestMapping("outputStudent")
	public void outputStudent(Model model) {
	
		Student stu = new Student("24001", "홍길동");
		
		model.addAttribute("student", stu);
	}
	
	@RequestMapping(value="inputStudent", method= RequestMethod.GET)
	public void inputStudentGET() {
		System.out.println("inputStudentGET 호출됨");
	}
	
	@RequestMapping(value="saveStudent", method=RequestMethod.POST)
	public String inputStudentPOST(@RequestParam(name="stuNo") String stuNo, 
			@RequestParam(name="stuName") String stuName, Model model, RedirectAttributes rttr) {
		
		System.out.println("학번: " + stuNo);
		System.out.println("이름: " + stuName);
		
		Student stu = new Student(stuNo, stuName);
		
		model.addAttribute("inputStudent", stu);
//		rttr.addAttribute("inputStudent", stu);
//		rttr.addFlashAttribute("inputStudent", stu);
		
		rttr.addAttribute("stuNo", stu.getStuNo());
		rttr.addAttribute("stuName", stu.getStuName());
		
		
		return "redirect:homeStudent";
	}
	
	@RequestMapping("homeStudent")
	public void homeStudent() {
		System.out.println("homeStudent GET요청");
	}
}

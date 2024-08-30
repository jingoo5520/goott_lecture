package com.spring.memberController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
	@RequestMapping("memberView")
	public String doMemberView() {
		System.out.println("doMemberView 호출");
		
		return "doMemberView";
	}
	
	// mapping 주소가 같으면, Ambiguous mapping 오류 발생
	@RequestMapping(value = {"memberView", "memberSave2"}, method = RequestMethod.GET)
	public String doMemberSave() {
		System.out.println("doMemberSave 호출됨");
		return "memberSave";
	}
	
	@RequestMapping(value="memberTest")
	public void doMemberTest(@RequestParam(name="name") String name, @RequestParam(name="age", required = false, defaultValue = "0") int age) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
	}
	
	
	// String age, required = true;
	@RequestMapping(value="paramTest")
	public String paramTest(@RequestParam(name="age") String age) {
		System.out.println("age: " + age);
		
		// String age
		// ?쿼리스트링이 없음: 400에러
		// ?age=3 -> age: 3
		// ?age = -> age: (빈문자)
		
		return "paramTest";
	}
	
	// String age, required = false;
	@RequestMapping(value="paramTest2")
	public String paramTest2(@RequestParam(name="age", required = false) String age) {
		System.out.println("age: " + age);
		
		// String age
		// ?쿼리스트링이 없음 -> age: null
		// ?age=3 -> age: 3
		// ?age = -> age: (빈문자)
		
		return "paramTest";
	}
	
	// int age, required = false;
	@RequestMapping(value="paramTest3")
	public String paramTest3(@RequestParam(name="age") int age) {
		System.out.println("age: " + age);
		
		// int age
		// ?쿼리스트링이 없음 -> 400에러
		// ?age=3 -> age: 3
		// ?age = -> 400에러
		
		return "paramTest";
	}
	
	// int age, required = false;
	@RequestMapping(value="paramTest4")
	public String paramTest4(@RequestParam(name="age", required=false) int age) {
		System.out.println("age: " + age);
		
		// int age
		// ?쿼리스트링이 없음 -> 500에러, Integer(참조타입)로 변환시 age: null
		// ?age=3 -> age: 3
		// ?age = -> 400에러
		
		return "paramTest";
	}
}

package com.spring.controller.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.ProductDTO;

@Controller
public class ProductController {
	
	@RequestMapping("viewProduct")
	public void doMemberView() {
		System.out.println("viewProduct 호출");
		// WEB-INF/views/viewProduct.jsp 포워딩
	}

	@RequestMapping("viewProduct2")
	public String redirectView() {
		
		// home
		return "redirect:/";
	}
	
	@RequestMapping(value= "hello", method=RequestMethod.GET)
	public void hello() {
		
		
	}
	
	@RequestMapping(value= "viewProduct3", method=RequestMethod.GET)
	public String redirectView3() {
		
		// home
		// hello와 mapping된 메서드가 있어야함
		// method도 동일해야함
		return "redirect:/hello";
	}
	
	
	// 파라미터를 넘겨주며 redirect
	@RequestMapping(value= "viewProduct4", method=RequestMethod.GET)
	public String redirectView4(RedirectAttributes rttr) {
		
//		rttr.addAttribute("name", "둘리");
//		rttr.addAttribute("age", 15);
//		
//		rttr.addFlashAttribute("status", "success");
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("name", "희동");
		map.put("age", "5");
		
		rttr.addAllAttributes(map);
		// home
		// hello와 mapping된 메서드가 있어야함
		// method도 동일해야함
		return "redirect:/hello";
	}
	
	// 파라미터 수집
	@RequestMapping(value="productTest")
	public void testParam(String name, int age) {
		System.out.println("name: " + name);
		System.out.println("age" + age);
	}
	
	@RequestMapping("productForm")
	public void productFormGET() {
		
	}
	
//	 @RequestMapping(value= "prodPost", method=RequestMethod.POST)
	@PostMapping("prodPost")
	public String saveProduct(@ModelAttribute(name="prod") ProductDTO product) {
		return "product";
	}
	
	// JSON 응답
	@GetMapping("jsonForm")
	public void jsonFormGET() {
		
	}
	
	@PostMapping("saveJson")
	public @ResponseBody ProductDTO outputJson(ProductDTO product) {
		return product;
	}
	
	
	
	
	
}

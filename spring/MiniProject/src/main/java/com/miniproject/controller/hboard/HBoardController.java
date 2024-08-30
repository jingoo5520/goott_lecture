package com.miniproject.controller.hboard;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniproject.controller.HomeController;
import com.miniproject.domain.HBoardVO;
import com.miniproject.service.hboard.HBoardService;

// Controller단에서 해야 할 일
// 1) URI 매핑 (어떤 URI가 어떤 방식으로 호출되었을 때 어떤 메서드에 매핑 시킬 것이냐)
// 2) 있다면 view단에서 보내준 매개 변수 수집
// 3) 데이터베이스에 대한 CRUD를 수행하기 위해 service단의 해당 메서드를 호출
// service단에서 return 값을 view로 바인딩
// 4) 부가적으로 컨트롤러단은 Servlet에 의해 동작되는 모듈이기 때문에, HttpServeletRequest, HttpServletResponse
// , HttpSession 등의 Servlet 객체를 이용 가능 
// -> 객체를 이용하여 구현할 기능이 있다면 그 기능은 컨트롤러 단에서 구현
// sysout 보다 logger 사용

@Controller
@RequestMapping("/hboard")
public class HBoardController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private HBoardService service;

	@RequestMapping("/listAll") // /hboard/listAll 호출시 해당 메서드 실행
	public void listAll(Model model) throws Exception {
		logger.info("HBoardController.listAll().....");

		List<HBoardVO> list = service.getAllBoard();

		/*
		 * for (HBoardVO b : list) { System.out.println(b.toString()); }
		 */
		
		model.addAttribute("boardList", list);
		
	}
}

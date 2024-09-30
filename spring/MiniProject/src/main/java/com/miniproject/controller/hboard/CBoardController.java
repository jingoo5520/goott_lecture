package com.miniproject.controller.hboard;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.PagingInfoDTO;
import com.miniproject.domain.SearchCriteriaDTO;
import com.miniproject.persistence.CBoardDAO;
import com.miniproject.service.hboard.CBoardService;
import com.miniproject.util.GetClientIPAddr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cboard")
public class CBoardController {

	@Inject
	private CBoardService service; // 서비스 객체 주입
	
	@Inject
	private CBoardDAO cDao;

	@RequestMapping("/listAll") // /hboard/listAll 호출시 해당 메서드 실행
	public void listAll(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pagingSize", defaultValue = "10") int pagingSize,
			SearchCriteriaDTO searchCriteriaDTO) {

		PagingInfoDTO dto = PagingInfoDTO.builder().pageNo(pageNo).pagingSize(pagingSize).build();

		List<HBoardVO> list = null;

		try {
			Map<String, Object> resultMap = service.getAllBoard(dto, searchCriteriaDTO);

			list = (List<HBoardVO>) resultMap.get("boardList");
			PagingInfo pi = (PagingInfo) resultMap.get("pagingInfo");

			model.addAttribute("boardList", list);
			model.addAttribute("pagingInfo", pi);
			model.addAttribute("search", searchCriteriaDTO);

		} catch (Exception e) {
			model.addAttribute("exception", "error");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/showSaveBoardForm", method = RequestMethod.GET)
	public String showSaveBoardForm() {
		return "./cboard/showSaveBoardForm";
	}

	// 게시글 저장 버튼을 누르면 해당 게시글을 DB에 저장하는 메서드
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(HBoardDTO boardDTO, RedirectAttributes rttr) {
		System.out.println("글저장: " + boardDTO.toString());

		try {
			if (service.saveBoard(boardDTO)) {
				rttr.addAttribute("status", "success");
			}

		} catch (Exception e) {
			e.printStackTrace();
			rttr.addAttribute("status", "fail");
		}

		return "redirect:/cboard/listAll";
	}

	@GetMapping(value = { "/viewBoard", "/modifyBoard" })
	public String viewBoard(@RequestParam(value = "boardNo", defaultValue = "-1") int boardNo, Model model,
			HttpServletRequest request) {

		BoardDetailInfo hboardInfo = null;
		String ipAddr = GetClientIPAddr.getClientIp(request);
		String returnViewPage = "";
		List<String> peopleLike = null;

		if (boardNo == -1) {
			returnViewPage = "redirect:/hboard/listAll";

		} else {
			try {
				
				peopleLike = cDao.selectPeopleWhoLikeBoard(boardNo);
				
				if (request.getRequestURI().equals("/cboard/viewBoard")) {
					System.out.println("게시글 상세보기 호출");
					hboardInfo = service.read(boardNo, ipAddr);
					returnViewPage = "/cboard/viewBoard";

				} else if (request.getRequestURI().equals("/cboard/modifyBoard")) {
					System.out.println("게시글 수정하기 호출");
					returnViewPage = "/cboard/modifyBoard";
					hboardInfo = service.read(boardNo);
				}

			} catch (Exception e) {
				e.printStackTrace();
				returnViewPage = "redirect:/cboard/listAll?status=fail";
			}

			model.addAttribute("board", hboardInfo);
			model.addAttribute("peopleLike", peopleLike);
		}
		
		return returnViewPage;

	}

	@RequestMapping(value = "/cancelBoard", method = RequestMethod.POST)
	public ResponseEntity<String> cancelBoard(HttpServletRequest request) {
		System.out.println("/cancleBoard 요청 실행");

		String realPath = request.getSession().getServletContext().getRealPath("/resources/boardUpFiles");

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@GetMapping("/showReplyForm")
	public String showReplyForm(@RequestParam(value = "boardNo", defaultValue = "-1") int boardNo, Model model,
			HttpServletRequest request) {

		HBoardDTO hboard = null;

		if (boardNo == -1) {
			return "redirect:/hboard?viewBoard=" + boardNo;

		} else {
			try {
				hboard = service.getBoard(boardNo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("hboard", hboard);
			return "hboard/showReplyForm";
		}
	}

	@RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	public String saveReplyBoard(HBoardReplyDTO replyBoardDTO, RedirectAttributes rttr) {
		System.out.println("/saveReply 요청 실행");

		String returnPage = "redirect:/hboard/listAll";

		try {
			boolean result = service.saveReply(replyBoardDTO);
			if (result) {
				// 예외없이 답글이 잘 저장된 경우
				rttr.addAttribute("status", "success");
			}

		} catch (Exception e) {
			rttr.addAttribute("status", "fail");

			e.printStackTrace();
		}

		return returnPage;
	}

	@RequestMapping(value = "/deleteBoard")
	public String deleteBoard(@RequestParam("boardNo") int boardNo, HttpServletRequest request,
			RedirectAttributes rttr) {
		System.out.println(boardNo + " 번 글 삭제 요청");

		String realPath = request.getSession().getServletContext().getRealPath("/resources/boardUpFiles");

		try {
			boolean result = service.deleteBoard(boardNo, realPath);

			if (result) {
				rttr.addAttribute("status", "success");
			}

		} catch (Exception e) {
			rttr.addAttribute("status", "fail");
			e.printStackTrace();
		}

		return "redirect:/cboard/listAll";
	}

	// 수정된 게시판 저장
	@RequestMapping(value = "/modifyBoardSave", method = RequestMethod.POST)
	public String modifyBoardSave(HBoardDTO modifyBoard, HttpServletRequest request, RedirectAttributes rttr) {

		try {

			System.out.println("modifyBoard: " + modifyBoard);

			if (service.modifyBoard(modifyBoard)) {
				rttr.addAttribute("status", "success");
			}
		} catch (Exception e) {
			rttr.addAttribute("status", "fail");
			e.printStackTrace();

		}

		return "redirect:/cboard/viewBoard?boardNo=" + modifyBoard.getBoardNo();
	}
	
	@PostMapping("/boardlike")
	public ResponseEntity<String> likeDislike(@RequestParam("who") String who, @RequestParam("boardNo") int boardNo, @RequestParam("like") String like){
		
		log.info(who + "님이 " + boardNo + "번 글을 " + like + "하셨습니다.");
		
		ResponseEntity<String> result = null;
		boolean dbResult = false;
		
		
			try {
				if(like.equals("like")) {
					dbResult = service.likeBoard(boardNo, who);
				} else if(like.equals("dislike")) {
					dbResult = service.disLikeBoard(boardNo, who);
				}
					
				if(dbResult) {
					result = new ResponseEntity<String>("success", HttpStatus.OK);
				}
				
			} catch (Exception e) {

				
				e.printStackTrace();
			}
		
		
		return result;
	}
	
//	@GetMapping("/getBoardlikers")
//	public void getBoardlikers(@RequestParam("boardNo") int boardNo) {
//		// service.getBoardlikers(boardNo);
//	}
}

package com.miniproject.controller.hboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.BoardUpFileStatus;
import com.miniproject.domain.BoardUpFilesVODTO;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.MyResponseWithoutData;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.PagingInfoDTO;
import com.miniproject.domain.SearchCriteriaDTO;
import com.miniproject.service.hboard.HBoardService;
import com.miniproject.util.FileProcess;
import com.miniproject.util.GetClientIPAddr;

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
	// 유저가 업로드한 파일을 임시 보관하는 객체
	private List<BoardUpFilesVODTO> uploadFileList = new ArrayList<BoardUpFilesVODTO>();

	// 수정요청시 유저가 업로드한 파일 저장
	private List<BoardUpFilesVODTO> modifyFileList = new ArrayList<BoardUpFilesVODTO>();

	@Inject
	private HBoardService service; // 서비스 객체 주입

	@Inject
	private FileProcess fileProcess; // FileProcess 객체 주입

	@RequestMapping("/listAll") // /hboard/listAll 호출시 해당 메서드 실행
	public void listAll(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pagingSize", defaultValue = "10") int pagingSize,
			SearchCriteriaDTO searchCriteriaDTO) {
		System.out.println(pageNo + "번 페이지 출력");
		System.out.println("searchCriteriaDTO: " + searchCriteriaDTO);

		PagingInfoDTO dto = PagingInfoDTO.builder().pageNo(pageNo).pagingSize(pagingSize).build();

		List<HBoardVO> list = null;

		try {
			Map<String, Object> resultMap = service.getAllBoard(dto, searchCriteriaDTO);

			list = (List<HBoardVO>) resultMap.get("boardList");
			PagingInfo pi = (PagingInfo) resultMap.get("pagingInfo");

			model.addAttribute("boardList", list);
			model.addAttribute("pagingInfo", pi);
			model.addAttribute("search", searchCriteriaDTO);
			// model.addAttribute("now", );
			
			
			
		} catch (Exception e) {
			model.addAttribute("exception", "error");
			e.printStackTrace();
		}

//		return new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(200, "", "success"), HttpStatus.OK); 

	}

	@RequestMapping(value = "/saveBoard", method = RequestMethod.GET)
	public String showSaveBoard() {
		return "./hboard/saveBoard";
	}

	// 게시글 저장 버튼을 누르면 해당 게시글을 DB에 저장하는 메서드
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(HBoardDTO boardDTO, RedirectAttributes rttr) {
		System.out.println("/saveBoard 요청 실행");
		System.out.println("글저장" + boardDTO.toString());

		// 첨부파일 리스트를 BoardDTO에 추가
		boardDTO.setFileList(uploadFileList);
//		boardDTO.setRef(boardDTO.getBoardNo());

		String returnPage = "redirect:/hboard/listAll";
		try {

			if (service.saveBoard(boardDTO)) {
				rttr.addAttribute("status", "success");
			}

		} catch (Exception e) {
			e.printStackTrace();
			rttr.addAttribute("status", "fail");
		}

		uploadFileList.clear();

		return returnPage;
	}

	@RequestMapping(value = "/upfiles", method = RequestMethod.POST)
	public ResponseEntity<MyResponseWithoutData> saveBoardFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		System.out.println("파일 전송 요청됨");
		System.out.println("파일의 오리지널 이름: " + file.getOriginalFilename());
		System.out.println("파일의 크기: " + file.getSize());
		System.out.println("파일의 contentType: " + file.getContentType());

		ResponseEntity<MyResponseWithoutData> result = null;

		try {
			BoardUpFilesVODTO fileInfo = fileSave(file, request);

			String tmp = null;
			uploadFileList.add(fileInfo);

			System.out.println("====================================");
			System.out.println("현재 파일리스트에 있는 파일들");

			for (BoardUpFilesVODTO f : uploadFileList) {
				System.out.println(f.toString());
			}
			System.out.println("====================================");

			System.out.println("fileInfo: " + fileInfo.toString());
			if (fileInfo.getThumbFileName() != null) {
				// 업로드 파일이 이미지인 경우

				tmp = fileInfo.getThumbFileName();

			} else {
				// 아닌 경우

				tmp = fileInfo.getNewFileName();
			}

			MyResponseWithoutData mrw = MyResponseWithoutData.builder().code(200).msg("success").newFileName(tmp)
					.build();

			result = new ResponseEntity<MyResponseWithoutData>(mrw, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();

			result = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return result;
	}

	private BoardUpFilesVODTO fileSave(MultipartFile file, HttpServletRequest request) throws IOException {
		// 파일의 기본 정보 저장

		String originalFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		String contentType = file.getContentType();
		byte[] upfile = file.getBytes();

		String realPath = request.getSession().getServletContext().getRealPath("/resources/boardUpFiles/");
		System.out.println("서버의 실제 물리적 경로: " + realPath);

		// 실제 파일 저장
		BoardUpFilesVODTO result = fileProcess.saveFileToRealPath(upfile, realPath, contentType, originalFileName,
				fileSize);

		return result;
	}

	@RequestMapping(value = "/removefile", method = RequestMethod.POST)
	public ResponseEntity<MyResponseWithoutData> removeUpFile(@RequestParam("removeFileName") String removeFileName,
			HttpServletRequest request) {

		System.out.println("업로드된 파일을 삭제하자");
		String realPath = request.getSession().getServletContext().getRealPath("/resources/boardUpFiles");

		ResponseEntity<MyResponseWithoutData> result = null;

		// 이미지라면
		// thumb_xxx.png 삭제
		// originalxxx.png 삭제

		// 이미지가 아니라면
		// originalxxx.ext 삭제

		int removeIndex = -1;
		boolean removeResult = false;

		if (removeFileName.contains("thumb_")) {
			for (int i = 0; i < uploadFileList.size(); i++) {
				if (uploadFileList.get(i).getThumbFileName().contains(removeFileName)) {
					System.out.println(i + "번째에서 해당 파일 찾음: " + uploadFileList.get(i).getThumbFileName());
					if (fileProcess.removeFile(realPath + removeFileName)
							&& fileProcess.removeFile(realPath + removeFileName.replace("thumb_", ""))) {
						removeIndex = i;
						System.out.println(removeFileName + "파일 삭제 성공");
						removeResult = true;
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < uploadFileList.size(); i++) {
				if (uploadFileList.get(i).getNewFileName().contains(removeFileName)) {
					System.out.println(i + "번째에서 해당 파일 찾음: " + uploadFileList.get(i).getNewFileName());
					if (fileProcess.removeFile(realPath + removeFileName)) {
						removeIndex = i;
						System.out.println("noimage - " + removeFileName + "파일 삭제 성공");
						removeResult = true;
						break;
					}
				}
			}
		}

		if (removeResult) {
			uploadFileList.remove(removeIndex);
			System.out.println("====================================");
			System.out.println("현재 파일리스트에 있는 파일들");

			for (BoardUpFilesVODTO f : uploadFileList) {
				System.out.println(f.toString());
			}
			System.out.println("====================================");
			result = new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(200, "", "success"),
					HttpStatus.OK);
		} else {
			result = new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(200, "", "fail"),
					HttpStatus.CONFLICT);
		}

		return result;
	}

	@RequestMapping(value = "/cancelBoard", method = RequestMethod.POST)
	public ResponseEntity<String> cancelBoard(HttpServletRequest request) {
		System.out.println("/cancleBoard 요청 실행");

		String realPath = request.getSession().getServletContext().getRealPath("/resources/boardUpFiles");

		allUploadFileDelete(realPath, uploadFileList);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	private void allUploadFileDelete(String realPath, List<BoardUpFilesVODTO> fileList) {

		for (int i = 0; i < fileList.size(); i++) {
			System.out.println(realPath + fileList.get(i).getNewFileName());
			fileProcess.removeFile(realPath + fileList.get(i).getNewFileName());

			System.out.println(fileList.get(i).toString());

			if (fileList.get(i).getThumbFileName() != null) {
				fileProcess.removeFile(realPath + fileList.get(i).getThumbFileName());
			}

		}
	}

	// 게시글 상세페이지 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	@GetMapping("/viewBoard1")
	public void viewBoard1(@RequestParam("boardNo") int boardNo, Model model) throws Exception {

		System.out.println(boardNo + "번 글 상세페이지 이동");

		HBoardDTO hboard = service.getBoard(boardNo);

		System.out.println("hboard: " + hboard);

		model.addAttribute("hboard", hboard);
	}

	// resultMapTest ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@GetMapping("/viewBoard2")
	public void viewBoard2(@RequestParam("boardNo") int boardNo, Model model) {

		HBoardDTO hboard = null;

		try {
			hboard = service.testResultMap(boardNo);

			System.out.println("hboard= " + hboard);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("hboard", hboard);
	}

	@GetMapping(value = { "/viewBoard", "/modifyBoard" })
	public String viewBoard(@RequestParam(value = "boardNo", defaultValue = "-1") int boardNo, Model model,
			HttpServletRequest request) {

		List<BoardDetailInfo> hboardList = null;
		String ipAddr = GetClientIPAddr.getClientIp(request);
		String returnViewPage = "";

		if (boardNo == -1) {
			return "redirect:/hboard/listAll";

		} else {
			try {
				if (request.getRequestURI().equals("/hboard/viewBoard")) {
					System.out.println("게시글 상세보기 호출");
					hboardList = service.read(boardNo, ipAddr);
					returnViewPage = "/hboard/viewBoard";

				} else if (request.getRequestURI().equals("/hboard/modifyBoard")) {
					System.out.println("게시글 수정하기 호출");
					returnViewPage = "/hboard/modifyBoard";
					hboardList = service.read(boardNo);

					// 저장된 파일 모두 호출
					for (BoardDetailInfo b : hboardList) {
						this.modifyFileList = b.getFileList();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				returnViewPage = "redirect:/hboard/listAll?status=fail";
			}

			model.addAttribute("hboardList", hboardList);

			return returnViewPage;
		}

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

		return "redirect:/hboard/listAll";
	}

	// 게시글 수정 처리
	@RequestMapping(value = "modifyRemoveFileCheck", method = RequestMethod.POST)
	public ResponseEntity<MyResponseWithoutData> modifyRemoveFileCheck(@RequestParam("removeFileNo") int removeFilePK) {
		System.out.println(removeFilePK + "번 파일 삭제 요청");

		// 아직 최종 수정은 아님
		// 삭제될 파일을 체크만 해두고 최종요청시 실제 삭제 처리

		for (BoardUpFilesVODTO file : this.modifyFileList) {
			if (removeFilePK == file.getBoardUpFileNo()) {
				file.setFileStatus(BoardUpFileStatus.DELETE);
			}
		}

		System.out.println("==================================");
		System.out.println("현재 파일리스트에 있는 파일들 (수정시)");
		for (BoardUpFilesVODTO file : this.modifyFileList) {
			System.out.println(file.toString());
		}

		System.out.println("==================================");

		return new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(200, null, "success"),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/cancelRemFiles", method = RequestMethod.POST)
	public ResponseEntity<MyResponseWithoutData> cancelRemFiles() {
		System.out.println("파일르스트의 모든 파일 삭제 취소");

		for (BoardUpFilesVODTO file : this.modifyFileList) {
			file.setFileStatus(null);
		}

		System.out.println("==================================");
		System.out.println("현재 파일리스트에 있는 파일들 (수정시)");
		for (BoardUpFilesVODTO file : this.modifyFileList) {
			System.out.println(file.toString());
		}

		System.out.println("==================================");

		return new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(200, null, "success"),
				HttpStatus.OK);
	}

	// 수정된 게시판 저장
	@RequestMapping(value = "/modifyBoardSave", method = RequestMethod.POST)
	public String modifyBoardSave(HBoardDTO modifyBoard, @RequestParam("modifyNewFile") MultipartFile[] modifyNewFile,
			HttpServletRequest request, RedirectAttributes rttr) {

		List<Integer> removeFileNoList = new ArrayList<Integer>();
		List<BoardUpFilesVODTO> newFileList = new ArrayList<BoardUpFilesVODTO>();

		for (BoardUpFilesVODTO file : modifyFileList) {
			// 기존에 있지만 삭제를 원하는 파일들
			if (file.getFileStatus() != null) {
				removeFileNoList.add(file.getBoardUpFileNo());
			}
		}

		try {
			for (int i = 0; i < modifyNewFile.length; i++) {
				System.out.println("새로 업로드된 파일: " + modifyNewFile[i]);
				BoardUpFilesVODTO fileInfo = fileSave(modifyNewFile[i], request);
				newFileList.add(fileInfo);
			}

			System.out.println("modifyBoard: " + modifyBoard);
			System.out.println("removeFileNoList: " + removeFileNoList);
			System.out.println("newFileList" + newFileList);

			if (service.modifyBoard(modifyBoard, removeFileNoList, newFileList)) {
				rttr.addAttribute("status", "success");

			}
		} catch (Exception e) {
			rttr.addAttribute("status", "fail");
			e.printStackTrace();

		}

		return "redirect:/hboard/viewBoard?boardNo=" + modifyBoard.getBoardNo();
	}
}

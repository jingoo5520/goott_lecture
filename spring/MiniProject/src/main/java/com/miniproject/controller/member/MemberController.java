package com.miniproject.controller.member;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproject.domain.LoginDTO;
import com.miniproject.domain.MemberDTO;
import com.miniproject.domain.MyResponseWithoutData;
import com.miniproject.service.member.MemberService;
import com.miniproject.util.FileProcess;
import com.mysql.cj.util.StringUtils;

@RequestMapping("/member")
@Controller
public class MemberController {

	@Inject
	private MemberService mService;

//	@Inject
//	private SendMailService sService;
	
	@Inject
	private FileProcess fp;

	@RequestMapping(value = "/register")
	public void showRegisterForm() {
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerMember(MemberDTO registerMember, MultipartFile userProfile, HttpServletRequest request, RedirectAttributes rttr) {
		System.out.println("회원가입 진행!: " + registerMember.toString());
		System.out.println(userProfile.getOriginalFilename());
		
		String resultPage = "redirect:/";
		
		String realPath = request.getSession().getServletContext().getRealPath("/resources/userImg");
		System.out.println("실제 파일 경로: " + realPath);
		
		String tmpUserProfileName = userProfile.getOriginalFilename();
		System.out.println("tmpUserProfileName: " + tmpUserProfileName);
		
		if(!StringUtils.isNullOrEmpty(tmpUserProfileName)) {
			String ext = tmpUserProfileName.substring(tmpUserProfileName.lastIndexOf(".") + 1);
			registerMember.setUserImg(registerMember.getUserId() + "." + ext);
		}
		
		try {
			// DB에 저장
			if(mService.saveMember(registerMember)) {
				
				rttr.addAttribute("status", "success");
				
				// 프로필 사진 올렸는지 확인
				// 올렸다면
				if(!StringUtils.isNullOrEmpty(tmpUserProfileName)) {
					fp.saveUserProfile(userProfile.getBytes(), realPath, registerMember.getUserImg());
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof IOException) {
				// 회원가입 유저의 회원가입 취소 처리
				rttr.addAttribute("status", "fileFail");
			} else {
				rttr.addAttribute("status", "fail");
			}
			
			return "redirect:/member/register"; // 실패한 경우
		}
		
		return resultPage;
		
	}

	@RequestMapping(value = "/isDuplicate", method = RequestMethod.POST)
	public ResponseEntity<MyResponseWithoutData> isDuplicate(@RequestParam("tmpUserId") String tmpUserId) {

		MyResponseWithoutData json = null;
		ResponseEntity<MyResponseWithoutData> result = null;

		try {
			if (mService.idIsDuplicate(tmpUserId)) {
				json = new MyResponseWithoutData(200, tmpUserId, "duplicate");
			} else {
				json = new MyResponseWithoutData(200, tmpUserId, "not duplicate");
			}

			result = new ResponseEntity<MyResponseWithoutData>(json, HttpStatus.OK);

		} catch (Exception e) {
			result = new ResponseEntity<MyResponseWithoutData>(HttpStatus.CONFLICT);
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/callSendMail", method = RequestMethod.POST)
	public ResponseEntity<String> callSendMail(@RequestParam("tmpUserEmail") String tmpUserEmail, HttpSession session) {
		String result = "";
		System.out.println("이메일 보내기");
		
		  String authCode = UUID.randomUUID().toString();
		  System.out.println("authCode: " + authCode);
		  
		  try {
			// new SendMailService().sendMail(tmpUserEmail, authCode);
			
			result = "success";
			
			// 인증코드를 세션 객체에 저장
			session.setAttribute("authCode", authCode);
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		} 
		  
		  return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/checkAuthCode", method= RequestMethod.POST)
	public ResponseEntity<String> checkAuthCode(@RequestParam("tmpUserAuthCode") String tmpUserAuthCode, HttpSession session) {
		System.out.println("tmpUserAuthCode: " + tmpUserAuthCode);
		String authCode = (String) session.getAttribute("authCode");
		System.out.println("authCode in Session: " + authCode);
		
		String result = "fail";
		if(authCode != null) {
			if(tmpUserAuthCode.equals(authCode)) {
				result = "success";
			}
		}

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/clearAuthCode", method= RequestMethod.POST)
	public ResponseEntity<String> clearAuthCode(HttpSession session){
		System.out.println("pass");
		
		if(session.getAttribute("authCode") != null) {
			session.removeAttribute("authCode");
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	// 로그인
	@RequestMapping(value="/login")
	public void loginGET() {
	}

	@RequestMapping(value="/login", method= RequestMethod.POST)
	public void loginPOST(LoginDTO loginDTO, Model model) {
		String resultPage = "";
		
		System.out.println("loginDTO: " + loginDTO);
		
		try {
			MemberDTO loginMember = mService.login(loginDTO);
			
			System.out.println("loginMember: " + loginMember);
			
			if(loginMember != null) {
				System.out.println("로그인 성공");
				
				// 로그인 정보를 model 객체에 저장 -> 인터셉터로 넘겨서 나머지 로그인 처리
				model.addAttribute("loginMember", loginMember);
				
//				session.setAttribute("loginMember", loginMember);
//				resultPage = "redirect:/";
			} else {
				System.out.println("로그인 실패");
				resultPage = "redirect:/member/login";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public String logoutMember(HttpSession session) {
		System.out.println("로그아웃");
		
		if(session.getAttribute("loginMember") != null) {
			session.removeAttribute("loginMember");
			
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	// 유저 이미지 가져오기
	/*
	 * public String getMemberImg() {
	 * 
	 * 
	 * }
	 */
}

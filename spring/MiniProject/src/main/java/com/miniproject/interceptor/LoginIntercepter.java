package com.miniproject.interceptor;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Timestamp;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.miniproject.domain.AutoLoginInfo;
import com.miniproject.domain.MemberDTO;
import com.miniproject.service.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginIntercepter extends HandlerInterceptorAdapter {

	@Inject
	private MemberService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean isLoginPageShow = false;
		
		HttpSession ses = request.getSession();

		if (ses.getAttribute("loginMember") != null) {
			System.out.println("cleaning loginMember...");
			ses.removeAttribute("loginMember");
		}

		
		
		
		if (request.getMethod().toUpperCase().equals("GET")) {
			// 자동 로그인
			
			
			if(request.getParameter("redirectUrl") != null) {
				// 댓글을 작성하기 위해 로그인 페이지 이동한 경우
				String uri = request.getParameter("redirectUrl");

				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				ses.setAttribute("destPath", "/cboard/viewBoard?boardNo=" + boardNo);
			}
			
			
			
			Cookie autoLoginCookie = WebUtils.getCookie(request, "al");
			if (autoLoginCookie != null) {
				String savedCookieSesId = autoLoginCookie.getValue();

				MemberDTO autoLoginUser = service.checkAutoLogin(savedCookieSesId);
				System.out.println("autoLoginUser: " + autoLoginUser);

				ses.setAttribute("loginMember", autoLoginUser);

				String dp = (String) ses.getAttribute("destPath");

				if (dp != null) {
					response.sendRedirect(dp);
					isLoginPageShow = false;
				} else {
					response.sendRedirect("/");
				}
			} else {
				//자동 로그인 쿠키가 없는 경우

				// 로그인 하지 않은 경우 -> 로그인 페이지 보여줌
				if (ses.getAttribute("loginMember") == null) {
					log.info("쿠키가 없고, 로그인 하지 않은 경우 로그인 페이지를 보여준다.");
					isLoginPageShow = true;
				}
				// 로그인 한 경우 -> 로그인 페이지 보여주지 않음

			} 
		} else if(request.getMethod().toUpperCase().equals("POST")) {
			// 로그인 버튼을 눌렀을 때
			isLoginPageShow = true;
		}
		
		
		
		return isLoginPageShow;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (request.getMethod().toUpperCase().equals("POST")) {
			Map<String, Object> model = modelAndView.getModel();
			MemberDTO loginMember = (MemberDTO) model.get("loginMember");

			System.out.println("loginMember: " + loginMember);

			if (loginMember != null) {
				// 세션에 로그인한 유저의 정보를 저장
				HttpSession ses = request.getSession();
				ses.setAttribute("loginMember", loginMember);


				if(request.getParameter("remember") != null) {
					saveAutoLoginInfo(request, response);
				}

				// 일반적인 경우
				// 글쓰기 버튼을 통해 로그인한 경우
				// 로그인 전에 저장된 경로가 있다면 해당 경로로 이동하고, 없다면 홈으로 이동
				System.out.println("ses.getAttribute(\"destPath\"): " + ses.getAttribute("destPath"));
				if (ses.getAttribute("destPath") != null) {

					response.sendRedirect((String) ses.getAttribute("destPath"));

				} else {
					// destPath 없는 경우
					response.sendRedirect("/");
				}

			} else {
				response.sendRedirect("/member/login?status=fail");
			}
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
	private void saveAutoLoginInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

	      String sesId = request.getSession().getId();
	      MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
	      String loginUserId = loginMember.getUserId();
	      Timestamp allLimit = new Timestamp(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));

	      if(service.saveAutoLoginInfo(new AutoLoginInfo(loginUserId, sesId, allLimit))) {
	    	  // 쿠키 생성 - 저장
	    	  Cookie autoLoginCookie = new Cookie("al", sesId);
	    	  
	    	  autoLoginCookie.setMaxAge(7 * 24 * 60 * 60);
	    	  autoLoginCookie.setPath("/");
	    	  
	    	  response.addCookie(autoLoginCookie);
	    	  
	    			  
	      }
	   }

}

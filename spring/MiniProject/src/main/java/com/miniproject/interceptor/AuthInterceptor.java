package com.miniproject.interceptor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.MemberDTO;
import com.miniproject.service.hboard.CBoardService;
import com.miniproject.service.hboard.HBoardService;
import com.miniproject.util.DestinationPath;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Inject
	HBoardService service;

	@Inject
	CBoardService cService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthInterceptor preHandle() 동작...");

		// 로그인 여부 검사
		// 로그인이 되어있는 경우 -> 글쓰기 페이지 이동
		// 로그인이 되어있지 않은 경우 -> 로그인페이지로 끌려감

		// 기존에 하려고 했던 동작을 위한 요청주소 저장

		HttpSession ses = request.getSession();
		new DestinationPath().setDestPath(request);

		boolean goOriginPath = false;

		if (ses.getAttribute("loginMember") == null) {
			// 미로그인
			System.out.println("미로그인");

			response.sendRedirect("/member/login");

		} else {
			System.out.println("로그인 중");
			// 로그인
			String uri = request.getRequestURI();

			if (uri.contains("modify") || uri.contains("removeBoard")) {
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				MemberDTO loginMember = (MemberDTO) ses.getAttribute("loginMember"); // 로그인 유저 정보 알기 위함

				if (uri.contains("hboard")) {
					// 수정이나 삭제 페이지에서 왔다면 그 글에 대한 수정/삭제 권한(본인글)이 있는지?

					List<BoardDetailInfo> board = service.read(boardNo); // 작성자 정보 알기 위함
					if (!board.get(0).getWriter().equals(loginMember.getUserId())) {
						response.sendRedirect("/hboard/viewBoard?status=authFail&boardNo=" + boardNo);
						System.out.println("작성자와 로그인한 유저의 아이디가 다르므로 쫒겨남");

						return false;
					} else {

						goOriginPath = true;
					}
				} else if (uri.contains("cboard")) {
					BoardDetailInfo board = cService.read(boardNo);

					if (!board.getWriter().equals(loginMember.getUserId())) {
						response.sendRedirect("/cboard/viewBoard?status=authFail&boardNo=" + boardNo);
						System.out.println("작성자와 로그인한 유저의 아이디가 다르므로 쫒겨남");

						return false;
					} else {
						goOriginPath = true;
					}
				}
			}
			
			goOriginPath = true;
		}
		return goOriginPath;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	// 로그인 인증이 필요한 페이지에서 클라이언트가 현재 로그인 되어있는지 아닌지 검사
	// 로그인 인증이 필요한 페이지(글작성, 수정, 글삭제 등)
	// 로그인이 되어 있지 않으면, 로그인하도록 하고
	// 로그인이 되어 있다면, 클라이언트가 원래 하려던 작업(목적)을 수행

}

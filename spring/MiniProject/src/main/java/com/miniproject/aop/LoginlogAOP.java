package com.miniproject.aop;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.miniproject.domain.LoginDTO;
import com.miniproject.domain.MemberDTO;
import com.miniproject.persistence.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
@EnableAspectJAutoProxy
public class LoginlogAOP {

	private String logContent;
	private static Map<String, Integer> loginAttemptCount = new ConcurrentHashMap<String, Integer>();

	@Inject
	private MemberDAO mDao;
	

	@Around("execution(* com.miniproject.service.member.MemberServiceImpl.login(..))")
	public Object betweenMemberLogin(ProceedingJoinPoint pjp) throws Throwable {
		log.info("=====================AOP Before=================");
		// MemberServiceImpl.login 메서드가 실행되기 이전에 수행할 부분

		Object[] params = pjp.getArgs();
		log.info("AOP Before : pjp.getArgs = {}", Arrays.toString(params));

		LoginDTO loginDTO = (LoginDTO) params[0];
		String who = loginDTO.getUserId();
		log.info("{} 가 로그인하려고 함....", who);

		// 로그인 로그에 기록할 사항
		long curTime = System.currentTimeMillis();
		String loginTime = new Date(curTime).toString();

		String ipAddr = loginDTO.getIpAddr();

		Calendar now = Calendar.getInstance(); // 현재 날짜 시간 객체
		String year = now.get(Calendar.YEAR) + ""; // 2024
		String month = year + new DecimalFormat("00").format(now.get(Calendar.MONTH) + 1); // 202410
		String when = year + new DecimalFormat("00").format(now.get(Calendar.DATE)); // 20241002

		this.logContent = loginTime + "," + who + "," + ipAddr;

		log.info("=====================AOP After=================");
		Object result = pjp.proceed();

		if (result == null) {
			this.logContent += "login fail";

			// 5회 모두 실패하면, isLock = "Y" 로 MemberDTO를 수정해서 반환하자.
			result = saveLoginFail(who, result);
		} else {
			log.info(" ===== AOP After ==== {}", result.toString());
			this.logContent += "login success";
			removeLoginFail(who);
		}

		// csv로 저장
		// /resources/log
		ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();

		String realPath = request.getSession().getServletContext().getRealPath("/resources/logs");
		log.info("realPath = {}", realPath);

		FileWriter fw = new FileWriter(realPath + File.separator + "log_" + when + ".csv", true);
		fw.write(this.logContent + "\n");
		log.info("로그인 기록저장 완료...");

		fw.flush();
		fw.close();

		return result;
	}

	private Object saveLoginFail(String failUserId, Object result) throws Exception {
//	      log.info("loginAttemptCount : {}", loginAttemptCount);

		if (loginAttemptCount.containsKey(failUserId)) {
			// 기존에 로그인 실패한 적이 있다.
			int failCount = loginAttemptCount.get(failUserId);
			++failCount; // 로그인 시도 횟수 1 증가

			if (failCount <= 4) {
				loginAttemptCount.put(failUserId, failCount);
			} else if (failCount == 5) {
				log.info("계정 잠그자....");
				mDao.updateAccountLock(failUserId); // 계정 잠금 (isLock = 'Y')
				removeLoginFail(failUserId); // 계정 잠금 후 이전에 로그인 기록 삭제

				MemberDTO memberDTO = (MemberDTO) result;
				result = memberDTO.builder().userId(failUserId).isLock("Y").build();

			}

			log.info("userId = {}, failCount = {}", failUserId, failCount);

		} else {
			// 기존에 로그인 실패한 기록이 없다.
			loginAttemptCount.put(failUserId, 1);
			log.info("userId = {}, failCount = {}", failUserId, loginAttemptCount.get(failUserId));

		}

		outputLoginAttempCount();

		return result;
	}

	private void removeLoginFail(String who) {
		if (loginAttemptCount.containsKey(who)) {
			loginAttemptCount.remove(who);
		}

		outputLoginAttempCount();
	}

	private void outputLoginAttempCount() {
	      log.info("============ 로그인 시도 횟수 =============");
	      Set<String> keys = loginAttemptCount.keySet();
	      for (String k : keys) {
	         log.info("userId = {}, attemptCount = {}", k, loginAttemptCount.get(k));
	         
	      }
	}
}

package com.miniproject.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailService {
	private String username;
	private String password;
	
	public void sendMail(String emailAddr, String activationCode) throws FileNotFoundException, IOException, AddressException, MessagingException {
		String subject = "miniproject.com에서 보내는 회원가입 이메일 인증번호입니다.";
		String message = "회원가입을 환영합니다. 인증번호: " + activationCode + "를 입력하시고 회원가입을 완료하세요.";
		
		System.out.println(emailAddr);
		
		Properties props = new Properties();
		
		// 포트번호 587
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.auth", "true");
		
		getAccount();
		
		// 세션 생성
		Session mailSession = Session.getDefaultInstance(props, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
			
		});
		
		System.out.println(mailSession.toString());
		
		if(mailSession != null) {
			MimeMessage mime = new MimeMessage(mailSession);
			
			mime.setFrom(new InternetAddress("jingoo0825@naver.com"));
			mime.addRecipient(RecipientType.TO , new InternetAddress(emailAddr));
			
			mime.setSubject(subject);
			// mime.setText(message);
			
			String html = "<h1>가입을 환영합니다!</h1>";
			html += "<h2>메일 인증 코드 " + activationCode + "를 입력하시고 가입을 완료하세요.";
			mime.setText(html, "utf-8", "html");
			
			Transport.send(mime);
		}
	}

	private void getAccount() throws FileNotFoundException, IOException {
		Properties account = new Properties();
		account.load(new FileReader("D:\\lecture\\spring\\MiniProject\\src\\main\\webapp\\WEB-INF\\config.properties"));
		
		this.username = (String) account.get("username");
		this.password = (String) account.get("password");
		
		System.out.println(this.username);
		System.out.println(this.password);
	}
}

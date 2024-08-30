package com.spring.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class SessionFactoryTest {

	@Inject
	private SqlSessionFactory ssf;

	
	  @Test
	  public void sqlSessionFactoryTest () {
	  
	  SqlSession session = ssf.openSession();
	  System.out.println(session.toString()); }
	 

	
}

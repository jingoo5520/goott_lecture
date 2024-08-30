package com.spring.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.persistance.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;

	/*
	 * @Test public void getDateTimeTest() { System.out.println(dao.getDateTime());
	 * }
	 */

	/*
	 * @Test public void insertMemberTest() throws Exception { MemberDTO member =
	 * new MemberDTO("test", "1234", "test@abc.com", "010-1234-1234", "F", null,
	 * null, null, "테스트", null);
	 * 
	 * try { if (dao.insertMember(member) == 1) { System.out.println("회원 저장 성공");
	 * 
	 * } } catch (Exception e) { System.out.println("실패"); e.printStackTrace(); }
	 * 
	 * }
	 */

	/*
	 * @Test public void updateMemberTest() throws Exception{ MemberDTO member = new
	 * MemberDTO("Test", null, null, null, null, null, null,
	 * "uploadMember/noimage.png", null, null);
	 * 
	 * if(dao.updateMember(member) == 1) { System.out.println("업데이트 완료"); } }
	 */

	/*
	 * @Test public void deleteMemberTest() throws Exception { String userId =
	 * "test";
	 * 
	 * try { if (dao.deleteMember(userId) == 1) { System.out.println("삭제 완료"); } }
	 * catch(Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	/*
	 * @Test public void selectAllMemberTest() throws Exception { List<MemberDTO>
	 * list = dao.selectAllMembers();
	 * 
	 * for (MemberDTO member : list) { System.out.println(member.toString()); } }
	 */

	/*
	 * @Test public void selectMemberByUserIdTest() throws Exception{
	 * System.out.println(dao.selectMemberByUserId("test")); }
	 */

	@Test
	public void loginMemberTest() throws Exception {
		System.out.println(dao.loginMember("user2", "1234"));
	}
}

package com.test.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

public class FormProc 
{
		
		// 트랜젝션 :DB가 일괄적으로 처리햐애하는 작업 단위 
		// 한개의 트랜젝션안에는 다수개 sql이 포함될 때 그 작업이 성공한 것이다 
	    // 구성 sql이 하나라도 실패하면 작업 단위안의 성공한 sql도 취소해야한다 
		// 오류없이 트랜잭션이 성공하면 모든 sql문장의 결과를 영구적으로 반영해야한다 
	
	
		//commit() :Transcation이 성공했을 때 호출 
	    //rollback(): 트랜잭션안의 한개의 sql이라도 실패할 경우에 모든 sql을 취소한다 
		//java(JDBC): 에서는 자동으로 한문장마다 commit()이 호출된다
		// 모델 1은 소규모 사이트 
	
	public boolean saveForm(HttpServletRequest request) {
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobby");
		String sHistory = request.getParameter("history");
		String sAge = request.getParameter("age");
		String sBirth = request.getParameter("birth");
		String intro = request.getParameter("intro");
	
		//MemberVO, UserDAO, member 테이블에 저장
		//hobby 테이블 별도 생성
		MemberVO m = new MemberVO();
		m.setUid(uid);
		m.setPwd(pwd);
		m.setGender(gender);
		m.setHobby(hobbies);
		m.setHistory(sHistory);
		m.setAge(sAge);
		m.setBirth(sBirth);
		m.setIntro(intro);
		
		UserDAO dao = new UserDAO();
		boolean saved = dao.saveMember(m);
		return saved;
	}      
   
   
}

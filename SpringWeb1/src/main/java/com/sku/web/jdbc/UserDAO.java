package com.sku.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sku.web.User;

//스프링에서는 jdbc템플릿 이용함 어플리케이션 프포포티에 getConn 

@Repository
public class UserDAO 
{
	
	
	@Autowired  // new 안해도 됨 wire: 연결  자동연결 객체를 연결해줘    dependency injection 의존성 주입: 하나의 객체가 다른 객체의 의존성를 제공해줌 
	private  JdbcTemplate jdbcTemplate;  // new 안하면 값은 null PointEXecpetion 발생하지만 @Autowired쓰면 객체 주입됨
	
	
	
	
	
	
	 public List<User> getList() {
	      String sql = "SELECT * FROM users";
	      List<User> list = jdbcTemplate.query(sql, (rs, i)->new User(rs.getString("USERID"), rs.getString("USERPWD")));
	      return list;
	      
	      //query가 list 를 리턴함  근데 안에 익명함수가  User를 리턴 반복 여러행이 ResultSet rs에  rs.next  아무튼 리스트에 모은다    
	   }
	 
	 public List<User> getList2() {
	      String sql = "SELECT * FROM users";
	      List<User> list = jdbcTemplate.query(sql, 
	    		  (rs, i)->{
	    			
	    			  User u= new User();
	    		  u.setUsername(rs.getString("USERID"));
	    		  u.setPassword(rs.getString("USERPWD"));
	    		 
	    		  return u;
	    		  });
	      return list;
	      
	      //query가 list 를 리턴함  근데 안에 익명함수가  User를 리턴 반복 여러행이 ResultSet rs에  rs.next  아무튼 리스트에 모은다    
	   }
	 
	/*
	
 query
<User>
List<User> org.springframework.jdbc.core.JdbcTemplate.query(
String sql,
RowMapper<User> rowMapper
) throws DataAccessException
	 
	 * */
	 public boolean update(User u) {
		 String sql = "UPDATE users SET userpwd=? WHERE userid=? ";
		 
		 int rows= jdbcTemplate.update(sql,u.getPassword(),u.getUsername());
		 
		 return rows>0; 
		 
	 }

	 public User getUserByName(String username) {
		 String sql = "SELECT * FROM users WHERE userid=? ";
	      try {
	      User u = jdbcTemplate.queryForObject(sql, 
	    		  (rs,i)->new User(rs.getString("USERID"),rs.getString("USERPWD")),
	             username);
	            
	    		 return u; 
	      }catch(Exception ex) {
	    	  ex.printStackTrace();
	    	  
	      }
	      
	      return null;
	   }
	      //query가 list 를 리턴함  근데 안에 익명함수가  User를 리턴 반복 여러행이 ResultSet rs에  rs.next  아무튼 리스트에 모은다    
	   
	 public boolean delete(String username) {
		 String sql = "DELETE FROM users WHERE userid=? ";
		 
		 int rows= jdbcTemplate.update(sql,username);
		 
		 return rows>0; 
		 
	 }
	

	
		
	public boolean login2(User user) {
		
	      String sql = "SELECT * FROM users WHERE userid=? AND userpwd=?";
	     
	      User u = jdbcTemplate.queryForObject(sql, 
	            new Object[] {user.getUsername(),user.getPassword()},
	            (rs,i)->new User(rs.getString("USERID"), rs.getString("USERPWD")));
	    		return u!=null ? true:false;
	      
	      
	   }
	
	
	public boolean login3(User user) {
		
	      String sql = "SELECT * FROM users WHERE userid=? AND userpwd=?";
	      try {
	      User u = jdbcTemplate.queryForObject(sql, 
	    		  (rs,i)->new User(rs.getString("USERID"), rs.getString("USERPWD")),
	             user.getUsername(),user.getPassword());
	            
	    		  if(u !=null) return true; 
	      }catch(Exception ex) {
	    	  ex.printStackTrace();
	    	  
	      }
	      
	      return false;
	   }
	// 함수의 인터페이스 
	//queryForObject : sql에 주기 
	// 내부에서 print  자동으로 돌아감 
	// rs : resultSet     i= 순번 
	// 람다식 
	//(rs,i): 익명 클래스 객체의 메소드  괄호는 메소드 이름 없는 개체의 메소드 메소드의 파라미터  jdbcTemplate.queryForObject(,,) 3번째 메소드 
	
	// select  -> queryForObject  -> 오브젝트 한개       :가져오는 값이 있다 
	// 여러개 select 문장 목록 -> query -> 목록 집합 리턴  
	// insert,delete ,update : sql 문장에 영향을 받고 행수가 나옴  update()
	
	public boolean addUser(User u) 
	{
	 String sql = "INSERT  INTO users VALUES(?,?)";
	 int rows= jdbcTemplate.update(sql,u.getUsername(),u.getPassword());
	 
	 return rows>0; 
	}
	
/*
	@Override
	   public int insertAndGetId(UserVO u) {
	      String sql = "INSERT INTO user VALUES(upload_seq_NEXTVAL,?,?,?)";
	      KeyHolder keyHolder = new GeneratedKeyHolder();
	      jdbcTemplate.update(conn->{
	         PreparedStatement pstmt;
	         pstmt = conn.prepareStatement(sql, new String[] {"num"}); // 가져올 키에 해당하는 컬럼명 
	         pstmt.setString(1, u.getName());
	         pstmt.setString(2, u.getPhone());
	         pstmt.setString(3, u.getEmail());
	         return pstmt;
	      }, keyHolder);
	      return keyHolder.getKey().intValue();
	   }
     인설트할때 키홀더를 넣어준다  
	*/
}

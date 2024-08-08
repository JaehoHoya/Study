package com.test.sku.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO 
{
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private Connection getConn() 
   {
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "tiger");
         return conn;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   

  


public boolean login(User user)
{
	
	String sql="SELECT * FROM users WHERE userid=? AND userpwd=?";
	conn=getConn();

	try
	{
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1,user.getUid());
		pstmt.setString(2,user.getPwd());
		rs= pstmt.executeQuery();
		if(rs.next())
		{
			return true;
		}
	}
	catch(SQLException sqle) 
	{
		sqle.printStackTrace();
	}
	finally {
		closeAll();
	}
	
	
	
	
	
	return false;
}



public List<User> getList()
{
	String sql="SELECT * FROM users ";
	conn=getConn();

	try
	{
		pstmt =conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		List<User> list =new ArrayList<>();
		
		while (rs.next()) { // ResultSet의 모든 레코드를 반복
            String uid = rs.getString("USERID");
            String pwd = rs.getString("USERPWD");
            User user = new User(uid, pwd);
            list.add(user); // 리스트에 사용자 추가
        }
		return list;
	}
	catch(SQLException sqle) 
	{
		sqle.printStackTrace();
	}
	finally {
		closeAll();
	}

return null;
}

public User detail(String uid) 
{
	conn = getConn();

	try {
        pstmt = conn.prepareStatement("SELECT * FROM users WHERE userid=?");
        pstmt.setString(1, uid);
        rs = pstmt.executeQuery();
       
        if(rs.next())
        {
            
            String UserId= rs.getString("USERID");
            String UserPwd=rs.getString("USERPWD");

           User user =new User();
           user.setUid(UserId);
           user.setPwd(UserPwd);
           return user;
        }
        
        
    }catch(SQLException sqle) {
        sqle.printStackTrace();
    }finally {
        closeAll();
    }
    return null;
}


public boolean updatePwd(String uid,String pwd)
{
	
	
	conn = getConn();

	try {
        pstmt = conn.prepareStatement("UPDATE users SET userpwd=? WHERE userid=?");
        pstmt.setString(1, pwd);
        pstmt.setString(2, uid);
     
        int rows = pstmt.executeUpdate();
        return rows>0;
        
    }catch(SQLException sqle) {
        sqle.printStackTrace();
    }finally {
        closeAll();
    }


	return false;
}

public boolean delete(String uid)
{
	conn = getConn();
	
	try {
        pstmt = conn.prepareStatement("DELETE FROM users  WHERE userid=?");
       
        pstmt.setString(1, uid);
     
        int rows = pstmt.executeUpdate();
        return rows>0;
        
    }catch(SQLException sqle) {
        sqle.printStackTrace();
    }finally {
        closeAll();
    }



	return false;
}


public boolean add(User user)
{
	conn = getConn();
    try {
        
        
        String sql = "INSERT INTO users(userid,userpwd) VALUES (?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getUid());
        pstmt.setString(2,user.getPwd());

        int rows = pstmt.executeUpdate();
        return rows>0;
    }catch(SQLException sqle) {
        sqle.printStackTrace();
    }
	
	return false;
}


private void closeAll() 
{
    try {
       if(rs!=null) rs.close();
       if(pstmt!=null) pstmt.close();
       if(conn!=null) conn.close();
    }catch(Exception e) {
       e.printStackTrace();
    }
 }




}


package com.test.model1board;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.test.join.User;



public class BoardDAO {
	
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
     
	
	private void closeAll() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
     
     
     
	
	public int addBoard(Board b) 
	   {
	      conn = getConn();
	      String sql = "INSERT INTO board(bnum,title,author,contents,rdate, hit) "
	               + "VALUES(board_seq.NEXTVAL, ?,?,?,?,0) "
	               + "RETURNING bnum INTO ?";
	      try {
	         CallableStatement cstmt = conn.prepareCall("{call " + sql + "}");
	         
	         cstmt.setString(1, b.getTitle());
	         cstmt.setString(2, b.getAuthor());
	         cstmt.setString(3, b.getContents());
	         cstmt.setDate(4, b.getRdate());
	         
	         cstmt.registerOutParameter(5, Types.INTEGER);
	         
	         int rows = cstmt.executeUpdate();
	         int bnum = cstmt.getInt(5);
	         
	         System.out.println("추출된 시퀀스 값:" + bnum);
	         return bnum ;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      }
	      return 0;
	   }
	

	public Board getBoard(int bnum)
	{
		String sql = "SELECT * FROM board WHERE bnum=?";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("검색 성공 "+bnum);
				int boardNum = rs.getInt("BNUM");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				String contents = rs.getString("CONTENTS");
				java.sql.Date rdate = rs.getDate("RDATE");
				int hit = rs.getInt("HIT");
				
				System.out.println(" "+bnum);
				
				return new Board(boardNum, title, author, contents, rdate, hit);
				
				
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	
	public List<Board> getListBoard()
	{
        List<Board> list = new ArrayList<>();
        String sql = "SELECT * FROM board";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
            	int boardNum = rs.getInt("BNUM");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				String contents = rs.getString("CONTENTS");
				java.sql.Date rdate = rs.getDate("RDATE");
				int hit = rs.getInt("HIT");
            	
                list.add(new Board(boardNum, title, author, contents, rdate, hit));
                
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        
        return list;
        
    }
		
	
	
}

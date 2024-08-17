package com.test.book2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {



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
	
	

	public List<Book> getListBook()
	{
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM book";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
            	int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				java.sql.Date pubdate=rs.getDate("PUBDATE");
				int page = rs.getInt("PAGE");
				int price = rs.getInt("PRICE");
				String cover = rs.getString("COVER");
            	
                list.add(new Book(no,title,author,publisher,pubdate,page,price,cover));
                
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        
        return list;
        
    }

	
	public Book getBook(int no)
	{
		String sql = "SELECT * FROM book WHERE no=?";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bNo = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				String publisher = rs.getString("PUBLISHER");
				java.sql.Date pubdate=rs.getDate("PUBDATE");
				int page = rs.getInt("PAGE");
				int price = rs.getInt("PRICE");
				String cover = rs.getString("COVER");
            	
                return new Book(bNo,title,author,publisher,pubdate,page,price,cover);
				
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	


}
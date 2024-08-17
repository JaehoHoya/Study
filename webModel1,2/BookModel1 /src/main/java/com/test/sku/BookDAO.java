package com.test.sku;

import java.sql.Connection;
import java.sql.Date;
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
	
	public Book getBook(Book book) {
	       String sql = "SELECT * FROM book WHERE no = ?";
	       conn = getConn();
	       if (conn == null) {
	           System.err.println("Failed to make connection!");
	           return null;
	       }
	       try {
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setInt(1, book.getNo());
	           rs = pstmt.executeQuery();
	           if (rs.next()) {
	               book.setTitle(rs.getString("title"));
	               book.setAuthor(rs.getString("author"));
	               book.setPublisher(rs.getString("publisher"));
	               book.setPubdate(rs.getDate("pubdate")); // Date 타입으로 변경
	               book.setPage(rs.getInt("page"));
	               book.setPrice(rs.getInt("price"));
	               book.setCover(rs.getString("cover"));
	               return book;
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           closeAll(); // 리소스 닫기
	       }
	       return null;
	   }

	
}

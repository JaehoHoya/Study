package com.test.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Book2DAO {



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
	
	

	public List<Book2> getListBook()
	{
        List<Book2> list = new ArrayList<>();
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
            	
                list.add(new Book2(no,title,author,publisher,pubdate,page,price,cover));
                
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        
        return list;
        
    }

	
	public Book2 getBook(int no)
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
            	
                return new Book2(bNo,title,author,publisher,pubdate,page,price,cover);
				
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public Book2 getBook(Book2 book) {
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


	public List<Book2> bookList(List<Book2> nums) // 장바구니 상세정보로 불러오기 
	{
		conn = getConn();
		String sNums = "";
		for(int i=0;i<nums.size();i++) {
			sNums += nums.get(i).getNo() + ",";
		}
		if(sNums.equals("")) {
			sNums = "0";
		}else {
			sNums = sNums.substring(0,sNums.lastIndexOf(","));
		}
		String sql = "SELECT * FROM book WHERE no IN(" + sNums + ")";

		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			List<Book2> list = new ArrayList<Book2>();
			while(rs.next()) {
				Book2 b = new Book2();
				b.setNo(rs.getInt("NO"));
				b.setTitle(rs.getString("TITLE"));
				b.setAuthor(rs.getString("AUTHOR"));
				b.setPublisher(rs.getString("PUBLISHER"));
				b.setPubdate(rs.getDate("PUBDATE"));
				b.setPrice(rs.getInt("PRICE"));
				b.setPage(rs.getInt("PAGES"));
				b.setCover(rs.getString("COVER"));
				list.add(b);
			}
			return list;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}


}
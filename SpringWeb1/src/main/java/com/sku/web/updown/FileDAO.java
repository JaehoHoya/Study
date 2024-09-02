package com.sku.web.updown;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.sku.web.User;

import jakarta.servlet.ServletContext;

@Repository
public class FileDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean addUpload(UploadVO vo) {

		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		 String sql = "INSERT  INTO upload VALUES(upload_seq.NEXTVAL,?,?,?,?)";
		
		 jdbcTemplate.update(conn->{
	         PreparedStatement pstmt;
	         pstmt = conn.prepareStatement(sql, new String[] {"UPID"}); // 가져올 키에 해당하는 컬럼명 
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getWriter());
	         pstmt.setDate(3, vo.getRdate());
	         pstmt.setString(4,vo.getContent() );
	         return pstmt;
	      }, keyHolder);
	      int key = keyHolder.getKey().intValue();
	      
		  int rows =0;
		  System.out.println("upload key:"+key);
		 
		  
		  String sql2 = "INSERT  INTO UPFILE VALUES(upfile_seq.NEXTVAL,?,?,?,?)";
	 	  for (AttachVO attach : vo.getAttList()) {
	 	        rows= jdbcTemplate.update(sql2, attach.getFname1(), attach.getFname2(), attach.getFsize(), key);
	 	    }
	      
	 	return rows>0;
	 	
	}
	
	public List<UploadVO> getList()
	{
		String sql = "SELECT \n"
				+ "    u.upid,\n"
				+ "    u.title,\n"
				+ "    u.writer,\n"
				+ "    u.rdate,  \n"
				+ "    COUNT(f.fnum) AS file_count\n"
				+ "FROM \n"
				+ "    upload u\n"
				+ "LEFT OUTER JOIN \n"
				+ "    upfile f ON u.upid = f.upid\n"
				+ "GROUP BY \n"
				+ "    u.upid, u.title, u.writer, u.rdate\n"
				+ "ORDER BY \n"
				+ "    u.rdate";
		
		List<UploadVO> list =jdbcTemplate.query(sql, 
		  		  (rs, i)->{
		  			    
		  			    UploadVO vo = new UploadVO();  
		  			    vo.setUnum(rs.getInt("upid")); 
		                vo.setTitle(rs.getString("title"));
		                vo.setWriter(rs.getString("writer"));
		                vo.setRdate(rs.getDate("rdate"));
		                vo.setFileCount(rs.getInt("file_count"));
		               
		                return vo;
		  		  });
		System.out.println(list.size());
		return list;
	}
	
	public UploadVO getDetail(int Unum) {
	    String sql = "SELECT \n"
	            + "    u.upid,\n"
	            + "    u.title,\n"
	            + "    u.writer,\n"
	            + "    u.rdate,\n"
	            + "    u.content,\n"
	            + "    LISTAGG(f.fname, ', ') WITHIN GROUP (ORDER BY f.fname) AS fname1,\n"
	            + "    LISTAGG(f.fname2, ', ') WITHIN GROUP (ORDER BY f.fname) AS fname2\n"
	            + "FROM \n"
	            + "    upload u\n"
	            + "LEFT JOIN \n"
	            + "    upfile f ON u.upid = f.upid\n"
	            + "WHERE\n"
	            + "    u.upid = ?\n"
	            + "GROUP BY \n"
	            + "    u.upid, u.title, u.writer, u.rdate ,u.content";

	    try {
	        // Execute the query with the Unum parameter
	        UploadVO vo = jdbcTemplate.queryForObject(sql, new Object[]{Unum}, (rs, rowNum) -> {
	         
	        	UploadVO v = new UploadVO();
	            v.setUnum(rs.getInt("upid"));
	            v.setTitle(rs.getString("title"));
	            v.setWriter(rs.getString("writer"));
	            v.setRdate(rs.getDate("rdate"));
	            v.setContent(rs.getString("content"));
	            String files = rs.getString("fname1");
	            String files2 = rs.getString("fname2");
	            if (files != null && !files.isEmpty())
	            {
	                String[] fileNames = files.split(",\\s*");
	                String[] fileNames2 = files2.split(",\\s*");
	                
	                List<AttachVO> attachList = new ArrayList<>();
	                for (String fileName : fileNames) {
	                    AttachVO attachVO = new AttachVO();
	                    attachVO.setFname1(fileName);
	                    attachList.add(attachVO);
	                }
	                for (String fileName : fileNames2) {
	                    AttachVO attachVO = new AttachVO();
	                    attachVO.setFname2(fileName);
	                    attachList.add(attachVO);
	                }
	                
	                v.setAttList(attachList);
	            }
	            

	            return v;
	        });

	        return vo;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
}
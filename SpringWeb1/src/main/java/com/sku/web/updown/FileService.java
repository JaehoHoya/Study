package com.sku.web.updown;
import com.sku.web.updown.FileDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	
	
	@Autowired
	private FileDAO fileDAO ;
	
	public boolean addUpload(UploadVO vo)
	{
		boolean added = fileDAO.addUpload(vo);
		return added; 
	}
	
	public List<UploadVO> getList()
	{
		List<UploadVO> list=fileDAO.getList();
		
		return list;
	}
	
	public UploadVO getDetail(int Unum)
	{
		UploadVO detail =fileDAO.getDetail(Unum);
		
		return detail;
	}

}
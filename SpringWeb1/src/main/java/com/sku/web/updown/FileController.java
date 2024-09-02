package com.sku.web.updown;
/**
 * upload 테이블 
 * -unum(pk,번호 자동 증가), title, writer, rdate ,desc(설명 1 (파일 설명)
 * upfile 테이블                
 * -fnum(pk) ,fname1, unum , fname2, fsize   
 * 
 *  파일 업로드시 
 *  파일 이름이 중복되는 것을 방지하기 위한 방법  
 *  UUID uuid = UUID.randomUUID();
 * 	String s= uuid.toStirng();
 * 
 * 	업로드 
 *  -파일명에 uuid 적용하여 유일성 있게 저장 
 *  ex) abc.txt -> abc_asdasfadf.text
 *  fname1(원래 파일명) fname2(서버에서 임시로 바꾼 파일명)
 *  다운로드 
 *  -원래의 파일명으로 다운로드 되도록 설정 
 *  FileService 클래스를 사용해서 파일 관련 처리 
 */


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController 
{
   @Autowired
   ResourceLoader resourceLoader;

   @Autowired
   FileService fileService;
   
   
   @GetMapping("/upload")
   public String getForm() 
   {
      return "updown/upload_form";
   }
   
   @PostMapping("/upload")
   @ResponseBody
   public String upload(@RequestParam("files")MultipartFile[] mfiles,    //클라이언트가 업로드한 파일들을 MultipartFile 배열로 받습니다.
                        HttpServletRequest request,
                        @RequestParam("title") String title, 
                        @RequestParam("writer") String writer,
                        @RequestParam("content") String content)
                        
   {	          
	   
	   System.out.println("컨트롤러"+content);
   				UploadVO vo = new UploadVO();
   				vo.setTitle(title);
   				vo.setWriter(writer);
   				vo.setContent(content);
   				
   				java.util.Date utilDate = new java.util.Date();
   			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
   			    vo.setRdate(sqlDate);
   	
      ServletContext context = request.getServletContext();              //애플리케이션의 서블릿 컨텍스트를 가져옵니다.
      String savePath = context.getRealPath("/WEB-INF/files");           //파일이 저장될 절대 경로를 설정합니다.

      /* static/upload 디렉토리에 업로드하려면, 아래처럼 절대경로를 구하여 사용하면 된다
      * Resource resource = resourceLoader.getResource("classpath:/static");
      * String absolutePath = resource.getFile().getAbsolutePath();
      */ 
      try {
    	  UUID uuid = UUID.randomUUID();
 		   String s= uuid.toString();
    	  List<AttachVO> attachList = new ArrayList<>();
         for(int i=0;i<mfiles.length;i++) 
         {
            if(mfiles[0].getSize()==0) continue;
            mfiles[i].transferTo(
              new File(savePath+"/"+mfiles[i].getOriginalFilename()+"_"+s));
            /* MultipartFile 주요 메소드
            String cType = mfiles[i].getContentType();
            String pName = mfiles[i].getName();
            Resource res = mfiles[i].getResource();
            long fSize = mfiles[i].getSize();
            boolean empty = mfiles[i].isEmpty();
            */
  	       
  	  	   AttachVO  aVO = new  AttachVO(mfiles[i].getOriginalFilename(),
  				                         mfiles[i].getOriginalFilename()+"_"+s,
  				                         mfiles[i].getSize());
          
  		   attachList.add(aVO);
  		  
         }
         vo.setAttList(attachList);
         Boolean success = fileService.addUpload(vo);
         String msg = String.format("파일(%d)개 저장성공 (작성자:%s)", mfiles.length,writer);
         return msg;
         
      } catch (Exception e) {
         e.printStackTrace();
         return "파일 저장 실패:";
      }
   }
   
   	@GetMapping("/fileList") 
   	public String getList(Model model)
   	{
   		List<UploadVO> list = fileService.getList();
   		
   		model.addAttribute("list",list);
   		return "updown/fileList";
   	}
   
   @GetMapping("/detail/{Unum}")
   public String getDetail(@PathVariable("Unum") int Unum ,Model model)
   {
	   UploadVO  detail = fileService.getDetail(Unum);
	   model.addAttribute("d",detail);
	   return "updown/fileDetail";
   }
   
   
   
   
   
   
   
   
   
   
   @GetMapping("/download/{fnum}")
   public ResponseEntity<Resource> download(
                              HttpServletRequest request,
                              @PathVariable String filename)
   
   
   {
	  
      Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
      
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(contentType == null) {
            contentType = "application/octet-stream"; // 다운로드 해야한다는 것을 인식. 
        }
        // UUID 제거를 위한 로직 추가
        String baseFilename = removeUUIDFromFilename(filename);
        System.out.println(baseFilename);
        /* 파일명이 한글로 되어 있을 때 다운로드가 안되는 경우... */
        try {
        	baseFilename = new String(baseFilename.getBytes("UTF-8"), "ISO-8859-1"); // 한글을 로마자로   한글은 http:header에 못들어감  
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + baseFilename + "\"")
                .body(resource);                                   //세미클론 공백 필요 프로토콜 규칙
   }
   
   private String removeUUIDFromFilename(String filename) {
	    // UUID는 일반적으로 36자의 길이를 가지며, '-' 문자가 포함되어 있습니다.
	    // 파일명이 UUID를 포함한 경우, UUID를 제거하여 원본 파일명을 반환합니다.
	    String[] parts = filename.split("_");
	    if (parts.length > 1) {
	        return parts[0];  // UUID가 포함된 파일명에서 UUID 제거
	    }
	    return filename;  // UUID가 없는 경우 원본 파일명 반환
	}
}
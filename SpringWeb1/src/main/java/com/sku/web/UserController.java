package com.sku.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sku.web.jdbc.UserDAO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")  // model 이용할떄 
public class UserController {

	@Autowired
	private UserDAO dao;
	
	/*
	@GetMapping("")   
	public String index(Model m) 
	{
		m.addAttribute("user",new User());
	   return "login"; 
	}
	*/
	
	//@SessionAttributes("user") 사용할시 @ModelAttribute("user")이녀석이 세션의 값을 가져다가 쓰려함 근데 로그인할때 user에 값이 없기에 에러가 발생 
	// 위에는 같이 쓸경우 오류해결방밥 
	@GetMapping("")   
	public String index() 
	{
	   return "login"; 
	}
	 
	
	@PostMapping("/login")
	@ResponseBody 					//@ModelAttribute("user") User user, Model model -> jsp에서 불러 쓸수 있다.
	public Map<String,Boolean> login(User user )	//@RequestParam String username ,@RequestParam String password ,(1)HttpSession session        
	{
		Map<String,Boolean> success = new HashMap<>();
		
		
		//모델은 영역이냐?: 모델은 HTTP 요청 처리의 특정 영역(스코프)에서만 유효하며, 요청 범위(Request Scope)로 간주됩니다. 즉, 한 요청의 범위에서만 사용되며, 요청이 완료되면 모델의 데이터는 사라집니다.
	/*	
	    boolean ok=false;
		if(user.getUsername().equals("jaeho") && user.getPassword().equals("1234"))
		{
		    
			ok= true;
			//(1)session.setAttribute("user",user.getUsername());
			
		}
	*/	
		//request.getSession 안해도됨 서블릿을 거쳐오기때문에   Session만 선언 

		boolean ok=dao.login3(user);
		success.put("ok",ok);
		return success;
	
	}
	
	/*
	
		@SessionAttributes("uid") : 클래스 위에 선언  세션속성 uid라는 변수가 있으면  자동으로 세션에 저장함  
		@SessionAttribute(name="uid" ,requried=false) : 메소드 파라미터   -sessionGetAttribute  값이 없을때 null 에러 -> requried=false 에러 안남   로그인 검사 
		@SessionStatus : 메소드 파라미터 -sessionSetAttribute  로그아웃할때  세션에 저장되어 있는 상태 정보 
	
	 */
	
	@GetMapping("/logout")
	@ResponseBody 
	public Map<String,Boolean> logout(@SessionAttribute(name="user" ,required=false)User user, SessionStatus status)
	{
		Map<String,Boolean>  map = new HashMap<>();
		if (user == null) 
		{   
			map.put("ok",false);
	    }
		else 
	    {
	       status.setComplete(); // 종료(만료)를 의미  모든데이터 지우기.
	       map.put("ok",true);
	    }
	    return map;
		
	}
	
	@GetMapping("/addForm")
	public String addform() 
	{
		return "addForm";
	}
	
	@PostMapping("/add")
	@ResponseBody 					
	public Map<String,Boolean> addUser(User user )
	{
		Map<String,Boolean> success = new HashMap<>();
		
		
		boolean ok=dao.addUser(user);
		success.put("ok",ok);
		
		return success;
		
		
	}
	
	@GetMapping("/listForm") 	
	public String listform(Model model) 
	{
	   
	   model.addAttribute("users",dao.getList());
		
		return "userList";
	}
	@GetMapping("/json")
	@ResponseBody
	public User getJson()
	{
		return new User("Steve","1234");
	}
	
	/*  http://localhost/user/json
	
	   {
       "username": "Steve",
       "password": "1234"
       }
	
	 * */
	
	
	@GetMapping("/update/{username}/{password}")
	@ResponseBody
    public User update(@PathVariable String username,@PathVariable String password) 
    {
		User u= new User(username,password);
		boolean Updated = dao.update(u);
		System.out.println(Updated);
		User u2=dao.getUserByName(username);
		return u2;
				
    }	
	
	
	@GetMapping("/delete/{username}")
	public String delete(@PathVariable String username) 
    {
		
		boolean deleted = dao.delete(username);
		
		return "redirect:/user/listForm";
				
    }	
	
			
}

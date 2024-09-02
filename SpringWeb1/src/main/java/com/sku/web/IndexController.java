package com.sku.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
/*
 @ResponseBody를 사용하면 스프링이 자동으로 Map 객체를 JSON으로 변환합니다. 이는 Jackson 라이브러리와 같은 JSON 변환 라이브러리를 통해 이루어집니다.
 
 RestController: @RestController 어노테이션은 @Controller와 @ResponseBody를 결합한 것으로, 
 모든 메서드에 @ResponseBody를 자동으로 적용합니다. 
 
 */



@Controller // 서블릿이 찾는다 
@RequestMapping("/") // / 요청 
public class IndexController 
{

	@GetMapping("")   // 이용자가 Get방식 요청  http:localhost/
	public String index() 
	{
	   return "index"; //,.jsp 안붙여졌네   properties ,   pom.xml dependency 추가 
	}
	
	
	@GetMapping("gugu")
	public String gugu(HttpServletRequest request)
	{
		
		int dan =Integer.parseInt(request.getParameter("dan"));  //프레임 워크인데 이정도는 해주겠지  
		String str= "";
		for(int i=1;i<10;i++) 
		{
			str +=dan+"*"+i+"="+dan*i+ "\n";
			
		}
		System.out.println(str);
		return null;
	}
	//o.s.web.servlet.DispatcherServlet 이녀석이 받고 있음 
	/*
	@GetMapping("gugu2")
	public String gugu(@RequestParam("dan") int dan, Model model )
	{
		
		String str= "";
		List<String> list =new ArrayList<>();
		for(int i=1;i<10;i++) 
		{
			str=dan+"*"+i+"="+dan*i+ "\n";
			list.add(str);
		}
		//System.out.println(str);
		model.addAttribute("list",list);
		return "gugu";
	
	}
	*/
	
	@GetMapping("gugu2/{dan}")
	public String gugu(@PathVariable int dan, Model model ) // 이름이 틀릴때 @PathVariable("dan") int a 
	{
		
		String str= "";
		List<String> list =new ArrayList<>();
		for(int i=1;i<10;i++) 
		{
			str=dan+"*"+i+"="+dan*i+ "\n";
			list.add(str);
		}
		//System.out.println(str);
		model.addAttribute("list",list);
		return "gugu";
	
	}
	@GetMapping({"gugu3/","gugu3/{dan}"}) //url 다수개 요청  값이 있느냐 없느냐 
	public String gugu(@PathVariable("dan") Optional<Integer> opt, Model model ) // 이름이 틀릴때 @PathVariable("dan") int a 
	// Optional<Integer> opt 값이 없어도 오류 없음. 
	{
		if(opt.isEmpty())
		{
			//파라미터 없는 경우 
		}
		else 
		{ 
			//파라미터 있는 경우 
			opt.isPresent(); //boolean :true;
			int dan =opt.get(); 
			
			String str= "";
			List<String> list =new ArrayList<>();
			for(int i=1;i<10;i++) 
			{
				str=dan+"*"+i+"="+dan*i+ "\n";
				list.add(str);
			}
			//System.out.println(str);
			model.addAttribute("list",list);
		
		}
		
		return "gugu";
	
	}
	//파라미터 2개 
	@GetMapping("login/{uid}/{pwd}")
	public String login(@PathVariable("uid") String uid ,
			            @PathVariable("pwd") String pwd)
	{
		
		
	
	    return "login";
	}
	//map에 담기 
	@GetMapping("login2/{uid}/{pwd}")
	public String login2(@PathVariable Map<String,String> map) 
			            
	{
		
		
	
	    return "login";
	}
	//
	@GetMapping("getJson")  
	@ResponseBody // 아래 메소드가 응답의 몸통이야   //json으로 자동 변환 
	public  Map<String,String> jsonTest() // Map을 쓰면 자동으로 json??
	{
		Map<String,String> map = new HashMap<>();
		map.put("uid","smith");
		map.put("email","smith@gmail.com");
		map.put("phone","010-4588-4553");
		return map; 
	}
	
	@GetMapping("calculate")
	public  String calculateForm() 
	{
		
		return "calculate"; 
	}
	@GetMapping("calculate/{a}/{b}")
	@ResponseBody 
	public  Map<String,Integer> calculate(@PathVariable("a") int a ,
            @PathVariable("b") int  b) 
	{
		
		int clc = a+b;
		Map<String,Integer> result = new HashMap<>();
		
		result.put("result",clc);
		
		
		
		return result; 
	}
	
	
}

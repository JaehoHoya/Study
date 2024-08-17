package com.test.sku;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.json.simple.JSONObject;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardService {

	
	
	private HttpServletRequest request;
    private HttpServletResponse response;

    public BoardService() {}

    public BoardService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    
    
    public String process() {
        String cmd = request.getParameter("cmd");
        String viewPath = null;

        if (cmd == null) {
            return "/board/index.jsp";
        }

        switch (cmd) {
            case "loginForm":
            	
            	viewPath = "/board/loginForm.jsp";
            	
            	break;
            	
            	
            case "login":
            	String uid=request.getParameter("uid");
            	String pwd=request.getParameter("pwd");
            	User user = new User();
            	UserDAO Udao= new UserDAO();
            	user.setUid(uid);
            	user.setPwd(pwd);
            	boolean success =Udao.login(user);
            	HttpSession session = request.getSession();
            	if(success) session.setAttribute("uid", user.getUid());
            	sendJSON("success",String.valueOf(success));
                break;
                
                
            case "detail":
            	BoardDAO dao= new BoardDAO();
            	int bnum=Integer.parseInt(request.getParameter("bnum"));
            	Board board =dao.getBoard(bnum);
            	request.setAttribute("detail", board);
                viewPath = "/board/boardDetail.jsp";
                break;
                
                
            case "list":
            	BoardDAO dao2= new BoardDAO();
            	List<Board> list =dao2.getListBoard();
            	request.setAttribute("list", list);
                viewPath = "/board/boardList.jsp";
                break;
                
                
            case "addForm":
            	viewPath = "/board/boardAddForm.jsp";
                break;
                
                
            case "add":
            	String title=request.getParameter("title");
            	String contents=request.getParameter("contents");
            	addBoard(title,contents);
                break;
                
                
            case "delete":
            	BoardDAO dao5= new BoardDAO();
            	int bnum2=Integer.parseInt(request.getParameter("bnum"));
            	boolean deleted=dao5.deleteBoard(bnum2);
            	sendJSON("deleted",String.valueOf(deleted));
                break;
                
            case "updateForm":
            	BoardDAO d= new BoardDAO();
            	int num=Integer.parseInt(request.getParameter("bnum"));
            	Board b =d.getBoard(num);
            	request.setAttribute("detail",b);
            	viewPath = "/board/updateForm.jsp";
                break;
            case "update":
            	int num2=Integer.parseInt(request.getParameter("bnum"));
            	String newTitle=request.getParameter("title");
            	String newcontents=request.getParameter("contents");
            	BoardDAO dao22= new BoardDAO();
            	
            	boolean update =dao22.update(num2,newTitle,newcontents);
            	System.out.print("aasdasdasd"+update);
            	if(update) sendJSON("ok",String.valueOf(update));
            	
            	break;
            	
            case "logout":
            	  HttpSession session2 = request.getSession();
                  session2.invalidate(); // 세션 무효화
                  sendJSON("logout", "true");
                  break;
             
           
        }

        return viewPath;
    }

    private void sendJSON(String key, String value) {
        String json = String.format("{\"%s\":%s}", key, value);
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void sendJSON(JSONObject jsObj) {
       
        try (PrintWriter out = response.getWriter()) {
            out.print(jsObj.toJSONString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addBoard(String title, String contents)
    {
    	Board board = new Board();
    	BoardDAO dao= new BoardDAO();
    	java.util.Date uDate= new java.util.Date();
    	java.sql.Date sDate=new java.sql.Date(uDate.getTime());
    	
    	
    	
    	board.setRdate(sDate);
    	HttpSession session = request.getSession();
        board.setAuthor((String) session.getAttribute("uid"));
    	
    	board.setTitle(title);
    	board.setContents(contents);
    	int bnum= dao.addBoard(board);
    	boolean saved=bnum>0;
    	
    	
    	
    	JSONObject jsObj= new JSONObject();
    	jsObj.put("saved",saved);
    	jsObj.put("bnum",bnum);
    	
    	sendJSON(jsObj);
    	
    }


}

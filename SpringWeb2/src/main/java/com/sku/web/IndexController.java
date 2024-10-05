package com.sku.web;


import com.sku.web.emp.Emp;
import com.sku.web.emp.EmpDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Slf4j // 문자열 출력 타이핑 줄어들임  파일에 저장 할떄도 사용됨
@Controller
@RequestMapping("/")
public class IndexController
{
    @Autowired
    private EmpDAO empDAO;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("greeting", "Hello World");
        return "th/index";
    }

    @GetMapping("jsp")
    public String jsp(Model model){
        model.addAttribute("greeting", "Hello World");
        return "index";
    }
    @GetMapping("html")
    public String html(Model model){
        model.addAttribute("greeting", "Hello World");
        return "th/index2";
    }

    @GetMapping("emp")
    public String emp(Model model){
        Emp emp = new Emp();
        emp.setEmpno(11);
        emp.setEname("jaeho");
        emp.setSal(3300);
        emp.setJob("Clerk");


        emp.setHiredate("2020-12-11");


        model.addAttribute("emp", emp);
        return "th/index2" ;
    }
    @GetMapping("list")
    public String list(Model model){

        List<Emp> list= new ArrayList<>();
        Emp emp = new Emp();
        emp.setEmpno(12);
        emp.setEname("hoya");
        emp.setSal(3500);
        emp.setJob("Developer");
        emp.setHiredate("2020-12-22");
        list.add(emp);

        Emp emp2 = new Emp();
        emp2.setEmpno(1);
        emp2.setEname("h");
        emp2.setSal(3);
        emp2.setJob("D");
        emp2.setHiredate("2020-12-11");
        list.add(emp2);

        model.addAttribute("list",list);

        return "th/index2";

    }

    @GetMapping("login")
    public String login(Model model){
        User u = new User();
        model.addAttribute("user", u); // 빈 오브젝트를 넣어줌 왜? 왜? 왜? 영역에 저장한거라서  폼에 있는 네임들을 바꿔줌 ! 우와 이건 지리네 그래서 이걸해주면 반드시 POST의 USER에 들어가짐
        return "th/login";
    }

    @PostMapping("login")
    @ResponseBody
    public String loginChek(User user)  // 폼에 있는 필드명이  User 변수명과 일치해야함
    {
        if(user.getUid().equals("smith")&&user.getPwd().equals("1234"))
        {   System.out.println("로그인 성공");
            log.info("로그인 성공");
            //log.debug();
            //log.error();
            return "로그인 성공";

        }else {log.warn("로그인 실패");}


        return "로그인 실패";
    }

    @GetMapping("emps")
    public String index2(Model model)
    {
        List<Emp> list = empDAO.empList();
        log.info(list.toString());
        model.addAttribute("emps", list);
        return  "th/empList";
    }

}



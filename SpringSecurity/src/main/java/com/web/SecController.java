package com.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/board")
public class SecController
{

    @GetMapping("")
    @ResponseBody
    public String index(Model model)

    {
        return "Hello Spring Security Index";
    }
    @GetMapping("loginForm")
    public String loginForm(@RequestParam(value = "error" , required = false) String error,
                            @RequestParam(value = "logout",required = false) String logout
                                , Model model)
    {
        if(error !=null)
        {
            model.addAttribute("error" , "아이디 또는 비밀번호를 틀렸어요");
        }
        else if(logout !=null)
        {
            if(logout.contains("T")) {
                model.addAttribute("logout", "로그아웃 성공");
            }
        }

        return "th/loginForm";
    }


    @GetMapping("/menu")
    public String menu()
    {
        return "th/menu";
    }

    @GetMapping("/denied")
    public String denied()
    {
        return "th/denied";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(Model model){

        // 인증정보가 들어 있어요!
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자의 세부 정보
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        String roles = authentication.getAuthorities().toString();
        log.info("username={}, roles={}", username, roles);

        return "username : " + username + " roles : " + roles;
    }
}

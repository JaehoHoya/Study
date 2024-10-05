package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mail")
public class EmailController
{
    @Autowired
    private EmailService svc;

    @GetMapping("")
    @ResponseBody
    public String index()
    {
        return "Java Mail Test";
    }

    @GetMapping("/test")
    @ResponseBody
    public String sendTestMail(HttpSession session)
    {
        boolean isSent = svc.sendHTMLMessage();
        //boolean isSent = svc.sendAttachMail();
        return isSent ? "메일 보내기 성공":"메일 보내기 실패";
    }

    @GetMapping("/auth/{code}")  // 보낸 메일에서 이용자가 인증 링크를 클릭했을 때
    @ResponseBody
    public String index(@PathVariable("code")String returnCode, HttpSession session)
    {
        String authCode = (String)session.getAttribute("authCode");
        if(returnCode.equals(authCode)) {
            return "이메일 인증 성공";
        }
        log.info("인증코드 확인={}", returnCode);
        return "이메일 인증 실패";
    }

    @GetMapping("/menu1")
    public String menu(){
        return "th/ws/menu1";
    }

}
package com.web.board;


import com.web.board.jpa.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    @GetMapping("loginForm")
    public String loginForm(@RequestParam(value = "error" , required = false) String error,
                            @RequestParam(value = "logout",required = false) String logout,Model model)
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

    // 메인 페이지
    @GetMapping("/main")
    public  String menu(Model model)

    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        model.addAttribute("username" , username);
        List<Board> list=service.getBoardList();
        model.addAttribute("list", list);
        return "th/main";
    }

    //상세 페이지
    @GetMapping("detail/{bnum}")
    public  String detail(@PathVariable int bnum, Model model)
    {

        Board board =service.getBoardDetail(bnum);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();

        model.addAttribute("username" , username);

        model.addAttribute("b", board);
        return "th/detail";
    }

    //게시물 추가 페이지로
    @GetMapping("/addForm")
    public String addForm(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        Board board = new Board();
        board.setAuthor(username);


        model.addAttribute("b",board);
        return "th/addForm";
    }

    // 게시글 추가
    @PostMapping("/add")
    public String add(Board board)
    {
        board.setRdate(Date.valueOf(LocalDate.now()));
        board.setHit(1);
        Boolean added =service.addBoard(board);

        return "redirect:./main";
    }

    // 게시글 수정폼으로
    @GetMapping("/updateForm/{bnum}")
    public  String updateForm(@PathVariable int bnum, Model model)
    {
        Board board =service.getBoardDetail(bnum);
        model.addAttribute("b", board);
        return "th/updateForm";
    }

    // 게시글 수정
    @PostMapping("/update")
    public String update(Board board)
    {
        board.setRdate(Date.valueOf(LocalDate.now()));
        board.setHit(1);
        service.updateBoard(board);
        return "redirect:./main";
    }

    // 게시글 삭제
    @GetMapping("/delete/{bnum}")
    public String delete(@PathVariable int bnum, Model model)
    {
        service.deleteBoard(bnum);
        return "redirect:/board/main";
    }


}

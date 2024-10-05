package com.sku.web.mb;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sku.web.emp.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookMapper bookMapper;

    //*******도서 정보*********//
    @GetMapping("/list/page/{page}")
    public String getPage(@PathVariable int page, Model model){
        log.info("getPage : {}", page);
        PageHelper.startPage(page,2);  // 한 화면에 3개 아이템
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.getBookList(), 5);
        //10을 안쓰먄 기본깂 8
        // 목록을 전부 가져와서 네비게이션 숫자를 가져와라
        model.addAttribute("pageInfo", pageInfo);
        return "th/mb/bookList2";
    }


    /*
    @GetMapping("/board/list2")
    public String getPage2(Pageable pageable, Model model)
    {
      Page<Board> pageInfo= req.findAll(pageable);
      List<Board> list =pageInfo.getComtent();
      model.addAttribute("page",pageInfo);

      return "th/board/list";
    }
    */













    @GetMapping("")
    public String bookList(Model model)
    {
        List<Book> list=bookService.getBookList();
        model.addAttribute("books",list);
        return "th/mb/bookList";
    }

    //*******도서 상세보기*********//
    @GetMapping("/Detail/{no}")
    public String bookDetail(@PathVariable int no, Model model)
    {
        Book book =bookService.getBookByNo(no);
        model.addAttribute("book",book);
        return "th/mb/bookDetail";
    }

    //*******도서 추가 페이지*********//
    @GetMapping("/addForm")
    public String addForm()
    {
        return "th/mb/bookAddForm";
    }
    @PostMapping("add")
    public String addBook(Book book)
    {
        int added=bookService.addBook(book);
        int generatedKey= book.getNo(); // 시퀸스가 생성된사번
        System.out.println("key값 확인:"+generatedKey);
        if(added>0)return  "redirect:./Detail/"+generatedKey;
        else  return  "th/mb/bookAddForm";
    }
    //*******도서 정보 삭제*********//
    @GetMapping("/deleteBook/{no}")
    @ResponseBody
    public  Map<String,Boolean> deleteBook(@PathVariable int no)
    {
        Map<String,Boolean> map=new HashMap<>();
        int rows = bookService.deleteBook(no);
        map.put("deleted", rows>0);
        return  map;
    }
    //*******도서 정보 수정 *********//
    @GetMapping("updateForm/{no}")
    public  String updateForm(@PathVariable int no,Model model)
    {
        Book book =bookService.getBookByNo(no);
        model.addAttribute("book",book);
        return "th/mb/updateForm";
    }

    @PostMapping("update")
    public String update(Book book)
    {
        boolean updated =bookService.update(book);
       if(updated)return  "redirect:/book";
      return "th/mb/bookUpdateForm";
    }







    @GetMapping("/search")
    public String searchForm(Model model) {

        Book books = new Book();
        model.addAttribute("book", books);
        return "th/mb/bookSearch";
    }


    @PostMapping("/search")
    @ResponseBody
    public List<Book> search(@RequestParam Map<String,String> info)
    {
        log.info("search form : {}",info);
        List<Book> books =bookMapper.searchByTitleOrAuthor(info);

        return books;
    }

    @GetMapping("/search/{low}/{high}")
    @ResponseBody
    public List<Book>  searchForm(@PathVariable int low,@PathVariable int high)
    {
            List<Book> books= bookMapper.searchRange(low,high);
            return books;
    }

    @GetMapping({"/search/author","/search/author/{author}"})
    @ResponseBody
    public List<Book> searchAuthor(@PathVariable(required=false) String author)
    {
        List<Book> books = bookMapper.searchAuthor(author);
        return books;
    }

    @GetMapping("/search/pubAndPrice")
    @ResponseBody
    public List<Book> searchPunAndPrice(Book book)
    {
        book.getPublisher();
        book.getPrice();
        List<Book> books=bookMapper.pubAndPrice(book);
        return books;
    }


}

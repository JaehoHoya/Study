package com.sku.web.mb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    //*******도서 정보 출력*********//
    public List<Book> getBookList()
    {
        List<Book> books = bookMapper.getBookList();
        return books;
    }

    //*******도서 상세 정보 출력*****//
    public Book getBookByNo(int no)
    {
        Book book = bookMapper.getBookByNo(no);

        return book;
    }
    //*******도서 정보  추가********//
    public int addBook(Book book)
    {
        int success =bookMapper.addBook(book);

       return success;
    }
    //*******도서 정보  삭제********//
    public int deleteBook(int no)
    {
        int success =bookMapper.deleteBook(no);
        return success;
    }

    //*******도서 정보  수정********//
    public boolean update(Book book)
    {
        int success =bookMapper.updateBook(book);
        if(success >0) return true;
        else return false;
    }

}

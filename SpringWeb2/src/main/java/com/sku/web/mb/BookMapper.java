package com.sku.web.mb;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {

    //*******도서 정보 출력*********//
    public List<Book> getBookList();

    //*******도서 상세 정보 출력*****//
    public Book getBookByNo(int no);

    //*******도서 정보 추가*********//
    public int addBook(Book book);

    //*******도서 정보 삭제*********//
    public int deleteBook(@Param("no") int no);

    //*******도서 정보 수정*********//
    public int updateBook(Book book);










    public List<Book> searchByTitleOrAuthor(Map<String, String> info);

    public List<Book> searchRange(@Param("low") int low, @Param("high") int high );

    //위처럼 파라미터에 이름을 붙여주면, xml에서는 파라미터 타입을 사용 안해도 됨

    public List<Book> searchAuthor(@Param("author") String author);

    public List<Book> pubAndPrice(Book book);


}

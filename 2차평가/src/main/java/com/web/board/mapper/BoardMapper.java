package com.web.board.mapper;

import com.web.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    //게시글 리스트
    List<Board> getBoardList();
    //게시글  상세보기
    Board getBoardDetail(int bnum);
    //글 추가하기
    int addBoard(Board board);
    //글 수정하기
    boolean updateBoard(Board board);
    //글 삭제하기
    boolean deleteBoard(int bnum);

    //작성자 또는 제목 검색
    List<Board> searchByTitleOrAuthor(Map<String, Object> info);
}

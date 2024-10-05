package com.web.board;

import com.web.board.jpa.Board;
import com.web.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    //게시글 전체 목록 조회
    public List<Board> getBoardList()
    {
        return repository.findAll();
    }

    //게시물 상세보기
    public Board getBoardDetail(int bnum)
    {
        Board board =repository.findBoardByBnum(bnum);
        return board;
    }
    //게시물 추가하기
    public boolean addBoard(Board board)
    {
      Board saved= repository.save(board);
      if(saved!=null) return true;
      else return false;
    }

    // 게시글 수정하기
    public  boolean updateBoard(Board board)
    {

        repository.save(board);
        return false;
    }


    // 게시글 삭제하기
    public boolean deleteBoard(int bnum)
    {
        repository.deleteById(bnum);
        return true;
    }

}

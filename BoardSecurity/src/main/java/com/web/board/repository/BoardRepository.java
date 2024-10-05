package com.web.board.repository;


import com.web.board.jpa.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //상세보기
    Board findBoardByBnum(int bnum);
}

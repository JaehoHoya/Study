package com.web.board;

import com.web.board.mapper.BoardMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    FileService fileService;

    //게시글 전체 목록 조회
    public List<Board> getBoardList()
    {
        return boardMapper.getBoardList();
    }

    //게시물 상세보기
    public Board getBoardDetail(int bnum)
    {

        return boardMapper.getBoardDetail(bnum);
    }

    //게시물 추가하기
    @Transactional
    public boolean addBoard(Board board, MultipartFile[] files )
    {
            // 로그 추가
            System.out.println("addBoard 호출됨: " + board);

            // 게시글 추가
            int savedBoard = boardMapper.addBoard(board);
            if (savedBoard > 0)
            {
                int bnum = board.getBnum(); // 자동 생성된 게시글 번호 가져오기

                // 파일 저장
                fileService.saveFiles(bnum, files);
            }
            return true;


    }
    // 게시글 수정하기
    public  boolean updateBoard(Board board)
    {

       boardMapper.updateBoard(board);
        return true;
    }

    // 게시글 삭제하기
    @Transactional
    public boolean deleteBoard(int bnum)
    {
        fileService.deleteFile(bnum);
        boardMapper.deleteBoard(bnum);
        return true;
    }

    public List<Board> search(Map<String,Object> info)
    {
        List<Board>  list= boardMapper.searchByTitleOrAuthor(info);
        return list;
    }

}

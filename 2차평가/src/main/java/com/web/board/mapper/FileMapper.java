package com.web.board.mapper;


import com.web.board.Board;
import com.web.board.BoardFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    int add(BoardFile file);

    int downCount(int no);

    int deleteFile(int no);
}

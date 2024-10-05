package com.web.board;

import com.web.board.mapper.BoardMapper;
import com.web.board.mapper.FileMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    FileMapper fileMapper;


    public  int saveFiles(int bnum , MultipartFile[] files)
    {

        UUID uuid = UUID.randomUUID();
        String u= uuid.toString();

        List<BoardFile> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = originalFileName + "_" + u;

            BoardFile boardFile = new BoardFile();
            boardFile.setFname1(originalFileName); // 원본 파일 이름
            boardFile.setFname2(uniqueFileName); // UUID가 포함된 고유 파일 이름
            boardFile.setFsize((int) file.getSize()); // 파일 크기 (바이트 단위)
            boardFile.setBnum(bnum); // 게시글 번호 (파일과 게시글 연결)

            fileList.add(boardFile);
        }


        for (BoardFile file : fileList) {
            fileMapper.add(file);
        }


        return 1;
    }


    public String deleteFile(int bnum)
    {

        fileMapper.deleteFile(bnum);


        return null;
    }
}

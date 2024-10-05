package com.web.board;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int bnum ;
    private String author ;
    private String title ;
    private String contents ;
    private Date  Rdate ;
    private int hit;
    private int fileCount;

    private String fname1; // 콤마로 구분된 파일 이름 1
    private String fname2; // 콤마로 구분된 파일 이름 2

    // 파일 이름을 리스트로 변환하는 메서드
    public List<String> getFileList1() {
        return Arrays.asList(fname1.split(", "));
    }

    public List<String> getFileList2() {
        return Arrays.asList(fname2.split(", "));
    }

}

package com.web.board;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardFile {

    private int fnum;
    private String fname1;
    private String fname2;
    private int fsize;

    private int bnum;
}

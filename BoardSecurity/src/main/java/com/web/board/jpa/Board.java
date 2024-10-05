package com.web.board.jpa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "BOARD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    @SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", allocationSize = 1)
    private int bnum ;

    private String author ;
    private String title ;
    private String contents ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  Rdate ;
    private int hit;

}

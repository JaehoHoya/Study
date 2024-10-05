package com.sku.web.mb;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int no;
    private String title;
    private String author;
    private String publisher;
    private Date pubDate;
    private int page;
    private int price;
    private String cover;

}

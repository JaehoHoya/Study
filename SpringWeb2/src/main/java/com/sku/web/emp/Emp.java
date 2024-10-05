package com.sku.web.emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

//데이타  클래스 :VO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private java.sql.Date hiredate;
    private int sal;
    private int comm;
    private int deptno;
   ;


    public void setHiredate(String sDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date udate = sdf.parse(sDate);
            this.hiredate = new java.sql.Date(udate.getTime());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

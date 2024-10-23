package PDS;

import java.io.Serializable;
import java.util.Date;

// 서버측으로 전달되는 통신 데이터를 모두 포함하는 클래스
public class PDSRequest implements Serializable
{
    //public  static  enum CMD {UPLOAD, LIST , DETAIL, FIND , UPDATE ,DELETE}; // 자료형으로서 의미가됨
    // private CMD cmd;
    int no;
    String fname;
    byte[] fdata;
    String author;
    Date date;
    String desc;

    boolean upload;
    boolean showFiles;
    boolean viewDetails;
    boolean search;
    boolean update;
    boolean delete;

    // 객체지향적으로 만들것 ......... -부족한점-
}

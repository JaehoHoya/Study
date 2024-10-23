package PDS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class PDSVO implements Serializable {

    private  int no ;
    private  String fname;
    private  byte[]   flen;
    private  String author;
    private  String date;
    private  String desc;
    private  String key;
    public PDSVO(int no, String fname,byte[] fdata,String author, String date, String desc)
    {
        setNo(no);
        setFname(fname);
        setFlen(fdata);
        setAuthor(author);
        setDate(date);
        setDesc(desc);
    }


    public  PDSVO(String fname){
        this.fname=fname;
        this.key="fname";
    };

    public  PDSVO(int no){
        this.no=no;
        this.key="no";
    }
    @Override
    public boolean equals(Object obj) {
        PDSVO other=(PDSVO) obj;
        if(key.equals("no")) return  this.getNo()==other.getNo();
        else if(key.equals("fname"))return this.getFname() != null && this.getFname().equals((other.getFname()));
        else  return  false;
    }

    @Override
    public String toString() {
        return String.format("%d번\t파일명:%-10s\t파일크기:%d[byte]\t작성자:%-10s\t날짜:%s\t내용:%s"
                ,no,
                fname,
                (flen != null ? flen.length : 0),
                author,
                date,
                desc );
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public byte[] getFlen() {
        return flen;
    }

    public void setFlen(byte[] flen) {
        this.flen = flen;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

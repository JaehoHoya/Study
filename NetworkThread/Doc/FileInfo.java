package Doc;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileInfo implements Serializable {

    int number ;

    String fileName;

    byte[] fileData;

    String who ;

    String writeDate;

    String content;

    public  FileInfo(){};
    public  FileInfo(String fileName){
        setFileName(fileName);
    }

    public FileInfo(int i, String fileName, byte[] fileData, String who, String number, String content) {

        setNumber(i);
        setFileName(fileName);
        setFileData(fileData);
        setWho(who);
        setWriteDate(number);
        setContent(content);

    }

    public  FileInfo(int number){
        this.number=number;
    }

    @Override
    public String toString() {
        return String.format("%d\t파일명:%s\t파일크기:%d[byte]\t작성자:%s\t날짜:%s\t내용:%s",number,
                fileName,
                (fileData != null ? fileData.length : 0),
                who,
                writeDate,
                content );
    }



    @Override
    public boolean equals(Object obj) {
       FileInfo other = (FileInfo)obj;

       return  this.fileName.equals(other.fileName);
    }


    public void FileIO(){};


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

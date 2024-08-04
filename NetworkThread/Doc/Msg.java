package Doc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Msg implements Serializable {


    String menu ;
    boolean showFiles;
    boolean upload;
    boolean delete;
    String response;
    boolean update;
    int number ;
    boolean search;
    List<FileInfo> data;
    String fileName;
    int idx;
    byte[] fileData;

    String who ;

    Date writeDate;

    String content;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

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

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

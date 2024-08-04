package Doc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {


    private  static  String srcPath="/Users/jeongjaeho/Downloads/";



    public byte[] load(String fname)
    {
        File f= new File(srcPath+ fname);

        if(!f.exists())
        {
            System.out.println(f.getPath()+" 파일이 없습니다.");
            return null;
        }

        try {
            FileInputStream fin = new FileInputStream(f);
            byte[] buf = new byte[(int) f.length()];
            fin.read(buf);
            fin.close();
            return buf;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public  boolean save(String fname, byte[] fdata)
    {
        String path= "/Users/jeongjaeho/Downloads/files/";
        try {
            FileOutputStream fout = new FileOutputStream(path+fname);
            fout.write(fdata);
            fout.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public  boolean deleteFile(String nmae) {

        String path = "/Users/jeongjaeho/Downloads/files/";
        File f = new File(path+nmae);
        if (f.exists()) {
            boolean deleted = f.delete();
            if (deleted) {
                return true;
            }
        }
        return false;
    }
    //저장
    public boolean Serialization(List<FileInfo> file)
    {
        try {

            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("/Users/jeongjaeho/Downloads/files/list_fileinfo.ser"));
            oos.writeObject(file);
            oos.flush();
            oos.close();
           return true;
        } catch (Exception e) {
            e.printStackTrace();

           return false;
        }


    }

    //꺼내오기
    public  List<FileInfo> deserialization()
    {

        String path ="/Users/jeongjaeho/Downloads/files/list_fileinfo.ser";
        File f = new File(path);
        if(!f.exists()){
            return new ArrayList<FileInfo>();
        }

        try{
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(path));
            List<FileInfo> list =(List<FileInfo>)ois.readObject();
            ois.close();

            return list;
        }catch (Exception e){
            System.err.println("파일 읽기 실패");
            e.printStackTrace();
            return  new ArrayList<>();
        }


    }

/*
    public void  search(FileInfo key){


        String ke="011";
        String path="/Users/jeongjaeho/Desktop";

        File f= new File(path);
        File[] files =f.listFiles();
        for(int i=0; i<files.length;i++){
            if(files[i].getName().equals(key)){
                System.out.printf("%s\t %d",files[i].getName(),files[i].length());
            }
        }



    }

 */
}

package Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileIO {

    private  static String srcPath ="/Users/jeongjaeho/Downloads/";
    private  static String savePath="/Users/jeongjaeho/Downloads/user/";

    public byte[] load(String fname) {



        File f= new File(srcPath+ fname);

        if(!f.exists()){
            System.out.println(f.getPath()+"파일이 없습니다.");
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

    public  boolean dowmload(String fname, byte[] fdata){

        try {
            FileOutputStream fout =new FileOutputStream(savePath+fname);
            fout.write(fdata);
            fout.close();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
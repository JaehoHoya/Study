package PDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PDSFileIO {

    private  static  String srcPath="/Users/jeongjaeho/Downloads/";

    public  byte[] load(String fname)
    {
        if (fname != null && !fname.equals(""))
        {
            File f = new File(srcPath + fname);
            if (!f.exists())
            {
                return null;
            }
            try
            {
                FileInputStream fin = new FileInputStream(f);
                byte[] buf = new byte[(int) f.length()];
                fin.read(buf);
                fin.close();
                return buf;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }
        return  null;

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


    public boolean serialization(List<PDSVO> file)
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




    public  List<PDSVO> deserialization()
    {

        String path ="/Users/jeongjaeho/Downloads/files/list_fileinfo.ser";
        File f = new File(path);
        if(!f.exists()){
            return new ArrayList<PDSVO>();
        }

        try{
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(path));
        List<PDSVO> list =(List<PDSVO>)ois.readObject();
            ois.close();

            return list;
        }catch (Exception e){
            System.err.println("파일 읽기 실패");
            e.printStackTrace();
            return  new ArrayList<>();
        }


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


}



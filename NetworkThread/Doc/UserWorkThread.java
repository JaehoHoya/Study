package Doc;

import Network.Client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserWorkThread extends  Thread
{

    private  Socket s;



    public UserWorkThread(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {

        try {
            OutputStream out =s.getOutputStream();
            ObjectOutputStream oos= new ObjectOutputStream(out);



                Msg msg = new Msg();
                msg.menu = "업로드(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x)";
                oos.writeObject(msg);
                oos.flush();

                InputStream in = s.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);

            while (true)
            {
                Msg clientMsg = (Msg) oin.readObject();

                // 클라이언트의 메뉴 선택에 따른 처리
                if (clientMsg.upload)
                {
                    if (clientMsg.fileData.length > 0)
                    {

                        boolean saved = new FileIO().save(clientMsg.fileName, clientMsg.fileData);
                        if (saved)
                        {
                            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                            String date= sdf.format(new Date());

                            List<FileInfo> files =new FileIO().deserialization();
                            FileInfo file = new FileInfo(getNextFileNumber(files),clientMsg.fileName,clientMsg.fileData,clientMsg.who,date,clientMsg.content);
                            files.add(file);

                            boolean saved2=new FileIO().Serialization(files);

                            Msg n = new Msg();
                            n.response = "파일 저장 성공";
                            oos.writeObject(n);
                            oos.flush();




                        }
                    }


                }
                if(clientMsg.showFiles)
                {

                    List<FileInfo> files = new FileIO().deserialization();
                    Msg list = new Msg();
                    list.data = files;

                    oos.writeObject(list);
                    oos.flush();
                }
                if(clientMsg.search)
                {

                    List<FileInfo> files = new FileIO().deserialization();

                    FileInfo key = new FileInfo(clientMsg.fileName);

                    if(files.contains(key))
                    {
                        int idx=files.indexOf(key);
                        Msg list= new Msg();

                        list.data= new ArrayList<FileInfo>();
                        list.data.add(files.get(idx));

                        oos.writeObject(list);
                        oos.flush();
                    }else
                    {
                        Msg list =new Msg();
                        list.response="파일이 없습니다.";
                        oos.writeObject(list);
                        oos.flush();

                    }



                }
                if(clientMsg.update)
                {

                    List<FileInfo> files = new FileIO().deserialization();

                    FileInfo key = new FileInfo(clientMsg.fileName);

                    if(files.contains(key))
                    {
                        int idx=files.indexOf(key);

                        files.get(idx).setContent(clientMsg.content);
                       boolean saved= new FileIO().Serialization(files);

                        if(saved) {
                            Msg a = new Msg();
                            a.response = "변경 완료";
                            oos.writeObject(a);
                            oos.flush();
                        }
                        else
                        {
                            Msg not =new Msg();
                            not.response="변경 실패";
                            oos.writeObject(not);
                            oos.flush();

                        }
                    }else {

                        Msg not =new Msg();
                        not.response="파일이 없습니다.";
                        oos.writeObject(not);
                        oos.flush();

                    }


                }
                if(clientMsg.delete)
                {
                    List<FileInfo> files = new FileIO().deserialization();

                   // FileInfo key = new FileInfo(clientMsg.fileName);
                      FileInfo key =new FileInfo(clientMsg.number);
                    if(files.contains(key))
                    {
                        int idx=files.indexOf(key);

                        boolean deleteFile =new FileIO().deleteFile(files.get(idx).getFileName());
                        files.remove(idx);
                        //boolean deleteFile =new FileIO().deleteFile(clientMsg.fileName);


                        for (int i = 0; i < files.size(); i++) {
                            files.get(i).setNumber(i + 1);
                        }

                        boolean saved= new FileIO().Serialization(files);


                        if(saved&&deleteFile){
                            Msg ok =new Msg();
                            ok.response="파일 삭제 완료";
                            oos.writeObject(ok);
                            oos.flush();
                        }

                    }else
                    {
                        Msg not =new Msg();
                        not.response="파일이 없습니다.";
                        oos.writeObject(not);
                        oos.flush();

                    }


                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private int getNextFileNumber(List<FileInfo> files) {
        int maxNumber = 0;
        for (FileInfo file : files) {
            if (file.getNumber() > maxNumber) {
                maxNumber = file.getNumber();
            }
        }
        return maxNumber + 1;
    }

}


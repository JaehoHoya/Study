package Doc;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class DocClient {

    static Scanner kbd= new Scanner(System.in);




    public static void main(String[] args){



        try {
                Socket s = new Socket("localhost", 1234);
                System.out.println("서버 접속");

                InputStream in=s.getInputStream();
                ObjectInputStream oin= new ObjectInputStream(in);
                Msg menu =(Msg)oin.readObject();

                OutputStream out =s.getOutputStream();
                ObjectOutputStream oos= new ObjectOutputStream(out);

               System.out.println(menu.menu);

               while (true)
               {
                   String m = kbd.nextLine();

                   if (m.equals("a"))
                   {
                       System.out.print("파일명:");
                       String fileName = kbd.nextLine();
                       System.out.print("작성자:");
                       String who = kbd.nextLine();
                       System.out.print("내용 입력:");
                       String content = kbd.nextLine();

                       Msg a = new Msg();
                       if (fileName != null && !fileName.equals(""))
                       {
                           byte[] fdata = new FileIO().load(fileName);
                           if (fdata != null)
                           {
                               a.fileName = fileName;
                               a.fileData = fdata;
                           }
                           else
                           {
                               System.err.println("파일이 없습니다.");
                           }
                       }

                       a.upload =true;
                       a.who = who;
                       a.content = content;

                       oos.writeObject(a);
                       oos.flush();

                       Msg res =(Msg)oin.readObject();
                       System.out.println(res.response);

                   }
                   else if (m.equals("s"))
                   {
                        Msg ss= new Msg();
                        ss.showFiles=true;
                        oos.writeObject(ss);

                       Msg list=(Msg)oin.readObject();
                        List<FileInfo> files =list.data;
                       System.out.println("\t\t\t\t\t\t<----------파일목록---------->");
                        for(FileInfo f: files)
                        {
                            System.out.println(f);
                        }

                   }

                   else  if(m.equals("f"))
                   {
                       System.out.print("검색할 파일 이름:");
                       String searchFileName= kbd.nextLine();
                       // 파일 이름 서버에 보내기
                       Msg f = new Msg();
                       f.search=true;
                       f.fileName = searchFileName;
                       oos.writeObject(f);
                       oos.flush();


                       Msg list= (Msg)oin.readObject();
                       List<FileInfo> data=list.data;
                       if(!data.isEmpty()) System.out.println(data.get(0));
                       else System.out.println(list.response);

                   }
                   if(m.equals("u"))
                   {
                       System.out.print("내용을 수정할 파일 이름:");
                       String FileName =kbd.nextLine();
                       System.out.println("내용 변경:");
                       String content=kbd.nextLine();

                       Msg a =new Msg();
                       a.update=true;
                       a.fileName=FileName;
                       a.content=content;

                       oos.writeObject(a);

                       Msg response=(Msg)oin.readObject();
                       System.out.println(response.response);

                   }
                   if(m.equals("d"))
                   {
                      // System.out.print("삭제할 파일 이름:");
                       // String fileNmae=kbd.nextLine();
                       System.out.print("삭제할 파일의 고유번호:");
                       int fileNumber=kbd.nextInt(); kbd.nextLine();
                       Msg d= new Msg();
                       d.delete =true;
                      // d.fileName=fileNmae;
                       d.number=fileNumber;
                       oos.writeObject(d);


                       Msg response =(Msg)oin.readObject();
                       System.out.println(response.response);

                   }


                   if(m.equals("x"))
                   {
                       System.out.println("사용자 로그아웃");
                       break;
                   }

               }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("클라이언트 종료");
    }




}
